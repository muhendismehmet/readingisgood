package com.mehmetozkan.readingisgood.service;

import com.mehmetozkan.readingisgood.entity.Book;
import com.mehmetozkan.readingisgood.entity.Customer;
import com.mehmetozkan.readingisgood.entity.Order;
import com.mehmetozkan.readingisgood.exception.EntityNotExistException;
import com.mehmetozkan.readingisgood.exception.OrderCountInvalidException;
import com.mehmetozkan.readingisgood.exception.lackOfStockException;
import com.mehmetozkan.readingisgood.model.dto.OrderDTO;
import com.mehmetozkan.readingisgood.model.dto.OrderDetailDTO;
import com.mehmetozkan.readingisgood.model.dto.OrderLightDTO;
import com.mehmetozkan.readingisgood.model.enums.Status;
import com.mehmetozkan.readingisgood.model.request.OrderByDateRequest;
import com.mehmetozkan.readingisgood.model.request.OrderRequest;
import com.mehmetozkan.readingisgood.model.response.OrderListResponse;
import com.mehmetozkan.readingisgood.model.response.OrderResponse;
import com.mehmetozkan.readingisgood.repository.BookRepository;
import com.mehmetozkan.readingisgood.repository.CustomerRepository;
import com.mehmetozkan.readingisgood.repository.OrderRepository;
import com.mehmetozkan.readingisgood.util.ModalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final BookRepository bookRepository;

    private final CustomerRepository customerRepository;

    private final OrderRepository orderRepository;

    public OrderService(BookRepository bookRepository, CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public OrderResponse createOrder(OrderRequest request) {

        Customer customer = customerRepository.getById(request.getCustomerID());

        List<Long> bookIDs = new ArrayList<>();
        request.getOrders().forEach(orderDTO -> bookIDs.add(orderDTO.getBookID()));
        List<Book> books = bookRepository.findAllById(bookIDs);

        Long totalBookCount = 0L;
        Map<Long, Long> bookAndCountMap = new HashMap<>();
        for (OrderDetailDTO dto : request.getOrders()) {

            if (dto.getOrderCount() <= 0)
                throw new OrderCountInvalidException(String.valueOf(dto.getOrderCount()));

            totalBookCount += dto.getOrderCount();
            bookAndCountMap.put(dto.getBookID(), dto.getOrderCount());
        }

        Order order = new Order();
        order.setBookList(books);
        //order.add(books);
        order.setTotalPrice(calculatePrice(bookAndCountMap, books));
        order.setDateCreated(new Date());
        order.setCustomerId(request.getCustomerID());
        order.setTotalBookCount(totalBookCount);
        customer.getOrderList().add(order);
        //customer.add(order);

        customerRepository.save(customer);

        logger.info("Order added to customer successfully! {}", customer);

        updateStockInformation(bookAndCountMap, books);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrder(ModalMapper.toLightDTO(order));
        orderResponse.setCustomer(ModalMapper.toLightDTO(customer));

        logger.info("Order created successfully! {}", orderResponse);

        return orderResponse;
    }

    private BigDecimal calculatePrice(Map<Long, Long> bookAndCountMap, List<Book> books) {

        logger.info("Price calculate started for books details {}!", books);

        long totalPrice = 0L;

        for (Map.Entry<Long, Long> entry : bookAndCountMap.entrySet()) {
            Book book = books.stream().filter(b -> b.getId().equals(entry.getKey())).findAny().orElse(null);
            if (book != null)
                totalPrice += book.getPrice().longValue() * entry.getValue();
            else
                throw new EntityNotExistException();
        }

        logger.info("Total price is: {}, books: {}", totalPrice, books);

        return new BigDecimal(totalPrice);

    }

    private void updateStockInformation(Map<Long, Long> bookAndCountMap, List<Book> books) {

        for (Map.Entry<Long, Long> entry : bookAndCountMap.entrySet()) {
            Book book = books.stream().filter(b -> b.getId().equals(entry.getKey())).findAny().orElse(null);

            if (book != null) {
                if (entry.getValue() > book.getRemainingStock())
                    throw new lackOfStockException();

                book.setRemainingStock(book.getRemainingStock() - entry.getValue());
            } else {
                throw new EntityNotExistException("Book not found.");
            }

        }

        logger.info("Stocks updated successfully for books! {}", books);

    }

    public OrderResponse getOrderById(Long id) {

        Customer customer = customerRepository.getCustomerByOrderListId(id);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setCustomer(ModalMapper.toLightDTO(customer));

        OrderLightDTO orderLightDTO = new OrderLightDTO();

        Order order = customer.getOrderList()
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (order != null)
            orderLightDTO = ModalMapper.toLightDTO(order);
        else
            throw new EntityNotExistException("No order found for customer.");

        orderResponse.setOrder(orderLightDTO);

        logger.info("Order response is ready for customer {}, response {}", ModalMapper.toLightDTO(customer), orderResponse);

        return orderResponse;

    }

    public OrderListResponse getOrderByDateInterval(OrderByDateRequest request) {

        List<Order> orderList = orderRepository.getAllByDateCreatedBetween(request.getStartDate(), request.getEndDate());
        List<OrderDTO> orderDTOS = new ArrayList<>();

        orderList.forEach(order -> orderDTOS.add(ModalMapper.toDTO(order)));

        logger.info("Orders get successfully for dates between [{}-{}]", request.getStartDate(), request.getEndDate());

        OrderListResponse response = new OrderListResponse();
        response.setStatus(Status.SUCCESS);
        response.setOrders(orderDTOS);

        return response;
    }
}
