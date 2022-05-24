package com.narola.fooddelivery.restaurants.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.restaurants.service.IRestaurantService;
import com.narola.fooddelivery.user.User;
import com.narola.fooddelivery.utility.Constant;
import com.narola.fooddelivery.utility.ServiceFactory;
import com.narola.fooddelivery.utility.URLConstantAdmin;
import com.narola.fooddelivery.utility.URLConstantUser;

public class SearchRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IRestaurantService service = ServiceFactory.getInstance().getRestaurantService();
		request.setAttribute("Restaurants", service.getRestaurants());
		request.setAttribute("Areas", service.getAreas());
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			int usertype = user.getAdmin();
			if (usertype == 1 || usertype == 2)
				getServletContext().getRequestDispatcher(URLConstantAdmin.SEARCHRESTAURANT_JSP).forward(request,
						response);
			else if (usertype == 0)
				getServletContext().getRequestDispatcher(URLConstantUser.SEARCHRESTAURANT_JSP).forward(request,
						response);
		} else
			getServletContext().getRequestDispatcher(URLConstantUser.SEARCHRESTAURANT_JSP).forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String restaurantName = request.getParameter("RestaurantName");
		String area = request.getParameter("Area");

		try {
			IRestaurantService restService = ServiceFactory.getInstance().getRestaurantService();
			restService.searchRestaurants(restaurantName, area);
			request.setAttribute("Areas", restService.getAreas());
		} catch (Exception e) {
			request.setAttribute("errMsg", Constant.ERR_SOMETHING_WRONG);
		}
		User user = (User) request.getSession().getAttribute("user");
		int usertype = user.getAdmin();
		if (usertype == 1 || usertype == 2)
			getServletContext().getRequestDispatcher(URLConstantAdmin.SEARCHRESTAURANT_JSP).forward(request, response);
		else if (usertype == 0)
			getServletContext().getRequestDispatcher(URLConstantUser.SEARCHRESTAURANT_JSP).forward(request, response);
	}

}
