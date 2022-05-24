package com.narola.fooddelivery.dishes.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.dishes.model.Dish;
import com.narola.fooddelivery.dishes.service.IDishService;
import com.narola.fooddelivery.utility.ServiceFactory;
import com.narola.fooddelivery.utility.URLConstantAdmin;
import com.narola.fooddelivery.utility.URLConstantOfServlet;

public class DishUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		try {
			String dishId = request.getParameter("DishId");
			String dishName = request.getParameter("DishName");
			String price = request.getParameter("Price");
			String ingr = request.getParameter("ingrediant");
			String category = request.getParameter("category");
			String dishType = request.getParameter("DishType");
			String restaurantID = request.getParameter("restaurant");

			Dish dish = new Dish();

			dish.setDishId(Integer.parseInt(dishId));
			dish.setDishName(dishName);
			dish.setPrice(Integer.parseInt(price));
			dish.setIngrident(ingr);

			dish.setSubcategoryId(Integer.parseInt(category));

			dish.setDishtype(Integer.parseInt(dishType));
			dish.setRestId(Integer.parseInt(restaurantID));

			IDishService dishService = ServiceFactory.getInstance().getDishService();
			dishService.updateDish(dish, request);

			response.sendRedirect(request.getContextPath() + URLConstantOfServlet.SEARCHDISH_WITH_NO_FILTER);
		} catch (Exception e2) {
			e2.printStackTrace();
			request.setAttribute("errMsg", e2.getMessage());
			doGet(request, response);

		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String str = URLConstantAdmin.UPDATEDISH_JSP + "?DishId=" + String.valueOf(req.getParameter("DishId"));
		DishServletRequest reqObject = new DishServletRequest();
		req.setAttribute("Restaurants", reqObject.getRestaurants());
		req.setAttribute("SubCategories", reqObject.getSubCategories());

		req.setAttribute("categories", reqObject.getCategories());
		req.setAttribute("Dish", reqObject.getDish(Integer.parseInt(req.getParameter("DishId"))));

		getServletContext().getRequestDispatcher(str).forward(req, resp);

	}

}
