package com.narola.fooddelivery.restaurants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.narola.fooddelivery.URLConstantAdmin;
import com.narola.fooddelivery.URLConstantOfServlet;
import com.narola.fooddelivery.location.Location;
import com.narola.fooddelivery.location.LocationDAO;
import com.narola.fooddelivery.user.User;

/**
 * Servlet implementation class AddRestaurant_servlet
 */
public class AddRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user=(User) request.getSession().getAttribute("user");
		int usertype=user.getAdmin();
		if(usertype==1||usertype==2)
			getServletContext().getRequestDispatcher(URLConstantAdmin.ADDRESTAURANT_JSP).forward(request, response);
//		else if(usertype==0)
//			getServletContext().getRequestDispatcher(URLConstant_Admin.ADDRESTAURANT_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String restaurantName = request.getParameter("RestName");
			String email = request.getParameter("email");
//			String DishPic = request.getParameter("DishPic");
			String addressline = request.getParameter("addressline");
			String area = request.getParameter("area");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String pincode = request.getParameter("pincode");

			//			System.out.println(DisableFlag);
			Location location = new Location();
			location.setAddressLine(addressline);
			location.setArea(area);
			location.setCity(city);
			location.setState(state);
			location.setPincode(Integer.parseInt(pincode));
//			System.out.println(location.toString());
			LocationDAO.addLocation(location);
			
			Part part = request.getPart("RestPic");
			InputStream is = part.getInputStream();
			byte[] bytes = new byte[is.available()];
			
			IOUtils.readFully(is, bytes);
			
			String imageAsBase64=Base64.getEncoder().encodeToString(bytes);
			
			Restaurant restaurant = new Restaurant();
			restaurant.setRestName(restaurantName);
			restaurant.setEmail(email);
			restaurant.setRestphotoAsBase64(imageAsBase64);
			restaurant.setLocId(location.getLocationId());

			RestDAO.addRestaurant(restaurant);
			response.sendRedirect(request.getContextPath()+URLConstantOfServlet.SEARCHRESTAURANT);
//			getServletContext().getRequestDispatcher(URLConstant.SEARCHRESTAURANT).forward(request, response);
			
		}catch (Exception e) {
			//RequestDispatcher rd = 
			e.printStackTrace();
			request.setAttribute("ErrMsg", e.getMessage());
			getServletContext().getRequestDispatcher(URLConstantAdmin.ADDRESTAURANT_JSP).include(request, response);
			
		}
		
		
	}

}
