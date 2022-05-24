package com.narola.fooddelivery.cart;

import java.util.ArrayList;

import com.narola.fooddelivery.dishes.model.Dish;

public class CartItem {
	
	private int itemId;
	private Cart cart;
	private Dish dish;
	private int qty;
	private int amount;

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
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
		this.qty = qty;
		setAmount(qty*dish.getPrice());
	}
	
	public int getAmount() {
		if(dish!=null && amount==0)
			setAmount(dish.getPrice()*qty);
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
		
	}
	
	
	

}
