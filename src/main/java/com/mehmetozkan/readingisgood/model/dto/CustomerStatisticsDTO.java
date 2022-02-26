package com.mehmetozkan.readingisgood.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Month;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStatisticsDTO {

    private Month month;
    private int totalOrderCount;
    private int totalBookCount;
    private BigDecimal totalPurchasedAmount;
}
