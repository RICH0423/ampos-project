package com.rich.ampos.order.service;

import com.rich.ampos.order.exception.EntityNotFoundException;
import com.rich.ampos.order.model.Menu;
import com.rich.ampos.order.model.MenuItem;
import com.rich.ampos.order.model.OrderData;
import com.rich.ampos.order.repository.OrderRepository;
import com.rich.ampos.order.repository.entity.Order;
import com.rich.ampos.order.service.client.MenuServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rich
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuServiceClient menuServiceClient;

    @Override
    public Page<Order> find(Pageable pageable) {
        Page<Order> result = orderRepository.findAll(pageable);

        result.getContent().stream().forEach(order -> {
            BigDecimal totalPrice = order.getItems().stream()
                    .map(MenuItem::caculatePaymentPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            log.debug("Total price: {}", totalPrice);
            order.setTotalPrice(totalPrice);
        });

        return result;
    }

    @Override
    public Order create(OrderData orderData) {
        Order order = new Order();
        order.setItems(transformToMenuItems(orderData));
        return orderRepository.save(order);
    }

    @Override
    public Order update(String id, OrderData orderData) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setItems(transformToMenuItems(orderData));
                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    private List<MenuItem> transformToMenuItems(OrderData orderData) {
        List<MenuItem> items = new ArrayList<>();

        orderData.getItems().stream().forEach(
                menuItem -> {
                    String menuId = menuItem.getMenuId();
                    Menu menu = menuServiceClient.findById(menuId);

                    MenuItem item = MenuItem.builder()
                            .id(menuId)
                            .quantity(menuItem.getQuantity())
                            .price(menu.getPrice())
                            .name(menu.getName())
                            .createdTime(Instant.now().toEpochMilli())
                            .build();
                    items.add(item);
                });
        return items;
    }
}
