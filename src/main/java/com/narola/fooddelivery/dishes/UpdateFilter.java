package com.narola.fooddelivery.dishes;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class UpdateFilter
 */
public class UpdateFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		if (req.getMethod().equals("POST")) {
			try {
				String dname = request.getParameter("DishName");
				String price = request.getParameter("Price");
				String ingr = request.getParameter("ingrediant");
				String category = request.getParameter("category");
				String dtype = request.getParameter("DishType");

				DishValidator.validate(dname, price, ingr, category, dtype);
				chain.doFilter(request, response);

			} catch (DishException e) {
				request.setAttribute("errMsg", e.getMessage());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/UpdateDish.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}

	}

}
