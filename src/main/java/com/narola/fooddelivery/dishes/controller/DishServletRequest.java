package com.narola.fooddelivery.dishes.controller;

import java.io.IOException;
import java.util.List;

import com.narola.fooddelivery.category.Category;
import com.narola.fooddelivery.category.CategoryDAO;
import com.narola.fooddelivery.category.SubCategory;
import com.narola.fooddelivery.category.SubCategoryDAO;
import com.narola.fooddelivery.dishes.model.Dish;
import com.narola.fooddelivery.restaurants.model.Restaurant;
import com.narola.fooddelivery.utility.DAOFactory;

public class DishServletRequest {
	private List<Restaurant> restaurants = null;
	private List<Category> categories = null;
	private List<SubCategory> subCategories = null;
	private Dish dish = null;
	/**
	 * @return the dish
	 */
	public Dish getDish(int dishId) {
		try {
			return DAOFactory.getInstance().getDishDAO().DishFromId(dishId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @param dish the dish to set
	 */
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	/**
	 * @return the restaurants
	 */
	public List<Restaurant> getRestaurants() {
		return DAOFactory.getInstance().getRestDAO().getAllRestaurants();
	}
	/**
	 * @param restaurants the restaurants to set
	 */
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	/**
	 * @return the categories
	 */
	public List<Category> getCategories() {
		return CategoryDAO.getAllCategories();
	}
	/**
	 * @param categories the categories to set
	 */
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	/**
	 * @return the subCategories
	 */
	public List<SubCategory> getSubCategories() {
		return SubCategoryDAO.getAllSubCategories();
	}
	/**
	 * @param subCategories the subCategories to set
	 */
	public void setSubCategories(List<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}

}
