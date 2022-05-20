package com.narola.fooddelivery.restaurants.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.location.LocationDAO;
import com.narola.fooddelivery.restaurants.service.IRestaurantService;
import com.narola.fooddelivery.restaurants.service.impl.RestaurantServiceImpl;
import com.narola.fooddelivery.user.User;
import com.narola.fooddelivery.utility.DAOFactory;
import com.narola.fooddelivery.utility.ServiceFactory;
import com.narola.fooddelivery.utility.URLConstantAdmin;
import com.narola.fooddelivery.utility.URLConstantUser;

public class SearchRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("Restaurants", DAOFactory.getInstance().getRestDAO().getAllRestaurants());
		request.setAttribute("Areas", LocationDAO.getAreas());
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

		IRestaurantService restService = ServiceFactory.getInstance().getRestaurantService();
		restService.searchRestaurants(request, restaurantName, area);
		
		User user = (User) request.getSession().getAttribute("user");
		int usertype = user.getAdmin();
		if (usertype == 1 || usertype == 2)
			getServletContext().getRequestDispatcher(URLConstantAdmin.SEARCHRESTAURANT_JSP).forward(request, response);
		else if (usertype == 0)
			getServletContext().getRequestDispatcher(URLConstantUser.SEARCHRESTAURANT_JSP).forward(request, response);
	}

}
