package com.narola.fooddelivery;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.category.CategoryDAO;
import com.narola.fooddelivery.category.SubCategoryDAO;
import com.narola.fooddelivery.dishes.dao.DishDAOMYSQL;

/**
 * Servlet implementation class DashboardUser_servlet
 */
@WebServlet("/UserHomePage")
public class UserHomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("SubCategories", SubCategoryDAO.getAllSubCategories());
		request.setAttribute("Categories", CategoryDAO.getPopularCategories());
		request.getRequestDispatcher(URLConstantUser.DASHBOARD).include(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
