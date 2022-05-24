package com.narola.fooddelivery.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.narola.fooddelivery.cart.CartDAO;
import com.narola.fooddelivery.utility.URLConstantAdmin;
import com.narola.fooddelivery.utility.URLConstantUser;

/**
 * Servlet implementation class Login_servlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String referer = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		referer = request.getHeader("referer");
		getServletContext().getRequestDispatcher(URLConstantAdmin.LOGIN_JSP).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (username != null && password != null) {
				User user = UserDAO.findUser(username, password);

				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				session.setAttribute("Cart", CartDAO.GetCartfromUserId(user.getUserId()));

				if (referer != null)
					response.sendRedirect(referer);
				else if (user.getAdmin() == 1 || user.getAdmin() == 2 || user.getAdmin() == 3)
					response.sendRedirect(request.getContextPath() + URLConstantAdmin.DASHBOARD);
				else
					response.sendRedirect(request.getContextPath() + URLConstantUser.DASHBOARD);
			}

			else
				response.sendRedirect(request.getContextPath() + URLConstantUser.DASHBOARD);

		} catch (Exception e) {
			request.setAttribute("ErrMsg", e.getMessage());
			getServletContext().getRequestDispatcher(URLConstantAdmin.LOGIN_JSP).forward(request, response);
			e.printStackTrace();
		}

	}

}
