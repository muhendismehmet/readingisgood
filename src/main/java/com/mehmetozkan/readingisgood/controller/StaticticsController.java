package com.mehmetozkan.readingisgood.controller;

import com.mehmetozkan.readingisgood.model.response.CustomerStatisticsResponse;
import com.mehmetozkan.readingisgood.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "#{@msProperties.statistics.path}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Api
@SwaggerDefinition(tags = { @Tag(name = "getir-statistics-api", description = "Getir Statistics Api") })
public class StaticticsController {

    private static final Logger logger = LoggerFactory.getLogger(StaticticsController.class);

    private final StatisticsService statisticsService;

    public StaticticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Customer Statistics", notes = "Get Customer Statistics")
    public CustomerStatisticsResponse getCustomerStatistics(@PathVariable Long id){
        logger.info("Get customer statistics by customer id {}", id);
        return statisticsService.getCustomerStatistics(id);
    }
}
