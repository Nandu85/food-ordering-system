package com.narola.fooddelivery.cart;

import java.io.IOException;
import java.util.ArrayList;

import com.narola.fooddelivery.dishes.model.Dish;
import com.narola.fooddelivery.restaurants.model.Restaurant;
import com.narola.fooddelivery.user.User;

public class Cart {
	
	private int cartId;
	private User user;
	private Restaurant restaurant;
	private int total;
	private ArrayList<CartItem> items;
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public int getTotal() {
		setTotal(CartDAO.getCartTotal(cartId));
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public ArrayList<CartItem> getItems() throws IOException {
//		if(items==null)
			setItems((ArrayList<CartItem>) CartItemsDAO.getItemsofCart(cartId));
		return items;
	}
	public void setItems(ArrayList<CartItem> items) {
		this.items = items;
	}
	
	
	public CartItem itemBelongToCart(Dish dish) throws IOException {
		ArrayList<CartItem> cartItems=this.getItems();
		for (CartItem cartItem : cartItems) {
			if(cartItem.getDish().getDishId()==dish.getDishId())
				return cartItem;
		}
		
		return null;
		
	}

}
