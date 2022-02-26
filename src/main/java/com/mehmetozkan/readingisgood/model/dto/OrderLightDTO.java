package com.mehmetozkan.readingisgood.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLightDTO {

    private BigDecimal totalPrice;
    private Date dateCreated;
    private List<BookLightDTO> bookList;

}
