package com.narola.fooddelivery.dishes.validation;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.narola.fooddelivery.DAOFactory;
import com.narola.fooddelivery.URLConstantAdmin;
import com.narola.fooddelivery.URLConstantOfServlet;
import com.narola.fooddelivery.category.SubCategoryDAO;
import com.narola.fooddelivery.dishes.DishException;
import com.narola.fooddelivery.dishes.model.Dish;
import com.narola.fooddelivery.restaurants.dao.RestDAOMYSQL;

/**
 * Servlet Filter implementation class validateDish
 */
public class validateDish_filter implements Filter {

	public validateDish_filter() {
	}

	public void destroy() {}

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
				req.setAttribute("Restaurants", DAOFactory.getInstance().getRestDAO().getAllRestaurants());
				if(req.getRequestURI().contains(URLConstantOfServlet.ADDDISH)) {
					req.setAttribute("SubCategories", SubCategoryDAO.getAllSubCategories());
					req.setAttribute("errMsg", e.getMessage());
					req.getRequestDispatcher(URLConstantAdmin.ADDDISH_JSP).include(req, response);
				} else if(req.getRequestURI().contains(URLConstantOfServlet.UPDATEDISH)) {					
					Dish dish = DAOFactory.getInstance().getDishDAO().DishFromId(Integer.parseInt(request.getParameter("DishId")));
					req.setAttribute("Dish", dish);
					req.setAttribute("categoryOfDish", DAOFactory.getInstance().getDishDAO().CategoryFromId(DAOFactory.getInstance().getDishDAO().DishFromId(Integer.parseInt(req.getParameter("DishId"))).getCategoryId()));
					req.setAttribute("SubCategories", SubCategoryDAO.getAllSubCategories());
					req.setAttribute("errMsg", e.getMessage());
					req.getRequestDispatcher(URLConstantAdmin.UPDATEDISH_JSP).include(req, response);
				}
			}
		}
		else {
			chain.doFilter(request, response);
		}

	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
