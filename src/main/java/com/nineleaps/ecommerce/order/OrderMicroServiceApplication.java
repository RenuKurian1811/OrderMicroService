package com.nineleaps.ecommerce.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.nineleaps.ecommerce.order.controller")
public class OrderMicroServiceApplication {

	public static void main(String[] args) {
//		CamelContext ctx = new DefaultCamelContext();
//		KafkaRouter kafkaRouter = new KafkaRouter();
        SpringApplication.run(OrderMicroServiceApplication.class, args);
        try {
        	System.out.println("inside main");
//        	ctx.addRoutes(kafkaRouter);
//        	ctx.start();
//            Thread.sleep(3*1000);
//            ctx.stop();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
