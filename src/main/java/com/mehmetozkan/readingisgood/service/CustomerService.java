package com.mehmetozkan.readingisgood.service;

import com.mehmetozkan.readingisgood.entity.Customer;
import com.mehmetozkan.readingisgood.entity.Order;
import com.mehmetozkan.readingisgood.exception.EntityNotExistException;
import com.mehmetozkan.readingisgood.exception.MailAlreadyTakenException;
import com.mehmetozkan.readingisgood.model.dto.OrderDTO;
import com.mehmetozkan.readingisgood.model.enums.Status;
import com.mehmetozkan.readingisgood.model.request.CustomerCreateRequest;
import com.mehmetozkan.readingisgood.model.request.CustomerParameterRequest;
import com.mehmetozkan.readingisgood.model.response.CustomerPageResponse;
import com.mehmetozkan.readingisgood.model.response.CustomerResponse;
import com.mehmetozkan.readingisgood.repository.CustomerRepository;
import com.mehmetozkan.readingisgood.repository.OrderRepository;
import com.mehmetozkan.readingisgood.util.ModalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    private final OrderRepository orderRepository;

    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public CustomerResponse createCustomer(CustomerCreateRequest request) {

        validateEmail(request.getEmail());

        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setSurname(request.getSurname());
        customer.setEmail(request.getEmail());
        customer.setOrderList(request.getOrderList());

        customerRepository.save(customer);

        logger.info("Customer saved successfully! {}", customer);

        CustomerResponse response = new CustomerResponse();
        response.setStatus(Status.SUCCESS);
        response.setCustomer(ModalMapper.toDTO(customer));

        return response;
    }

    private void validateEmail(String email) {

        Customer customer = customerRepository.getCustomerByEmail(email);

        if(customer != null) {
            throw new MailAlreadyTakenException(email);
        }
    }

    public CustomerResponse getCustomer(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistException(String.valueOf(id)));

        CustomerResponse response = new CustomerResponse();
        response.setStatus(Status.SUCCESS);
        response.setCustomer(ModalMapper.toDTO(customer));

        logger.info("Customer get successfully! {}", response);

        return response;
    }

    public CustomerPageResponse getCustomerOrders(CustomerParameterRequest request) {

        CustomerPageResponse customerPageResponse = new CustomerPageResponse();

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        List<Order> orders = orderRepository.findByCustomerId(request.getId(), pageable);

        if(orders.isEmpty()){
            customerPageResponse.setOrderDTOS(new PageImpl<>(Collections.emptyList()));
            logger.warn("Customer page is empty for request! {}", request);
            return customerPageResponse;
        }

        List<OrderDTO> dtoList = new ArrayList<>();

        orders.forEach(order -> dtoList.add(ModalMapper.toDTO(order)));

        customerPageResponse.setOrderDTOS(new PageImpl<>(dtoList, PageRequest.of(request.getPage(), request.getSize()), customerRepository.count()));

        logger.info("Customer page is ready! {}", customerPageResponse);

        return customerPageResponse;

    }
}
