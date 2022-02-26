package com.mehmetozkan.readingisgood.model.response;


import com.mehmetozkan.readingisgood.model.dto.CustomerDTO;
import com.mehmetozkan.readingisgood.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerResponse {

    private CustomerDTO customer;
    private Status status;

}
