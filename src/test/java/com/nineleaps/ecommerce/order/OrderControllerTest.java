package com.nineleaps.ecommerce.order;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nineleaps.ecommerce.order.beans.Product;
import com.nineleaps.ecommerce.order.controller.OrderController;
import com.nineleaps.ecommerce.order.model.ItemsModel;
import com.nineleaps.ecommerce.order.model.OrderModel;
import com.nineleaps.ecommerce.order.service.OrderService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {
	
	@Autowired
	OrderController orderController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private OrderService orderService;
	
	@MockBean
	KafkaTemplate<String, OrderModel> KafkaJsontemplate;
	
	@BeforeEach
	void setupThis() {
		System.out.println("@BeforeEach executed");
	}
	
	Product productStub = new Product("P1","Pname1","100","Desc1", "S1");
	
	@Test
	public void testCreateOrderSuccess() throws Exception {

		List<ItemsModel> itemlist = new ArrayList<ItemsModel>();
		ItemsModel itemModel = new ItemsModel("P1",1,2);
		itemlist.add(itemModel);
		OrderModel orderStub = new OrderModel("O1", "date", "customerName", "customerEmail", "customerAddress",
				itemlist, 100);
		when(orderService.checkProductAvailability(anyString())).thenReturn(productStub);
		mockMvc.perform(post("/order/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString((orderStub))))
				.andExpect(status().isOk());
		
	}	
	
	static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
