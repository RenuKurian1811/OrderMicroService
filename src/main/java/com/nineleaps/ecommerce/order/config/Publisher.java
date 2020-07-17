package com.nineleaps.ecommerce.order.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.nineleaps.ecommerce.order.entity.OrderEntity;

public class Publisher {
	private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);

	  @Value("${kafka.topic.json}")
	  private String jsonTopic;

	  @Autowired
	  private KafkaTemplate<String, OrderEntity> kafkaTemplate;

	  public void send(OrderEntity car) {
	    LOGGER.info("sending car='{}'", car.toString());
	    kafkaTemplate.send(jsonTopic, car);
	  }
}
