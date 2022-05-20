package com.narola.fooddelivery.category;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.utility.URLConstantOfServlet;

/**
 * Servlet implementation class DeleteCategory_servlet
 */
@WebServlet(urlPatterns ={"/DeleteCategory","/DeleteSubCategory"})

public class DeleteCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getRequestURI().contains("DeleteCategory")) {
			if(request.getParameter("Id")!=null)
				CategoryDAO.deleteCategoryById(Integer.parseInt(request.getParameter("Id")));
			response.sendRedirect(request.getContextPath() + URLConstantOfServlet.ADDCATEGORY);
		}
		else if(request.getRequestURI().contains("DeleteSubCategory")) {
			if(request.getParameter("Id")!=null)
				SubCategoryDAO.deleteSubCategoryById(Integer.parseInt(request.getParameter("Id")));
			response.sendRedirect(request.getContextPath() + URLConstantOfServlet.SUBCATEGORIES);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
