package com.rich.ampos.order.service;

import com.netflix.discovery.converters.Auto;
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return orderRepository.findAll(pageable);
    }

    @Override
    public Optional<Order> find(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order create(OrderData orderData) {
        List<MenuItem> items = new ArrayList<>();
        orderData.getItems().stream().forEach(menuItem -> {
            String menuId = menuItem.getMenuId();
            Menu menu = menuServiceClient.findById(menuId);
            log.info("Get menu by id: {}, result: {}", menuId, menu);

            MenuItem item = MenuItem.builder()
                    .id(menuId)
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .quantity(menuItem.getQuantity())
                    .createdTime(Instant.now().toEpochMilli())
                    .build();
            items.add(item);
        });

        Order order = new Order();
        order.setItems(items);
        return orderRepository.save(order);
    }

    @Override
    public Order update(String id, Order newOrder) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setItems(newOrder.getItems());
                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new EntityNotFoundException(id));
    }
}
