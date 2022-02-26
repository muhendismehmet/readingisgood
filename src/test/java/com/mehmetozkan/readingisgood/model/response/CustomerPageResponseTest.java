package com.mehmetozkan.readingisgood.model.response;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerPageResponseTest {

    @Test
    public void it_should_convert_to_string_properly() {

        CustomerPageResponse response = new CustomerPageResponse();
        response.setOrderDTOS(new PageImpl<>(Collections.emptyList()));


        assertThat(new PageImpl<>(Collections.emptyList())).isEqualTo(response.getOrderDTOS());
     }
}
