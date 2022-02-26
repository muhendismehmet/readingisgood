package com.mehmetozkan.readingisgood.model.dto;

import com.mehmetozkan.readingisgood.entity.Customer;
import com.mehmetozkan.readingisgood.util.ModalMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerDTOTest {

    @Test
    public void it_should_convert_to_string_properly() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Peter");
        customer.setSurname("Morgan");
        customer.setEmail("petermorgan@gmail.com");
        customer.setOrderList(new ArrayList<>());

        CustomerDTO dto = ModalMapper.toDTO(customer);

        assertThat(1L).isEqualTo(dto.getId());

    }
}
