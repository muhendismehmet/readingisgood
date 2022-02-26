package com.mehmetozkan.readingisgood.controller;

import com.mehmetozkan.readingisgood.model.request.OrderByDateRequest;
import com.mehmetozkan.readingisgood.model.request.OrderRequest;
import com.mehmetozkan.readingisgood.model.response.OrderListResponse;
import com.mehmetozkan.readingisgood.model.response.OrderResponse;
import com.mehmetozkan.readingisgood.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "#{@msProperties.order.path}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

@Api
@SwaggerDefinition(tags = {
        @Tag(name = "getir-order-api", description = "Getir Order Api")
})
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Order", notes = "Create Order")
    public OrderResponse createOrder(@Valid @RequestBody OrderRequest request) {
        logger.info("Creating order started for request {}", request);
        return orderService.createOrder(request);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Order By Id", notes = "Get Order By Id")
    public OrderResponse getOrderById(@PathVariable Long id) {
        logger.info("Get order started for order id {}", id);
        return orderService.getOrderById(id);
    }

    @PostMapping(value = "/date", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Order By Date Interval", notes = "Get Order By Date Interval")
    public OrderListResponse getOrderByDateInterval(@Valid @RequestBody OrderByDateRequest request) {
        logger.info("Get order by date interval started for request {}", request);
        return orderService.getOrderByDateInterval(request);
    }
}
