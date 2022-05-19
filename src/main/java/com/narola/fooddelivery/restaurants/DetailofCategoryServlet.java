package com.narola.fooddelivery.restaurants;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.DAOFactory;
import com.narola.fooddelivery.URLConstantUser;
import com.narola.fooddelivery.category.SubCategoryDAO;
import com.narola.fooddelivery.user.User;

public class DetailofCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		if (id != null) {
			IRestaurantService restService = new RestaurantServiceImpl();
			
			req.setAttribute("SubCategory", restService.getSubCategoryById(id));
			req.setAttribute("Restaurants", restService.getRestaurantFromSubCategory(id));
			getServletContext().getRequestDispatcher(URLConstantUser.CATEGORYDETAIL_JSP).forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
