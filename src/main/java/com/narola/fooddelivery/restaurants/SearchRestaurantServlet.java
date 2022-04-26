package com.narola.fooddelivery.restaurants;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.URLConstantAdmin;
import com.narola.fooddelivery.URLConstantUser;
import com.narola.fooddelivery.location.LocationDAO;
import com.narola.fooddelivery.user.User;

/**
 * Servlet implementation class SearchRestaurant_servlet
 */
public class SearchRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setAttribute("Restaurants", RestDAO.getAllRestaurants());
		request.setAttribute("Areas", LocationDAO.getAreas());
		User user=(User) request.getSession().getAttribute("user");
		if(user!=null) {
		int usertype=user.getAdmin();
		if(usertype==1||usertype==2)
			getServletContext().getRequestDispatcher(URLConstantAdmin.SEARCHRESTAURANT_JSP).forward(request, response);
		else if(usertype==0)
			getServletContext().getRequestDispatcher(URLConstantUser.SEARCHRESTAURANT_JSP).forward(request, response);
		}
		else
			getServletContext().getRequestDispatcher(URLConstantUser.SEARCHRESTAURANT_JSP).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String RestaurantName = request.getParameter("RestaurantName");
		String Area = request.getParameter("Area");

		if(!RestaurantName.equals(""))
			request.setAttribute("Restaurants", RestDAO.searchRestaurantFromName(RestaurantName));
		else if(!Area.equals(""))
			request.setAttribute("Restaurants", RestDAO.searchRestaurantFromArea(Area));
		else
			request.setAttribute("Restaurants", RestDAO.getAllRestaurants());
		request.setAttribute("Areas", LocationDAO.getAreas());
		User user=(User) request.getSession().getAttribute("user");
		int usertype=user.getAdmin();
		if(usertype==1)
			getServletContext().getRequestDispatcher(URLConstantAdmin.SEARCHRESTAURANT_JSP).forward(request, response);
		else if(usertype==0)
			getServletContext().getRequestDispatcher(URLConstantUser.SEARCHRESTAURANT_JSP).forward(request, response);
	}

}
