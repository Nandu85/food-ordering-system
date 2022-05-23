package com.narola.fooddelivery.restaurants.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.narola.fooddelivery.location.Location;
import com.narola.fooddelivery.restaurants.service.IRestaurantService;
import com.narola.fooddelivery.restaurants.service.impl.RestaurantServiceImpl;
import com.narola.fooddelivery.utility.Constant;
import com.narola.fooddelivery.utility.DAOFactory;
import com.narola.fooddelivery.utility.ServiceFactory;
import com.narola.fooddelivery.utility.URLConstantAdmin;
import com.narola.fooddelivery.utility.URLConstantOfServlet;

/**
 * Servlet implementation class UpdateRestaurant_servlet
 */
public class UpdateRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String referer=null;
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		referer=request.getHeader("referer");
		IRestaurantService service = ServiceFactory.getInstance().getRestaurantService();
		request.setAttribute("Restaurant", service.getRestaurantFromId(request.getParameter("RestaurantId")));
		getServletContext().getRequestDispatcher(URLConstantAdmin.UPDATERESTAURANT_JSP).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String restaurantId=request.getParameter("RestaurantId");
			String restaurantName=request.getParameter("RestName");
			String email=request.getParameter("email");
			int disableFlag=request.getParameter("Disable")==null?0:1;
			
			String addressline=request.getParameter("addressline");
			String area=request.getParameter("area");
			String city=request.getParameter("city");
			String state=request.getParameter("state");
			String pincode=request.getParameter("pincode");
			
			Location location = new Location();
			location.setAddressLine(addressline);
			location.setArea(area);
			location.setCity(city);
			location.setState(state);
			location.setPincode(Integer.parseInt(pincode));

			Part restImage = request.getPart("RestPic");
			
			IRestaurantService restService = ServiceFactory.getInstance().getRestaurantService();
			restService.updateRestaurant(location, restImage, restaurantName, email, restaurantId, disableFlag);
			
			if(referer!=null)
				response.sendRedirect(referer);
			else
				response.sendRedirect(request.getContextPath()+URLConstantOfServlet.SEARCHRESTAURANT);
		} catch (NumberFormatException|IOException|ServletException e) {
			e.printStackTrace();
			request.setAttribute("errMsg", Constant.ERR_SOMETHING_WRONG);
			doGet(request, response);
		}
		
	}

}
