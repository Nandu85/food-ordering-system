package com.narola.fooddelivery.restaurants;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.DAOFactory;
import com.narola.fooddelivery.URLConstantAdmin;
import com.narola.fooddelivery.URLConstantUser;
import com.narola.fooddelivery.location.LocationDAO;
import com.narola.fooddelivery.user.User;

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

		IRestaurantService restService = new RestaurantServiceImpl();
		restService.searchRestaurants(request, restaurantName, area);
		
		User user = (User) request.getSession().getAttribute("user");
		int usertype = user.getAdmin();
		if (usertype == 1 || usertype == 2)
			getServletContext().getRequestDispatcher(URLConstantAdmin.SEARCHRESTAURANT_JSP).forward(request, response);
		else if (usertype == 0)
			getServletContext().getRequestDispatcher(URLConstantUser.SEARCHRESTAURANT_JSP).forward(request, response);
	}

}
