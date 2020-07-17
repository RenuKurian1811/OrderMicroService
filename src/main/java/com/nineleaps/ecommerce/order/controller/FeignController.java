package com.nineleaps.ecommerce.order.controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nineleaps.ecommerce.order.beans.Product;

@FeignClient(name="ProductMicroService", url = "http://localhost:8081")
@RibbonClient(name="ProductMicroService")
public interface FeignController {

	@GetMapping(value= "/product/getProduct/{id}")
	public Product checkProductAvailability(@PathVariable String id);
}
