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
import com.narola.fooddelivery.ServiceFactory;
import com.narola.fooddelivery.URLConstantAdmin;
import com.narola.fooddelivery.URLConstantOfServlet;
import com.narola.fooddelivery.category.CategoryDAO;
import com.narola.fooddelivery.category.SubCategoryDAO;
import com.narola.fooddelivery.dishes.model.Dish;
import com.narola.fooddelivery.dishes.service.IDishService;
import com.narola.fooddelivery.dishes.service.impl.DishServiceImpl;
import com.narola.fooddelivery.restaurants.dao.RestDAOMYSQL;

public class DishUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		try {
			String did = request.getParameter("DishId");
			String dname = request.getParameter("DishName");
			String price = request.getParameter("Price");
			String ingr = request.getParameter("ingrediant");
			String category = request.getParameter("category");
			String dtype = request.getParameter("DishType");
			String restaurantID = request.getParameter("restaurant");

			Dish dish = new Dish();

			dish.setDishId(Integer.parseInt(did));
			dish.setDishName(dname);
			dish.setPrice(Integer.parseInt(price));
			dish.setIngrident(ingr);

			dish.setSubcategoryId(Integer.parseInt(category));

			dish.setDishtype(Integer.parseInt(dtype));
			dish.setRestId(Integer.parseInt(restaurantID));

			IDishService dishService = ServiceFactory.getInstance().getDishService();
			dishService.updateDish(dish, request);

			response.sendRedirect(request.getContextPath() + URLConstantOfServlet.SEARCHDISH_WITH_NO_FILTER);

		} catch (Exception e2) {

			e2.printStackTrace();
			request.setAttribute("Restaurants", DAOFactory.getInstance().getRestDAO().getAllRestaurants());
			request.setAttribute("Dish",
					DAOFactory.getInstance().getDishDAO().DishFromId(Integer.parseInt(request.getParameter("DishId"))));
			request.setAttribute("categories", DAOFactory.getInstance().getDishDAO().getCategories());
			request.setAttribute("SubCategories", SubCategoryDAO.getAllSubCategories());

			request.setAttribute("errMsg", e2.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher(URLConstantAdmin.UPDATEDISH_JSP);
			dispatcher.forward(request, response);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String str = URLConstantAdmin.UPDATEDISH_JSP + "?DishId=" + String.valueOf(req.getParameter("DishId"));
		req.setAttribute("Restaurants", DAOFactory.getInstance().getRestDAO().getAllRestaurants());
		req.setAttribute("SubCategories", SubCategoryDAO.getAllSubCategories());

		req.setAttribute("categories", CategoryDAO.getAllCategories());
		req.setAttribute("Dish",
				DAOFactory.getInstance().getDishDAO().DishFromId(Integer.parseInt(req.getParameter("DishId"))));
		// req.setAttribute("categoryOfDish",
		// DishDAO.CategoryFromId(DishDAO.DishFromId(Integer.parseInt(req.getParameter("DishId"))).getCategoryId()));
		getServletContext().getRequestDispatcher(str).forward(req, resp);

	}

}
