package com.narola.fooddelivery.restaurants;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.URLConstantAdmin;
import com.narola.fooddelivery.URLConstantUser;
import com.narola.fooddelivery.user.User;

/**
 * Servlet implementation class RestaurantDetail_servlet
 */
public class RestaurantDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("RestaurantId")!=null)
			request.setAttribute("Restaurant", RestDAO.getRestaurantFromId(Integer.parseInt(request.getParameter("RestaurantId"))));
//		request.setAttribute("dishList", DishDAO.getRestaurantMenu(Integer.parseInt(request.getParameter("RestaurantId"))));
		User user=(User) request.getSession().getAttribute("user");
		if(user==null || user.getAdmin()==0)
			getServletContext().getRequestDispatcher(URLConstantUser.RESTDETAIL_JSP).forward(request, response);
		
		else if(user.getAdmin()==1)
			getServletContext().getRequestDispatcher(URLConstantAdmin.RESTDETAIL_JSP).forward(request, response);
		//getServletContext().getRequestDispatcher(URLConstant_Admin.RESTDETAIL_JSP).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
