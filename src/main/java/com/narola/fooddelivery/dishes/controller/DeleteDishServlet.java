package com.narola.fooddelivery.dishes.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.dishes.DishException;
import com.narola.fooddelivery.dishes.service.IDishService;
import com.narola.fooddelivery.dishes.service.impl.DishServiceImpl;
import com.narola.fooddelivery.utility.DAOFactory;
import com.narola.fooddelivery.utility.ServiceFactory;
import com.narola.fooddelivery.utility.URLConstantOfServlet;

/**
 * Servlet implementation class deleteDish
 */
public class DeleteDishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String did = request.getParameter("DishId");

			IDishService dishService = ServiceFactory.getInstance().getDishService();

			dishService.deleteDish(did);

			response.sendRedirect(request.getContextPath() + URLConstantOfServlet.SEARCHDISH_WITH_NO_FILTER);
		} catch (IOException e) {
			new DishException("Something ");
		}

	}

}
