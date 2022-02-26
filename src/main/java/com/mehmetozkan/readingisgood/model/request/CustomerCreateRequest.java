package com.mehmetozkan.readingisgood.model.request;


import com.mehmetozkan.readingisgood.entity.Order;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
public class CustomerCreateRequest {

    @NotNull(message = "Name can not be null.")
    @Size(min = 3, max = 16, message = "Name must have min 3 and max 16 character.")
    private String name;

    @NotNull(message = "Surname can not be null.")
    @Size(min = 3, max = 16, message = "Name must have min 3 and max 16 character.")
    private String surname;

    @Email(message = "Invalid email format")
    private String email;

    private List<Order> orderList;


}
