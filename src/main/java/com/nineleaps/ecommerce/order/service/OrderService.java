package com.nineleaps.ecommerce.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.nineleaps.ecommerce.order.beans.Product;
import com.nineleaps.ecommerce.order.controller.FeignController;
import com.nineleaps.ecommerce.order.entity.ItemsEntity;
import com.nineleaps.ecommerce.order.entity.OrderEntity;
import com.nineleaps.ecommerce.order.model.OrderModel;
import com.nineleaps.ecommerce.order.repository.OrderRepository;

@Component
@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired(required = true)
	private FeignController feignController;

	public Product checkProductAvailability(String productId) {
		return feignController.checkProductAvailability(productId);
	}

	public void saveOrderDetails(OrderModel order) {
		OrderEntity orderEntity = new OrderEntity();
		List<ItemsEntity> itemEntityList = new ArrayList<ItemsEntity>();
		
		int n = order.getItem().size();
		String productId = null;
		double price = 0.0;
		int quantity = 0;

		for (int i = 0; i < order.getItem().size(); i++) {
			productId = order.getItem().get(i).getProductId();
			price = order.getItem().get(i).getPrice();
			quantity = order.getItem().get(i).getQuantity();
			ItemsEntity itemEntity = new ItemsEntity(productId, quantity, price);
			itemEntityList.add(itemEntity);

		}

		
		OrderEntity entity = new OrderEntity();
		entity.setId(order.getId());
		entity.setItem(itemEntityList);
		entity.setCustomerEmail(order.getCustomerEmail());
		entity.setCustomerName(order.getCustomerName());
		entity.setCustomerAddress(order.getCustomerAddress());
		entity.setTotal(order.getTotal());
		entity.setDate(order.getDate());
		
		orderRepository.save(entity);
	}

}
