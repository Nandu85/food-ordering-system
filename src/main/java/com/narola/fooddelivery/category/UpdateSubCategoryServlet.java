package com.narola.fooddelivery.category;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.narola.fooddelivery.URLConstantOfServlet;

/**
 * Servlet implementation class UpdateSubCategoryServlet
 */
public class UpdateSubCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+URLConstantOfServlet.SUBCATEGORIES);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Id=request .getParameter("categoryId");
		String name=request .getParameter("categoryName");
		String superCategory=request.getParameter("catId");
		Part photo=request.getPart("CategoryPhoto");
		InputStream is=null;
		SubCategory cat=null;
		if(name!=null && !name.trim().isEmpty() && Id!=null && !Id.trim().isEmpty() && superCategory!=null && !superCategory.trim().isEmpty()) {
			cat=new SubCategory();
			cat.setCategoryId(Integer.parseInt(Id));
			cat.setCategory(CategoryDAO.getCategoryById(Integer.parseInt(superCategory)));
			cat.setCategoryName(name);
			
 			SubCategoryDAO.UpdateSubCategory(cat);
			
			if(photo!=null && photo.getSize()>1) {
				is=photo.getInputStream();
				SubCategoryDAO.AddImage(is, Integer.parseInt(Id));
			}
		}
		
		doGet(request, response);
	}

}
