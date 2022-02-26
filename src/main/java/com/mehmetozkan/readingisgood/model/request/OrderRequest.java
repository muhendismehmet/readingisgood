package com.mehmetozkan.readingisgood.model.request;


import com.mehmetozkan.readingisgood.model.dto.OrderDetailDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderRequest {

    @NotNull(message = "CustomerId can not be null")
    private Long customerID;

    @NotEmpty(message = "Order list can not be empty.")
    private List<OrderDetailDTO> orders;


}
