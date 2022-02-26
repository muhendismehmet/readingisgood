package com.mehmetozkan.readingisgood.model.response;

import com.mehmetozkan.readingisgood.entity.Customer;
import com.mehmetozkan.readingisgood.model.dto.CustomerDTO;
import com.mehmetozkan.readingisgood.model.enums.Status;
import com.mehmetozkan.readingisgood.util.ModalMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerResponseTest {

    @Test
    public void it_should_convert_to_string_properly() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Peter");
        customer.setSurname("Morgan");
        customer.setEmail("petermorgan@gmail.com");
        customer.setOrderList(new ArrayList<>());

        CustomerDTO dto = ModalMapper.toDTO(customer);

        CustomerResponse response = new CustomerResponse();
        response.setCustomer(dto);
        response.setStatus(Status.SUCCESS);

        assertThat(customer.getId()).isEqualTo(response.getCustomer().getId());

     }
}
