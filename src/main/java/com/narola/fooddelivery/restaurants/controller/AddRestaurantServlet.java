package com.narola.fooddelivery.restaurants.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.narola.fooddelivery.location.Location;
import com.narola.fooddelivery.restaurants.model.Restaurant;
import com.narola.fooddelivery.restaurants.service.IRestaurantService;
import com.narola.fooddelivery.restaurants.service.impl.RestaurantServiceImpl;
import com.narola.fooddelivery.user.User;
import com.narola.fooddelivery.utility.ServiceFactory;
import com.narola.fooddelivery.utility.URLConstantAdmin;
import com.narola.fooddelivery.utility.URLConstantOfServlet;

/**
 * Servlet implementation class AddRestaurant_servlet
 */
public class AddRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");
		int usertype = user.getAdmin();
		if (usertype == 1 || usertype == 2)
			getServletContext().getRequestDispatcher(URLConstantAdmin.ADDRESTAURANT_JSP).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String restaurantName = request.getParameter("RestName");
			String email = request.getParameter("email");

			String addressline = request.getParameter("addressline");
			String area = request.getParameter("area");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String pincode = request.getParameter("pincode");

			Location location = new Location();
			location.setAddressLine(addressline);
			location.setArea(area);
			location.setCity(city);
			location.setState(state);
			location.setPincode(Integer.parseInt(pincode));


			Restaurant restaurant = new Restaurant();
			restaurant.setRestName(restaurantName);
			restaurant.setEmail(email);

			Part part = request.getPart("RestPic");

			IRestaurantService restService = ServiceFactory.getInstance().getRestaurantService();
			restService.addRestaurant(location, part, restaurant);
			
			response.sendRedirect(request.getContextPath() + URLConstantOfServlet.SEARCHRESTAURANT);

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("ErrMsg", e.getMessage());
			getServletContext().getRequestDispatcher(URLConstantAdmin.ADDRESTAURANT_JSP).include(request, response);

		}

	}

}
