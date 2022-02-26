package com.mehmetozkan.readingisgood.model.response;

import com.mehmetozkan.readingisgood.model.dto.CustomerLightDTO;
import com.mehmetozkan.readingisgood.model.dto.OrderLightDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderResponse {
    private CustomerLightDTO customer;
    private OrderLightDTO order;
}
