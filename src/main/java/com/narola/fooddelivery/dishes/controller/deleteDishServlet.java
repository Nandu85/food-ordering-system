package com.narola.fooddelivery.dishes.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.DAOFactory;
import com.narola.fooddelivery.URLConstantOfServlet;
import com.narola.fooddelivery.dishes.service.IDishService;
import com.narola.fooddelivery.dishes.service.impl.DishServiceImpl;

/**
 * Servlet implementation class deleteDish
 */
public class deleteDishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String did = request.getParameter("DishId");

		IDishService dishService = new DishServiceImpl();

		dishService.deleteDish(did);

		response.sendRedirect(request.getContextPath() + URLConstantOfServlet.SEARCHDISH_WITH_NO_FILTER);

	}

}
