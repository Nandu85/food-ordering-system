package com.narola.fooddelivery.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.restaurants.model.Restaurant;
import com.narola.fooddelivery.utility.DAOFactory;
import com.narola.fooddelivery.utility.URLConstantAdmin;
import com.narola.fooddelivery.utility.URLConstantOfServlet;

/**
 * Servlet implementation class UpdateUser_servlet
 */
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("Restaurants", DAOFactory.getInstance().getRestDAO().getAllRestaurants());
		if(request.getParameter("UserId")!=null) {
			request.setAttribute("user", UserDAO.findByUserId(Integer.parseInt(request.getParameter("UserId"))));
			getServletContext().getRequestDispatcher(URLConstantAdmin.UPDATEUSER_JSP).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		if(request.getParameter("UserId")!=null && username!=null && !username.trim().isEmpty() && email!=null && !email.trim().isEmpty() && password!=null && !password.trim().isEmpty()) {
			int UserId=Integer.parseInt(request.getParameter("UserId"));
			User user=UserDAO.findByUserId(UserId);	
			user.setEmail(email);
			user.setPassword(password);
			user.setUsername(username);
			UserDAO.updateUser(user);
			
			if(user.getAdmin()==3) {
				Restaurant restaurant=DAOFactory.getInstance().getRestDAO().getRestaurantFromId(Integer.parseInt(request.getParameter("Restaurant")));
				restaurant.setUserId(UserId);
				DAOFactory.getInstance().getRestDAO().setRestaurantAdmin(restaurant);
				user.setRestaurantId(UserId);
			}
		}
		
		response.sendRedirect(request.getContextPath()+URLConstantOfServlet.VIEWUSER);
		
	}

}
