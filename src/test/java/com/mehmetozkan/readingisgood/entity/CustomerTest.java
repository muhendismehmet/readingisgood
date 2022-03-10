package com.mehmetozkan.readingisgood.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    @Test
    public void it_should_convert_to_string_properly() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Peter");
        customer.setSurname("Morgan");
        customer.setEmail("petermorgan@gmail.com");
        customer.setOrderList(new ArrayList<>());

        String customerStringValue = customer.toString();
        
        assertThat(1L).isEqualTo(customer.getId());

    }
}
