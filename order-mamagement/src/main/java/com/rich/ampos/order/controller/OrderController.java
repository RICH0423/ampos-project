package com.rich.ampos.order.controller;

import com.rich.ampos.order.exception.EntityNotFoundException;
import com.rich.ampos.order.model.OrderData;
import com.rich.ampos.order.repository.entity.Order;
import com.rich.ampos.order.service.OrderService;
import com.rich.ampos.order.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author rich
 */
@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(Constants.API_ORDER)
public class OrderController {

    @Value("${pagination.start}")
    private int pageStart;

    @Value("${pagination.size}")
    private int pageSize;

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    Page<Order> findAll(
            @RequestParam(value="page", required=false) Integer page,
            @RequestParam(value="size", required=false) Integer size) {
        if(page == null) {
            page = pageStart;
        }
        if(size == null) {
            size = pageSize;
        }

        return orderService.find(PageRequest.of(page, size));
    }

    @PostMapping
    Order createOrder(@Valid @RequestBody OrderData orderData) {
        return orderService.create(orderData);
    }

    @GetMapping("/{id}")
    Order findById(@PathVariable String id) {
        return orderService.find(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @PutMapping("/{id}")
    Order updateOrder(@RequestBody Order newOrder, @PathVariable String id) {
        return orderService.update(id, newOrder);
    }

}
