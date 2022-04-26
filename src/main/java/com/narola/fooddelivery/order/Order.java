package com.narola.fooddelivery.order;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import com.narola.fooddelivery.Transaction.Transaction;
import com.narola.fooddelivery.location.Location;
import com.narola.fooddelivery.restaurants.Restaurant;
import com.narola.fooddelivery.review.Review;
import com.narola.fooddelivery.user.User;

public class Order {
	private int OrderId;
	private User user;
	private Restaurant restaurant;
	private int total;
	private int orderStatus;
	private Transaction transaction;
	private Location location;
	private String razorpayOrderId;
	private List<OrderItem> items;
	private Timestamp timestamp;
	private Review review;

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public Timestamp getDate() {
		return timestamp;
	}

	public void setDate(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public List<OrderItem> getItems() throws IOException {
		if (items == null)
			setItems((List<OrderItem>) OrderItemDAO.getItemsOfOrder(OrderId));
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}

	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;

	}

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
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
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int compareTo(int value1, int value2) {
		
			if(value1==value2)
				return 0;
			else if(value1>value2)
				return 1;
			else
				return -1;
		
		
	}

}
