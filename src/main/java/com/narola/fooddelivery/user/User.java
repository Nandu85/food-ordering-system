package com.narola.fooddelivery.user;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.narola.fooddelivery.Constant;
import com.narola.fooddelivery.DAOFactory;
import com.narola.fooddelivery.location.Location;
import com.narola.fooddelivery.location.LocationDAO;
import com.narola.fooddelivery.order.Order;
import com.narola.fooddelivery.order.OrderDAO;
import com.narola.fooddelivery.restaurants.dao.RestDAOMYSQL;
import com.narola.fooddelivery.restaurants.model.Restaurant;

public class User {

	private int userId;
	private String username;
	private String email;
	private String password;
	private int admin;
	private int RestaurantId;
	private Restaurant restaurant;
	private ArrayList<Location> locations;
	private String encryptedPass;
	private List<Order> orders;
	
	public List<Order> getOrders() {
		return OrderDAO.getOrderofUser(userId);
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public String getEncryptedPass() {
		if(encryptedPass==null)
			setEncryptedPass(Base64.getEncoder().encodeToString(this.password.getBytes()));
		return encryptedPass;
	}
	public void setEncryptedPass(String encryptedPass) {
		this.encryptedPass = encryptedPass;
	}
	public ArrayList<Location> getLocations() {
		setLocations((ArrayList<Location>) LocationDAO.getLocationFromuserId(userId));
		return locations;
	}
	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}
	public int getRestaurantId() {
		return RestaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		RestaurantId = restaurantId;
	}
	
	public Restaurant getRestaurant() {
		if(restaurant==null && RestaurantId!=0)
			return DAOFactory.getInstance().getRestDAO().getRestaurantFromId(RestaurantId);
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	
	
//	@Override
//	public String toString() {
//		return "User [userId=" + userId + ", username=" + username + ", email=" + email + ", password=" + password
//				+ ", admin=" + admin + ", RestaurantId=" + RestaurantId + ", restaurant=" + restaurant + "]";
//	}
//	
	
}
