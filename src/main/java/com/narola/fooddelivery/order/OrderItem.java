package com.narola.fooddelivery.order;

import com.narola.fooddelivery.dishes.model.Dish;

public class OrderItem {

	private int OrderItemId;
	private Order order;
	private Dish dish;
	private int qty;
	private int amount;
	public int getOrderItemId() {
		return OrderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		OrderItemId = orderItemId;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Dish getDish() {
		return dish;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		setAmount(qty*dish.getPrice());
		this.qty = qty;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
