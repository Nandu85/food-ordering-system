package com.narola.fooddelivery.dishes.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.dishes.model.Dish;
import com.narola.fooddelivery.dishes.service.IDishService;
import com.narola.fooddelivery.utility.ServiceFactory;
import com.narola.fooddelivery.utility.URLConstantAdmin;

/**
 * Servlet implementation class SearchingDish
 */
public class SearchingDishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String isFilter = request.getParameter("isfilter"); 
		String dishName = request.getParameter("DishName");
		String category = request.getParameter("category");
		String dishType = request.getParameter("DishType");
		try {			
			IDishService dishService = ServiceFactory.getInstance().getDishService();
			List<Dish> dishList=dishService.searchDish(request, response, dishName, category, dishType, isFilter);
			request.setAttribute("dishList", dishList);
			request.setAttribute("categories", dishService.getCategories());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(URLConstantAdmin.SEARCHDISH_JSP);
			dispatcher.forward(request, response);
		} catch (Exception e2) {
			e2.printStackTrace();	
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
