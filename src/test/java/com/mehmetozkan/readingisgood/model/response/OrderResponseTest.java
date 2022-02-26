package com.mehmetozkan.readingisgood.model.response;

import com.mehmetozkan.readingisgood.entity.Customer;
import com.mehmetozkan.readingisgood.entity.Order;
import com.mehmetozkan.readingisgood.model.dto.CustomerLightDTO;
import com.mehmetozkan.readingisgood.model.dto.OrderLightDTO;
import com.mehmetozkan.readingisgood.util.ModalMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderResponseTest {

    @Test
    public void it_should_convert_to_string_properly() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Peter");
        customer.setSurname("Morgan");
        customer.setEmail("petermorgan@gmail.com");
        customer.setOrderList(new ArrayList<>());

        CustomerLightDTO customerLightDTO = ModalMapper.toLightDTO(customer);

        Order order = new Order();
        order.setId(1L);
        order.setDateCreated(new Date(1995, Calendar.JULY, 7));
        order.setTotalPrice(new BigDecimal(15));
        order.setCustomerId(1L);
        order.setBookList(new ArrayList<>());

        OrderLightDTO orderLightDTO = ModalMapper.toLightDTO(order);

        OrderResponse response = new OrderResponse();
        response.setOrder(orderLightDTO);
        response.setCustomer(customerLightDTO);

        String responseStringValue = response.toString();


        assertThat(1L).isEqualTo(customer.getId());
        assertThat(1L).isEqualTo(order.getId());

     }
}
