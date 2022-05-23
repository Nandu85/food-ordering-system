package com.narola.fooddelivery.restaurants.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.restaurants.service.IRestaurantService;
import com.narola.fooddelivery.restaurants.service.impl.RestaurantServiceImpl;
import com.narola.fooddelivery.utility.Constant;
import com.narola.fooddelivery.utility.ServiceFactory;
import com.narola.fooddelivery.utility.URLConstantUser;

public class DetailofCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		if (id != null) {
			try {
				IRestaurantService restService = ServiceFactory.getInstance().getRestaurantService();
				
				req.setAttribute("SubCategory", restService.getSubCategoryById(id));
				req.setAttribute("Restaurants", restService.getRestaurantFromSubCategory(id));
				getServletContext().getRequestDispatcher(URLConstantUser.CATEGORYDETAIL_JSP).forward(req, resp);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
				req.setAttribute("errMsg", Constant.ERR_SOMETHING_WRONG);
				getServletContext().getRequestDispatcher(URLConstantUser.CATEGORYDETAIL_JSP).forward(req, resp);
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
