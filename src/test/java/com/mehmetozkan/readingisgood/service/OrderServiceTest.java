package com.mehmetozkan.readingisgood.service;

import com.mehmetozkan.readingisgood.entity.Book;
import com.mehmetozkan.readingisgood.entity.Customer;
import com.mehmetozkan.readingisgood.entity.Order;
import com.mehmetozkan.readingisgood.exception.EntityNotExistException;
import com.mehmetozkan.readingisgood.model.dto.OrderDetailDTO;
import com.mehmetozkan.readingisgood.model.request.OrderByDateRequest;
import com.mehmetozkan.readingisgood.model.request.OrderRequest;
import com.mehmetozkan.readingisgood.repository.BookRepository;
import com.mehmetozkan.readingisgood.repository.CustomerRepository;
import com.mehmetozkan.readingisgood.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest(classes = {OrderService.class})
@ActiveProfiles("dev")
@Import({EntityNotExistException.class})
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void it_should_create_order() {

        Book book = new Book();
        book.setId(1L);
        book.setName("The Silmarillion");
        book.setDescription("The Silmarillion is a collection of mythopoeic stories.");
        book.setWriter("J. R. R. Tolkien");
        book.setPrice(new BigDecimal(15));
        book.setRemainingStock(10L);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book);
        bookList.add(book);


        when(bookRepository.findAllById(Mockito.anyCollection())).thenReturn(bookList);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Peter");
        customer.setSurname("Morgan");
        customer.setEmail("petermorgan@gmail.com");
        customer.setOrderList(new ArrayList<>());

        when(customerRepository.getById(customer.getId())).thenReturn(customer);

        List<OrderDetailDTO> orders = new ArrayList<>();
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setOrderCount(1L);
        dto.setBookID(1L);
        orders.add(dto);

        OrderRequest request = new OrderRequest();
        request.setCustomerID(1L);
        request.setOrders(orders);

        orderService.createOrder(request);
    }

    @Test
    public void it_should_get_order_by_id() {

        Book book = new Book();
        book.setId(1L);
        book.setName("The Silmarillion");
        book.setDescription("The Silmarillion is a collection of mythopoeic stories.");
        book.setWriter("J. R. R. Tolkien");
        book.setPrice(new BigDecimal(15));
        book.setRemainingStock(10L);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book);
        bookList.add(book);

        List<Order> orderList = new ArrayList<>();

        Order order = new Order();
        order.setId(1L);
        order.setDateCreated(new Date(1995, Calendar.JULY, 7));
        order.setTotalPrice(new BigDecimal(15));
        order.setCustomerId(1L);
        order.setBookList(bookList);

        orderList.add(order);
        orderList.add(order);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Peter");
        customer.setSurname("Morgan");
        customer.setEmail("petermorgan@gmail.com");
        customer.setOrderList(orderList);

        when(customerRepository.getCustomerByOrderListId(customer.getId())).thenReturn(customer);

        orderService.getOrderById(1L);
    }

    public void it_should_get_order_by_id_and_throws_exception() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Peter");
        customer.setSurname("Morgan");
        customer.setEmail("petermorgan@gmail.com");
        customer.setOrderList(new ArrayList<>());

        when(customerRepository.getCustomerByOrderListId(customer.getId())).thenReturn(customer);

        orderService.getOrderById(1L);
    }

    @Test
    public void it_should_get_order_by_date_interval() {

        OrderByDateRequest request = new OrderByDateRequest();
        request.setStartDate(new Date(1995, Calendar.JULY, 7));
        request.setStartDate(new Date(2021, Calendar.JULY, 7));

        Book book = new Book();
        book.setId(1L);
        book.setName("The Silmarillion");
        book.setDescription("The Silmarillion is a collection of mythopoeic stories.");
        book.setWriter("J. R. R. Tolkien");
        book.setPrice(new BigDecimal(15));
        book.setRemainingStock(10L);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book);
        bookList.add(book);

        List<Order> orderList = new ArrayList<>();

        Order order = new Order();
        order.setId(1L);
        order.setDateCreated(new Date(1995, Calendar.JULY, 7));
        order.setTotalPrice(new BigDecimal(15));
        order.setCustomerId(1L);
        order.setBookList(bookList);

        orderList.add(order);
        orderList.add(order);

        when(orderRepository.getAllByDateCreatedBetween(request.getStartDate(), request.getEndDate()))
                .thenReturn(orderList);

        orderService.getOrderByDateInterval(request);
    }
}
