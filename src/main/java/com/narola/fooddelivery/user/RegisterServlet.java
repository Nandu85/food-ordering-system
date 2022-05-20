package com.narola.fooddelivery.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.utility.URLConstantAdmin;
import com.narola.fooddelivery.utility.URLConstantOfServlet;

/**
 * Servlet implementation class Register_servlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(URLConstantAdmin.REGISTER_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
//		System.out.println(request.getParameter("username"));
		if(username!=null && email!=null && password!=null) {
			User user=new User();
			user.setAdmin(0);
			user.setEmail(email);
			user.setPassword(password);
			user.setUsername(username);
			
			UserDAO.addUser(user);
		}
		
		
		getServletContext().getRequestDispatcher(URLConstantOfServlet.LOGIN).forward(request, response);

	
	}

}
