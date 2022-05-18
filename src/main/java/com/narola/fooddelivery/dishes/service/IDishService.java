package com.narola.fooddelivery.dishes.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.dishes.model.Dish;

public interface IDishService {

	public void addDish(HttpServletRequest request);
	
	public void updateDish(Dish dish, HttpServletRequest request);
	
	public HttpServletRequest searchDish(HttpServletRequest request, HttpServletResponse response, String dname, String categoryId,
			String dtype1, String isFilter) throws ServletException, IOException;
	
	public void deleteDish(String dishId);
}
