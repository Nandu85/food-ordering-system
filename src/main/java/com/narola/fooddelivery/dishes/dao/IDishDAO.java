package com.narola.fooddelivery.dishes.dao;

import java.io.IOException;
import java.util.List;

import com.narola.fooddelivery.DatabaseException;
import com.narola.fooddelivery.dishes.model.Dish;

public interface IDishDAO {
	
	public Dish addDish(Dish dish) throws DatabaseException;
	
	public List<Dish> getAllDish() throws DatabaseException;
	
	public List<Dish> getAllDish(String dname,String categoryId,String dtype1) throws DatabaseException;
	
	public Dish updateDish(Dish dish) throws IOException,DatabaseException;
	
	public Dish DeletedDish(Dish dish) throws DatabaseException;
	
	public List<String> getCategories() throws DatabaseException;
	
	public String CategoryFromId(int id) throws DatabaseException;
	
	public Dish DishFromId(int id) throws IOException;
	
	public List<Dish> getRestaurantMenu(int RestaurantId) throws DatabaseException;
	
	public List<Dish> getDishesFromSubCategory(int id) throws DatabaseException;
	
	

	
	

}
