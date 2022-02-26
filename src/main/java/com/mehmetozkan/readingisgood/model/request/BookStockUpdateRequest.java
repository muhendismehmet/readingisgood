package com.mehmetozkan.readingisgood.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class BookStockUpdateRequest {

    @NotNull(message = "ID can not be null.")
    private Long id;

    @NotNull(message = "SoldStock can not be null.")
    @Min(value = 1, message = "Stock must be higher than 1.")
    private Long soldStock;


}
