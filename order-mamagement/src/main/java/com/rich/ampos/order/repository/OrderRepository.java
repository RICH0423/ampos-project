package com.rich.ampos.order.repository;

import com.rich.ampos.order.repository.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String>  {
}
