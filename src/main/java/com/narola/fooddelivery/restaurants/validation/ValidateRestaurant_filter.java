package com.narola.fooddelivery.restaurants.validation;

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
import com.narola.fooddelivery.restaurants.RestaurantException;

/**
 * Servlet Filter implementation class ValidateRestaurant_filter
 */
public class ValidateRestaurant_filter implements Filter {

    public ValidateRestaurant_filter() {}
    
    public void destroy() {}
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	
    	HttpServletRequest req=(HttpServletRequest)request;
    	
    	if(req.getMethod().equals("POST")) {
    		try {
    			String restId=request.getParameter("RestaurantId");
    			String restaurantName = request.getParameter("RestName");
        		String email = request.getParameter("email");
        		
        		if(restaurantName == null || restaurantName.trim().isEmpty()) {
        			throw new RestaurantException("Please Enter Restaurant Name...");
        		} else if(email == null || email.trim().isEmpty()) {
        			throw new RestaurantException("Please Enter email...");
        		}
    			chain.doFilter(request, response);

        		
        	}
        	catch (Exception e) {
        		req.setAttribute("ErrMsg", e.getMessage());
        		if(req.getRequestURI().contains(URLConstantOfServlet.ADDRESTAURANT)) {
        			req.getRequestDispatcher(URLConstantAdmin.ADDRESTAURANT_JSP).include(req, response);
        		}
        			
        		else if(req.getRequestURI().contains(URLConstantOfServlet.UPDATERESTAURANT)) {
        			req.setAttribute("Restaurant", DAOFactory.getInstance().getRestDAO().getRestaurantFromId(Integer.parseInt(request.getParameter("RestaurantId"))));
        			req.getRequestDispatcher(URLConstantAdmin.UPDATERESTAURANT_JSP).include(req, response);
        		}
        			

    		}
    		
        	
    	}else {
    			chain.doFilter(request, response);
    	}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {}

}
