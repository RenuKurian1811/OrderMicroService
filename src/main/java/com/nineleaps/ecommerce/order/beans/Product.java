package com.nineleaps.ecommerce.order.beans;

import java.io.Serializable;

import org.springframework.stereotype.Component;

public class Product implements Serializable{
	private String productId;
	private String name;
	private String price;
	private String description;
	private String supplierId;

	public Product() {
		
	}

	public Product(String productId, String name, String price, String description, String supplierId) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.description = description;
		this.supplierId = supplierId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
}

