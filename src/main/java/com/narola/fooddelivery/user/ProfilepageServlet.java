package com.narola.fooddelivery.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.utility.URLConstantAdmin;
import com.narola.fooddelivery.utility.URLConstantOfServlet;
import com.narola.fooddelivery.utility.URLConstantUser;

/**
 * Servlet implementation class Profilepage_servlet
 */
public class ProfilepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=(User) request.getSession().getAttribute("user");
		if(user!=null && (user.getAdmin()==1 || user.getAdmin()==2 || user.getAdmin()==3))
			getServletContext().getRequestDispatcher(URLConstantAdmin.PROFILE_JSP).forward(request, response);
		else if(user!=null && user.getAdmin()==0) {
			if(request.getParameter("transaction")!=null && request.getParameter("transaction").equals("fail"))
				request.setAttribute("ErrMsg", "Payment Fail");
			getServletContext().getRequestDispatcher(URLConstantUser.PROFILE_JSP).forward(request, response);
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		String name=request.getParameter("username");
//		String phoneNo=request.getParameter("phoneNo");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		int id=0;
		User user=null;
		if(userId!=null && !userId.trim().isEmpty())
			id = Integer.parseInt(userId);
			user=UserDAO.findByUserId(id);
			if(name!=null && !name.equals(user.getUsername()))
				user.setUsername(name);
			if(email!=null && !email.equals(user.getEmail()))
				user.setEmail(email);
			if(password!=null && !password.equals(user.getPassword()))
				user.setPassword(password);
			
			UserDAO.updateUser(user);
			request.getSession().setAttribute("user", user);
//		response.sendRedirect(URLConstantOfServlet.PROFILE);
			
		doGet(request, response);
	}

}
