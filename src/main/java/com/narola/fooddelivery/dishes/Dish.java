package com.narola.fooddelivery.dishes;

import java.io.InputStream;

import com.narola.fooddelivery.DAOFactory;
import com.narola.fooddelivery.category.SubCategory;
import com.narola.fooddelivery.restaurants.RestDAO;
import com.narola.fooddelivery.restaurants.Restaurant;

public class Dish {

	private int dishId;
	private String dishName;
	private int price;
	private String ingrediant;
	private int categoryId;
	private int dishtype;
	private InputStream photo;
	private String photoAsBase64;
	private String category;
	private SubCategory subCategory;
	
	private int RestId;
	private Restaurant restaurant;
	
	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
	
	public String getCategory() {
		if(category==null)
			return DAOFactory.getInstance().getDishDAO().CategoryFromId(categoryId);
		return category;
	}
	
	public String getPhotoAsBase64() {
		return photoAsBase64;
	}
	public void setPhotoAsBase64(String photoAsBase64) {
		this.photoAsBase64 = photoAsBase64;
	}
	
	
	public int getDishId() {
		return dishId;
	}
	public void setDishId(int dishId) {
		this.dishId = dishId;
	}
	
	
	public int getDishtype() {
		return dishtype;
	}
	public void setDishtype(int dishtype) {
		this.dishtype = dishtype;
	}

	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	
	public String getIngrident() {
		return ingrediant;
	}
	public void setIngrident(String ingrediant) {
		this.ingrediant = ingrediant;
	}
	
	public InputStream getPhoto() {
		return photo;
	}
	public void setPhoto(InputStream photo) {
		this.photo = photo;
	}
	
	
	public int getRestId() {
		return RestId;
	}
	public void setRestId(int restId) {
		RestId = restId;
	}
	public Restaurant getRestaurant() {
		if(restaurant==null)
			return RestDAO.getRestaurantFromId(RestId);
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	@Override
	public String toString() {
		return "Dish [dishId=" + dishId + ", dishName=" + dishName + ", price=" + price + ", ingrediant=" + ingrediant
				+ ", categoryId=" + categoryId + ", dishtype=" + dishtype + ", photo=" + photo + ", photoAsBase64="
				+ photoAsBase64 + ", RestId=" + RestId + ", restaurant=" + restaurant + "]";
	}
	
	
	
	

}
