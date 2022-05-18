package com.narola.fooddelivery.restaurants;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.narola.fooddelivery.DAOFactory;
import com.narola.fooddelivery.dishes.model.Dish;
import com.narola.fooddelivery.location.Location;
import com.narola.fooddelivery.user.User;
import com.narola.fooddelivery.user.UserDAO;

public class Restaurant {
	private int RestId;
	private String RestName;
	private int Rating;
	private int LocId;
	private int DisableFlag;
	private String email;
	private String password;
	private Location location;
	private ArrayList<Dish> Menu=null;
	private ArrayList<String> Categories=null;
	private int UserId;
	private User user;
	private Timestamp timestamp;
	
	public Timestamp getTimestamp() {
		if(timestamp==null)
			setTimestamp(RestDAO.getJoinDate(RestId));
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	
	public User getUser() {
		if(user==null && UserId!=0)
			return UserDAO.findByUserId(UserId);
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
	
	
	public ArrayList<String> getCategories() {
		if(Categories==null)
			return (ArrayList<String>) RestDAO.getRestaurantCategories(RestId);
		return Categories;
	}
	public void setCategories(ArrayList<String> categories) {
		Categories = categories;
	}
	
	
	public ArrayList<Dish> getMenu() {
		if(Menu==null)
			return (ArrayList<Dish>) DAOFactory.getInstance().getDishDAO().getRestaurantMenu(RestId);
		return Menu;
	}
	public void setMenu(ArrayList<Dish> menu) {
		Menu = menu;
	}


	//	private InputStream restphoto;
	private String restphotoAsBase64;
	
	public int getRestId() {
		return RestId;
	}
	public void setRestId(int restId) {
		RestId = restId;
	}
	
	public String getRestName() {
		return RestName;
	}
	public void setRestName(String restName) {
		RestName = restName;
	}
	
	public int getRating() {
		return Rating;
	}
	public void setRating(int rating) {
		Rating = rating;
	}
	
	public int getLocId() {
		return LocId;
	}
	public void setLocId(int locId) {
		LocId = locId;
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
		return DisableFlag;
	}
	public void setDisableFlag(int disableFlag) {
		DisableFlag = disableFlag;
	}
	
	
	@Override
	public String toString() {
		return "Restaurant [RestId=" + RestId + ", RestName=" + RestName + ", Rating=" + Rating + ", LocId=" + LocId
				+ ", DisableFlag=" + DisableFlag + ", email=" + email + ", password=" + password + ", location="
				+ location + ", Menu=" + Menu + ", Categories=" + Categories + ", restphotoAsBase64="
				+ restphotoAsBase64 + "]";
	}
	

}
