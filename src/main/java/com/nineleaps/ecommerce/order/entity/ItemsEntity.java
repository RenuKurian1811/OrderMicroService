package com.nineleaps.ecommerce.order.entity;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.Column;

@org.springframework.data.cassandra.core.mapping.UserDefinedType("items")
public class ItemsEntity implements Serializable{
	
	public ItemsEntity() {

	}
	
	public ItemsEntity(String productId, int quantity, double price) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

	@Column("product_id")
	private String productId;
	@Column("quantity")
	private int quantity;
	@Column("price")
	private double price;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
