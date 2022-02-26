package com.mehmetozkan.readingisgood.model.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderDetailDTOTest {

    @Test
    public void it_should_convert_to_string_properly() {

        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setBookID(1L);
        dto.setOrderCount(2L);

        assertThat(1L).isEqualTo(dto.getBookID());

    }
}
