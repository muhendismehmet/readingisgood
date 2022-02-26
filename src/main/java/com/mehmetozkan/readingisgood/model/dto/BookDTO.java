package com.mehmetozkan.readingisgood.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookDTO {

    private Long id;
    private String name;
    private String description;
    private String writer;
    private Double price;
    private Long remainingStock;

}
