package com.mehmetozkan.readingisgood.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private List<OrderDTO> orderList;
}
