package com.mehmetozkan.readingisgood.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookLightDTO {
    private String name;
    private String writer;
    private BigDecimal price;
}
