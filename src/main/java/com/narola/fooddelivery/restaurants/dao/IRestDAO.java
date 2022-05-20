package com.narola.fooddelivery.restaurants.dao;

import java.sql.Timestamp;
import java.util.List;

import com.narola.fooddelivery.exception.DatabaseException;
import com.narola.fooddelivery.restaurants.model.Restaurant;

public interface IRestDAO {
	public Restaurant addRestaurant(Restaurant restaurant) throws DatabaseException;

	public List<Restaurant> searchRestaurantFromName(String restaurantName) throws DatabaseException;

	public List<Restaurant> searchRestaurantFromArea(String area) throws DatabaseException;

	public List<Restaurant> getAllRestaurants() throws DatabaseException;

	public Restaurant getRestaurantFromId(int id) throws DatabaseException;

	public Restaurant updateRestaurant(Restaurant restaurant) throws DatabaseException;

	public Restaurant setRestaurantAdmin(Restaurant restaurant) throws DatabaseException;

	public List<String> getRestaurantCategories(int restId) throws DatabaseException;

	public Restaurant getRestaurantFromUserId(int id) throws DatabaseException;

	public List<Restaurant> getRestaurantsFromSubCategory(int id) throws DatabaseException;
	
	public Timestamp getJoinDate(int restId);

}
