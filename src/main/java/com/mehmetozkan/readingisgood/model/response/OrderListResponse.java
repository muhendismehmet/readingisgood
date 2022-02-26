package com.mehmetozkan.readingisgood.model.response;

import com.mehmetozkan.readingisgood.model.dto.OrderDTO;
import com.mehmetozkan.readingisgood.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderListResponse {

    private List<OrderDTO> orders;
    private Status status;
}
