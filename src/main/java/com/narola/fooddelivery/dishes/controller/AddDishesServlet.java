package com.narola.fooddelivery.dishes.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.narola.fooddelivery.dishes.DishException;
import com.narola.fooddelivery.dishes.model.Dish;
import com.narola.fooddelivery.dishes.service.IDishService;
import com.narola.fooddelivery.dishes.service.impl.DishServiceImpl;
import com.narola.fooddelivery.utility.URLConstantAdmin;
import com.narola.fooddelivery.utility.URLConstantOfServlet;

public class AddDishesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DishServletRequest reqObject = new DishServletRequest();
		req.setAttribute("Restaurants", reqObject.getRestaurants());
		req.setAttribute("SubCategories", reqObject.getSubCategories());
		getServletContext().getRequestDispatcher(URLConstantAdmin.ADDDISH_JSP).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try {
			String dname = request.getParameter("DishName");
			String price = request.getParameter("Price");
			String ingr = request.getParameter("ingrediant");
			String category = request.getParameter("category");
			String dtype = request.getParameter("DishType");

			Part photopart;

			photopart = request.getPart("DishPic");

			Dish dish = new Dish();
			dish.setDishName(dname);
			dish.setPrice(Integer.valueOf(price));
			dish.setIngrident(ingr);
			dish.setSubcategoryId(Integer.parseInt(category));
			dish.setDishtype(Integer.parseInt(dtype));
			dish.setRestId(Integer.parseInt(request.getParameter("restaurant")));

			IDishService dishService = new DishServiceImpl();
			dishService.addDish(dish, photopart);
			response.sendRedirect(request.getContextPath() + URLConstantOfServlet.SEARCHDISH_WITH_NO_FILTER);
		} catch (DishException | IOException e) {
			request.setAttribute("errMsg", e.getMessage());
			doGet(request, response);
		}
	}
}
