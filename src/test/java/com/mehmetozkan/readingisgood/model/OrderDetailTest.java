package com.mehmetozkan.readingisgood.model;

import com.mehmetozkan.readingisgood.model.dto.BookDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderDetailTest {

    @Test
    public void it_should_convert_to_string_properly() {

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setBook(new BookDTO());
        orderDetail.setOrderCount(1L);

        String orderDetailStringValue = orderDetail.toString();


        assertThat(1L).isEqualTo(orderDetail.getOrderCount());

    }

}
