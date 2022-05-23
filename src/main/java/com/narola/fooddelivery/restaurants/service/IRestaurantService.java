package com.narola.fooddelivery.restaurants.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.narola.fooddelivery.category.SubCategory;
import com.narola.fooddelivery.location.Location;
import com.narola.fooddelivery.restaurants.model.Restaurant;

public interface IRestaurantService {

	public void addRestaurant(Location location, Part part, Restaurant restaurant);
	
	public List<Restaurant> searchRestaurants(String restaurantName, String area);
	
	public void updateRestaurant(Location location, Part restImage,String restaurantName,String email,String restaurantId,int disableFlag);
	
	public SubCategory getSubCategoryById(String subCatId);
	
	public List<Restaurant> getRestaurantFromSubCategory(String subCatId);
	
	public Restaurant getRestaurantFromId(String restaurantId);
	
	public List<Restaurant> getRestaurants();
	
	public List<String> getAreas();
}
