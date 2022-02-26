package com.mehmetozkan.readingisgood.model.response;

import com.mehmetozkan.readingisgood.model.dto.CustomerStatisticsDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CustomerStatisticsResponse {
    List<CustomerStatisticsDTO> customerStatistics;
}
