package com.nineleaps.ecommerce.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nineleaps.ecommerce.order.beans.Product;
import com.nineleaps.ecommerce.order.beans.ProductNotFoundException;
import com.nineleaps.ecommerce.order.config.Publisher;
import com.nineleaps.ecommerce.order.entity.OrderEntity;
import com.nineleaps.ecommerce.order.model.OrderModel;
import com.nineleaps.ecommerce.order.service.KafkaSender;
import com.nineleaps.ecommerce.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

//	@Autowired
//	CamelContext camelContext;
//	
	@Autowired
	private OrderService orderService;

//	ObjectMapper mapper = new ObjectMapper();
//	
//	@Autowired
//	KafkaRouter kafkaRouteProducer;
//	

//	@EndpointInject(uri ="direct:kafkaRoute")
//	ProducerTemplate kafkaProducer;

//	@PostConstruct
//	public void setup() {
//		try {
//			camelContext.addRoutes(kafkaRouteProducer);
////			camelContext.start();
////            Thread.sleep(3*1000);
////            ctx.stop();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	@Autowired
//	KafkaSender kafkaSender;

	@Autowired
	KafkaTemplate<String, OrderModel> KafkaJsontemplate;

	@PostMapping("/create")
	public ResponseEntity<?> createOrder(@RequestBody OrderModel order) {
		ResponseEntity<?> rs = null;
		ProductNotFoundException exception = new ProductNotFoundException();
		int itemCount = order.getItem().size();
		for (int i = 0; i < itemCount; i++) {
			Product isProductAvail = orderService.checkProductAvailability(order.getItem().get(i).getProductId());
			// if(isProductAvail.isPresent()) {
			if (null != isProductAvail && null != isProductAvail.getProductId()) {
				orderService.saveOrderDetails(order);
				rs = new ResponseEntity<>(order, HttpStatus.OK);
				// kafkaProducer.sendBody("direct:kafkaRoute",mapper.writeValueAsString(order));
				// return new ResponseEntity<>(order, HttpStatus.OK);
			//	kafkaSender.send(order);
				KafkaJsontemplate.send("orderTopic",order);
				System.out.println("Message sent to the Kafka Topic java_in_use_topic Successfully");
			} else {
				exception.setErrorMessage("Product not available in inventory");
				rs = new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
			}
		}
		return rs;
	}
}
