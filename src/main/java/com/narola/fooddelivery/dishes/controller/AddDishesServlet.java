package com.narola.fooddelivery.dishes.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.narola.fooddelivery.DAOFactory;
import com.narola.fooddelivery.URLConstantAdmin;
import com.narola.fooddelivery.URLConstantOfServlet;
import com.narola.fooddelivery.category.SubCategoryDAO;
import com.narola.fooddelivery.dishes.DishException;
import com.narola.fooddelivery.dishes.service.IDishService;
import com.narola.fooddelivery.dishes.service.impl.DishServiceImpl;
import com.narola.fooddelivery.restaurants.RestDAOMYSQL;


public class AddDishesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("Restaurants", DAOFactory.getInstance().getRestDAO().getAllRestaurants());

		req.setAttribute("SubCategories", SubCategoryDAO.getAllSubCategories());;
		getServletContext().getRequestDispatcher(URLConstantAdmin.ADDDISH_JSP).forward(req, resp);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");		
		
		
		
		try {			
			
			IDishService dishService = new DishServiceImpl();
			
			dishService.addDish(request);
			
			response.sendRedirect(request.getContextPath()+URLConstantOfServlet.SEARCHDISH_WITH_NO_FILTER);
			
		} catch (DishException e) {
			request.setAttribute("categories", DAOFactory.getInstance().getDishDAO().getCategories());
			request.setAttribute("SubCategories", SubCategoryDAO.getAllSubCategories());
			request.setAttribute("Restaurants", DAOFactory.getInstance().getRestDAO().getAllRestaurants());
			request.setAttribute("errMsg", e.getMessage());
			doGet(request, response);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(URLConstantAdmin.ADDDISH_JSP);
			dispatcher.include(request, response);
		}
		
	}

}
