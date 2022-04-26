package com.narola.fooddelivery.location;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.URLConstantOfServlet;
import com.narola.fooddelivery.URLConstantUser;
import com.narola.fooddelivery.user.User;

/**
 * Servlet implementation class AddLocationServlet
 */
public class AddLocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(URLConstantUser.ADDLOCATION_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String addressline = request.getParameter("addressline");
		String area = request.getParameter("area");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String pincode = request.getParameter("pincode");
		
		String LocId=request.getParameter("LocId");
		Location location=null;
		if(addressline!=null && !addressline.trim().isEmpty() && area!=null && !area.trim().isEmpty() && city!=null && !city.trim().isEmpty() && state!=null && !state.trim().isEmpty() && pincode!=null && !pincode.trim().isEmpty()) {
			location = new Location();
			if(LocId!=null && !LocId.trim().isEmpty())
				location=LocationDAO.getLocationFromId(Integer.parseInt(LocId));
			else if(!request.getRequestURI().contains(URLConstantOfServlet.ADDLOCATION)) {
				throw new LocationException("LocId Not Entered");
			}
			location.setAddressLine(addressline);
			location.setArea(area);
			location.setCity(city);
			location.setState(state);
			location.setPincode(Integer.parseInt(pincode));
			location.setUser((User) request.getSession().getAttribute("user"));
			
			if(LocId!=null && !LocId.trim().isEmpty())
				LocationDAO.UpdateLocation(location);
			else {
				LocationDAO.addLocation(location);
				LocationDAO.setUserLocation(location);
			}
			
			
		}
		
		response.sendRedirect(request.getContextPath()+URLConstantOfServlet.PROFILE);
	}

}
