package com.narola.fooddelivery.dishes.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.DAOFactory;
import com.narola.fooddelivery.URLConstantAdmin;
import com.narola.fooddelivery.category.CategoryDAO;
import com.narola.fooddelivery.dishes.service.IDishService;
import com.narola.fooddelivery.dishes.service.impl.DishServiceImpl;
import com.narola.fooddelivery.user.User;

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
		String dname = request.getParameter("DishName");
		String category = request.getParameter("category");
		String dtype = request.getParameter("DishType");
		try {
			
			IDishService dishService = new DishServiceImpl();
			request=dishService.searchDish(request, response, dname, category, dtype, isFilter);
			
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
