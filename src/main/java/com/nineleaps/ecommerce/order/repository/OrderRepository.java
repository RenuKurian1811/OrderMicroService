package com.nineleaps.ecommerce.order.repository;

import org.springframework.data.repository.CrudRepository;

import com.nineleaps.ecommerce.order.entity.OrderEntity;
import com.nineleaps.ecommerce.order.model.OrderModel;

public interface OrderRepository extends CrudRepository<OrderEntity, String>{

}

