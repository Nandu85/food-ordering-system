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

import com.narola.fooddelivery.DAOFactory;
import com.narola.fooddelivery.URLConstantAdmin;
import com.narola.fooddelivery.URLConstantOfServlet;
import com.narola.fooddelivery.location.Location;
import com.narola.fooddelivery.location.LocationDAO;

/**
 * Servlet implementation class UpdateRestaurant_servlet
 */
public class UpdateRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String referer=null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		referer=request.getHeader("referer");
		request.setAttribute("Restaurant", DAOFactory.getInstance().getRestDAO().getRestaurantFromId(Integer.parseInt(request.getParameter("RestaurantId"))));
		getServletContext().getRequestDispatcher(URLConstantAdmin.UPDATERESTAURANT_JSP).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		doGet(request, response);
//		System.out.println();
		String RestaurantId=request.getParameter("RestaurantId");
		String RestaurantName=request.getParameter("RestName");
		String email=request.getParameter("email");
		int DisableFlag=request.getParameter("Disable")==null?0:1;

		Part RestImage = request.getPart("RestPic");
		
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
	
		LocationDAO.addLocation(location);
		
		InputStream is=RestImage.getInputStream();
		byte bytes[]=new byte[is.available()];
		IOUtils.readFully(is, bytes);
		String imgToString=Base64.getEncoder().encodeToString(bytes);
		
	
		Restaurant restaurant = DAOFactory.getInstance().getRestDAO().getRestaurantFromId(Integer.parseInt(RestaurantId));
		
		restaurant.setRestName(RestaurantName);
		restaurant.setEmail(email);
		restaurant.setLocation(location);
		restaurant.setLocId(LocationDAO.getLocationId(location));
		if(!imgToString.isEmpty())
			restaurant.setRestphotoAsBase64(imgToString);
		restaurant.setDisableFlag(DisableFlag);
//		System.out.println(imgToString+" From Update Rest");
		DAOFactory.getInstance().getRestDAO().updateRestaurant(restaurant);
		
		if(referer!=null)
			response.sendRedirect(referer);
		else
			response.sendRedirect(request.getContextPath()+URLConstantOfServlet.SEARCHRESTAURANT);
		
	}

}
