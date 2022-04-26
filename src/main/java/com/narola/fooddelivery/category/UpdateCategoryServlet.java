package com.narola.fooddelivery.category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.URLConstantOfServlet;

/**
 * Servlet implementation class UpdateCategory_servlet
 */
@WebServlet("/UpdateCategory")
public class UpdateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.sendRedirect(request.getContextPath()+URLConstantOfServlet.ADDCATEGORY);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("categoryId")!=null && request.getParameter("categoryName")!=null) {
			Category category=CategoryDAO.getCategoryById(Integer.parseInt(request.getParameter("categoryId")));		
			category.setCategoryName(request.getParameter("categoryName"));
			category.setPopularity(request.getParameter("popularity")==null?0:1);		
			CategoryDAO.UpdateCategory(category);
		}
		
				
		doGet(request, response);
	}

}
