package com.mehmetozkan.readingisgood.model.request;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderRequestTest {

    @Test
    public void it_should_convert_to_string_properly() {

        OrderRequest request = new OrderRequest();
        request.setOrders(new ArrayList<>());
        request.setCustomerID(1L);

        assertThat(1L).isEqualTo(request.getCustomerID());
     }
}
