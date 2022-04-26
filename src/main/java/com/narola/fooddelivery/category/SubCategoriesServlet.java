package com.narola.fooddelivery.category;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.narola.fooddelivery.URLConstantAdmin;

/**
 * Servlet implementation class SubCategoriesServlet
 */
public class SubCategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("categories", CategoryDAO.getAllCategories());
		request.setAttribute("subCategories", SubCategoryDAO.getAllSubCategories());
		getServletContext().getRequestDispatcher(URLConstantAdmin.SUBCATEGORIES_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("subcategoryName");
		String SuperCategoryId = request.getParameter("catId");
		
		Part photo=request.getPart("CategoryPhoto");
		InputStream is=null;
		SubCategory cat=null;

		if (name != null && !name.trim().isEmpty() && SuperCategoryId != null && !SuperCategoryId.trim().isEmpty()) {
			int id = Integer.parseInt(SuperCategoryId);
			SubCategory subCat= SubCategoryDAO.AddSubCategory(name, id);
			if(photo!=null && photo.getSize()>1) {
				is=photo.getInputStream();
				SubCategoryDAO.AddImage(is, subCat.getCategoryId());
			}
		}

		doGet(request, response);
	}

}
