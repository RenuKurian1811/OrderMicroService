package com.nineleaps.ecommerce.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.nineleaps.ecommerce.order.entity.OrderEntity;
import com.nineleaps.ecommerce.order.model.OrderModel;

@Service
public class KafkaSender {

	@Autowired
	private KafkaTemplate<String, OrderModel> kafkaTemplate;
	String kafkaTopic = "orderTopic";

	public void send(OrderModel order) {

		kafkaTemplate.send(kafkaTopic, order);
	}
}
