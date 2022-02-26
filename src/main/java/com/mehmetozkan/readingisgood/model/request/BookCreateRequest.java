package com.mehmetozkan.readingisgood.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class BookCreateRequest {

    @NotEmpty(message = "Name can not be empty.")
    private String name;

    private String description;

    @NotEmpty(message = "Writer can not be empty.")
    private String writer;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price can not be less than 0.")
    private BigDecimal price;

    @NotNull(message = "Stock can not be null.")
    @Min(value = 1, message = "Stock must be higher than 1.")
    private Long remainingStock;


}
