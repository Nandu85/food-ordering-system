package com.narola.fooddelivery.restaurants.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.restaurants.service.IRestaurantService;
import com.narola.fooddelivery.restaurants.service.impl.RestaurantServiceImpl;
import com.narola.fooddelivery.user.User;
import com.narola.fooddelivery.utility.ServiceFactory;
import com.narola.fooddelivery.utility.URLConstantAdmin;
import com.narola.fooddelivery.utility.URLConstantUser;

/**
 * Servlet implementation class RestaurantDetail_servlet
 */
public class RestaurantDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IRestaurantService restaurantService = ServiceFactory.getInstance().getRestaurantService();
		String restId=request.getParameter("RestaurantId");
		if(restId!=null)
			request.setAttribute("Restaurant", restaurantService.getRestaurantFromId(restId));
		User user=(User) request.getSession().getAttribute("user");
		if(user==null || user.getAdmin()==0)
			getServletContext().getRequestDispatcher(URLConstantUser.RESTDETAIL_JSP).forward(request, response);
		
		else if(user.getAdmin()==1)
			getServletContext().getRequestDispatcher(URLConstantAdmin.RESTDETAIL_JSP).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
