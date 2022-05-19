package com.narola.fooddelivery.location;

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

/**
 * Servlet Filter implementation class ValidateLocation_filter
 */
public class ValidateLocation_filter implements Filter {

    public ValidateLocation_filter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
    	
    	if(req.getMethod().equals("POST")) {
    		try {
    			String addressline = request.getParameter("addressline");
    			String area = request.getParameter("area");
    			String city = request.getParameter("city");
    			String state = request.getParameter("state");
    			String pincode = request.getParameter("pincode");
        		
        		if(addressline == null || addressline.trim().isEmpty()) {
        			throw new LocationException("Please Enter addressline...");
        		} else if(area == null || area.trim().isEmpty()) {
        			throw new LocationException("Please Enter area...");
        		} else if(city == null || city.trim().isEmpty()) {
        			throw new LocationException("Please Enter city...");
        		} else if(state == null || state.trim().isEmpty()) {
        			throw new LocationException("Please Enter state...");
        		} else if(pincode == null || pincode.trim().isEmpty()) {
        			throw new LocationException("Please Enter pincode...");
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

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
