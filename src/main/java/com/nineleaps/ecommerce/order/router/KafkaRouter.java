//package com.nineleaps.ecommerce.order.router;
//
//import org.apache.camel.ProducerTemplate;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.component.kafka.KafkaConstants;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.nineleaps.ecommerce.order.controller.OrderController;
//
//@Component
//public class KafkaRouter extends RouteBuilder {
//
//	
//	@Override
//	public void configure() throws Exception {
//
//		try {
//
//			System.out.println("inside router");
//			String topicName = "topic=orderTopic";
//			String kafkaServer = "kafka:localhost:9092";
//			String zooKeeperHost = "zookeeperHost=localhost&zookeeperPort=2181";
//			String serializerClass = "serializerClass=kafka.serializer.StringEncoder";
//			String toKafka = "kafka:orderTopic?brokers=localhost:9092";
//			
////			from("file:home/Nineleaps?fileName=KafkaTest.txt&charset=UTF-8&noop=true")
////			.to("kafka://localhost:9092?topic=orderTopic&brokers=localhost:9092");
//			
//			from("direct:kafkaRoute")
//            .to("kafka://localhost:9092?topic=orderTopic&brokers=localhost:9092");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("msg published");
//
//	}
//
//}
