package com.narola.fooddelivery.restaurants.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.narola.fooddelivery.dishes.model.Dish;
import com.narola.fooddelivery.location.Location;
import com.narola.fooddelivery.user.User;
import com.narola.fooddelivery.user.UserDAO;
import com.narola.fooddelivery.utility.DAOFactory;

public class Restaurant {
	private int restaurantId;
	private String restaurantName;
	private int rating;
	private int locationId;
	private int disableFlag;
	private String email;
	private String password;
	private Location location;
	private ArrayList<Dish> restaurantMenu = null;
	private ArrayList<String> categoriesList = null;
	private int restaurantUserId;
	private User user;
	private Timestamp timestamp;

	public Timestamp getTimestamp() {
		if (timestamp == null)
			setTimestamp(DAOFactory.getInstance().getRestDAO().getJoinDate(restaurantId));
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getUserId() {
		return restaurantUserId;
	}

	public void setUserId(int userId) {
		restaurantUserId = userId;
	}

	public User getUser() {
		if (user == null && restaurantUserId != 0)
			return UserDAO.findByUserId(restaurantUserId);
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getCategories() {
		if (categoriesList == null)
			return (ArrayList<String>) DAOFactory.getInstance().getRestDAO().getRestaurantCategories(restaurantId);
		return categoriesList;
	}

	public void setCategories(ArrayList<String> categories) {
		categoriesList = categories;
	}

	public List<Dish> getMenu() {
		if (restaurantMenu == null)
			return (ArrayList<Dish>) DAOFactory.getInstance().getDishDAO().getRestaurantMenu(restaurantId);
		return restaurantMenu;
	}

	public void setMenu(ArrayList<Dish> menu) {
		restaurantMenu = menu;
	}

	private String restphotoAsBase64;

	public int getRestId() {
		return restaurantId;
	}

	public void setRestId(int restId) {
		restaurantId = restId;
	}

	public String getRestName() {
		return restaurantName;
	}

	public void setRestName(String restName) {
		restaurantName = restName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating1) {
		rating = rating1;
	}

	public int getLocId() {
		return locationId;
	}

	public void setLocId(int locId) {
		locationId = locId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRestphotoAsBase64() {
		return restphotoAsBase64;
	}

	public void setRestphotoAsBase64(String restphotoAsBase64) {
		this.restphotoAsBase64 = restphotoAsBase64;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getDisableFlag() {
		return disableFlag;
	}

	public void setDisableFlag(int disableFlag1) {
		disableFlag = disableFlag1;
	}

	@Override
	public String toString() {
		return "Restaurant [RestId=" + restaurantId + ", RestName=" + restaurantName + ", Rating=" + rating + ", LocId="
				+ locationId + ", DisableFlag=" + disableFlag + ", email=" + email + ", password=" + password
				+ ", location=" + location + ", Menu=" + restaurantMenu + ", Categories=" + categoriesList
				+ ", restphotoAsBase64=" + restphotoAsBase64 + "]";
	}

}
