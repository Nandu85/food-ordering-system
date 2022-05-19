package com.narola.fooddelivery.restaurants;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.DAOFactory;
import com.narola.fooddelivery.URLConstantAdmin;
import com.narola.fooddelivery.URLConstantUser;
import com.narola.fooddelivery.user.User;

/**
 * Servlet implementation class RestaurantDetail_servlet
 */
public class RestaurantDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IRestaurantService restaurantService = new RestaurantServiceImpl();
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
