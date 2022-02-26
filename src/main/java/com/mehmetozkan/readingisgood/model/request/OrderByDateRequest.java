package com.mehmetozkan.readingisgood.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
public class OrderByDateRequest {

    @NotNull(message = "Start date can not be null.")
    private Date startDate;

    @NotNull(message = "End date can not be null.")
    private Date endDate;

}
