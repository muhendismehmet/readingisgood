package com.mehmetozkan.readingisgood.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CustomerParameterRequest {

    @NotNull(message = "Id can not be null")
    private Long id;

    @NotNull(message = "Page can not be null")
    @Min(value = 0, message = "Page must be greater or equal 0.")
    private int page;

    @NotNull(message = "Size can not be null")
    @Min(value = 0, message = "Size must be greater or equal 0.")
    private int size;


}
