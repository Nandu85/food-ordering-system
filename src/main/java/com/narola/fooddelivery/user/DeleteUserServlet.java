package com.narola.fooddelivery.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.utility.URLConstantOfServlet;

/**
 * Servlet implementation class DeleteUser_servlet
 */
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("UserId")!=null)
		UserDAO.DeleteUser(UserDAO.findByUserId(Integer.parseInt(request.getParameter("UserId"))));
		response.sendRedirect(request.getContextPath()+URLConstantOfServlet.VIEWUSER);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
