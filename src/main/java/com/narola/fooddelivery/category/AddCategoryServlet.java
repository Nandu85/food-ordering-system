package com.narola.fooddelivery.category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.exception.DatabaseException;
import com.narola.fooddelivery.utility.URLConstantAdmin;
import com.narola.fooddelivery.utility.URLConstantOfServlet;


@WebServlet("/AddCategory")
public class AddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("categories", CategoryDAO.getAllCategories());
		request.getRequestDispatcher(URLConstantAdmin.ADDCATEGORY_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryName = request.getParameter("categoryName");
		int popularity = request.getParameter("popularity") == "on" ? 0 : 1;
		if (categoryName != null && !categoryName.trim().isEmpty()) {
			try {
				CategoryDAO.AddCategory(categoryName, popularity);
			} catch (DatabaseException e) {
				e.printStackTrace();
				request.setAttribute("errMsg", "Something went wrong. please try after sometime");
				request.getRequestDispatcher(URLConstantAdmin.ADDCATEGORY_JSP).forward(request, response);
				return;
			}
		}
		else {
			request.setAttribute("errMsg", "Please enter Category Name");
		}
		
		
		response.sendRedirect(request.getContextPath() + URLConstantOfServlet.ADDCATEGORY);
	}

}
