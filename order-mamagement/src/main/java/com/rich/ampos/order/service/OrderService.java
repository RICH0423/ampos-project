package com.rich.ampos.order.service;

import com.rich.ampos.order.model.OrderData;
import com.rich.ampos.order.repository.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author rich
 */
public interface OrderService {

    Page<Order> find(Pageable pageable);

    Order create(OrderData orderData);

    Order update(String id, OrderData orderData);
}
