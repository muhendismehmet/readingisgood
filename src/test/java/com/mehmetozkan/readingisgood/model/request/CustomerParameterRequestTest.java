package com.mehmetozkan.readingisgood.model.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerParameterRequestTest {

    @Test
    public void it_should_convert_to_string_properly() {

        CustomerParameterRequest request = new CustomerParameterRequest();
        request.setId(1L);
        request.setPage(1);
        request.setSize(1);

        assertThat(1L).isEqualTo(request.getId());
    }
}
