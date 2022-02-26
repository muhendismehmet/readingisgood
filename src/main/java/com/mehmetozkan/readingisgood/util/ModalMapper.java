package com.mehmetozkan.readingisgood.util;

import com.mehmetozkan.readingisgood.entity.Book;
import com.mehmetozkan.readingisgood.entity.Customer;
import com.mehmetozkan.readingisgood.entity.Order;
import com.mehmetozkan.readingisgood.model.dto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModalMapper {

    public static BookDTO toDTO(Book book) {
        return BookDTO.builder().id(book.getId())
                .name(book.getName()).description(book.getDescription())
                .writer(book.getWriter()).price(book.getPrice().doubleValue())
                .remainingStock(book.getRemainingStock()).build();
    }

    public static BookLightDTO toLightDTO(Book book) {
        return BookLightDTO.builder().name(book.getName())
                .price(book.getPrice()).writer(book.getWriter()).build();
    }

    public static CustomerDTO toDTO(Customer customer) {
        List<OrderDTO> orderDTOS = new ArrayList<>();

        if (Objects.nonNull(customer.getOrderList()))
            customer.getOrderList().forEach(order -> orderDTOS.add(toDTO(order)));

        return CustomerDTO.builder().id(customer.getId()).name(customer.getName())
                .surname(customer.getSurname()).email(customer.getEmail())
                .orderList(orderDTOS).build();
    }

    public static CustomerLightDTO toLightDTO(Customer customer) {
        return CustomerLightDTO.builder().name(customer.getName())
                .surname(customer.getSurname()).email(customer.getEmail()).build();
    }

    public static OrderDTO toDTO(Order order) {
        List<BookDTO> bookDTOS = new ArrayList<>();
        order.getBookList().forEach(book -> bookDTOS.add(toDTO(book)));

        return OrderDTO.builder().id(order.getId()).totalPrice(order.getTotalPrice())
                .dateCreated(order.getDateCreated()).customerId(order.getCustomerId())
                .totalBookCount(order.getTotalBookCount()).bookList(bookDTOS).build();
    }

    public static OrderLightDTO toLightDTO(Order order) {
        List<BookLightDTO> bookLightDTOS = new ArrayList<>();
        order.getBookList().forEach(book -> bookLightDTOS.add(toLightDTO(book)));

        return OrderLightDTO.builder().dateCreated(order.getDateCreated())
                .totalPrice(order.getTotalPrice()).bookList(bookLightDTOS).build();
    }
}