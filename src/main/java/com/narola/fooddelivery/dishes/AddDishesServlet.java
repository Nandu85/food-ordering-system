package com.narola.fooddelivery.dishes;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.narola.fooddelivery.URLConstantAdmin;
import com.narola.fooddelivery.URLConstantOfServlet;
import com.narola.fooddelivery.category.CategoryDAO;
import com.narola.fooddelivery.category.SubCategoryDAO;
import com.narola.fooddelivery.restaurants.RestDAO;


public class AddDishesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("Restaurants", RestDAO.getAllRestaurants());
//		req.setAttribute("categories", CategoryDAO.getAllCategories());
		req.setAttribute("SubCategories", SubCategoryDAO.getAllSubCategories());;
		getServletContext().getRequestDispatcher(URLConstantAdmin.ADDDISH_JSP).forward(req, resp);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");		
		
		String dname = request.getParameter("DishName");
		String price = request.getParameter("Price");
		String ingr = request.getParameter("ingrediant");
		
		Part photopart=request.getPart("DishPic");
		System.out.println(photopart.getContentType());
		System.out.println(photopart.getName());
		System.out.println(photopart.getSize());
		System.out.println(photopart.getSubmittedFileName());
		
		String category = request.getParameter("category");
		String dtype = request.getParameter("DishType");
		
		InputStream file=photopart.getInputStream();
		
		try {			
			
			Dish dish = new Dish();
			dish.setDishName(dname);
			dish.setPrice(Integer.valueOf(price));
			dish.setIngrident(ingr);
			dish.setPhoto(file);
			dish.setSubCategory(SubCategoryDAO.getSubCategoryById(Integer.parseInt(category)));
			
			dish.setCategoryId(SubCategoryDAO.getSubCategoryById(Integer.parseInt(category)).getCategory().getCategoryId());
			dish.setDishtype(Integer.parseInt(dtype));
			dish.setRestId(Integer.parseInt(request.getParameter("restaurant")));
			
			DishValidator.validate(dish);
			
			DishDAO.addDish(dish);
			
			response.sendRedirect(request.getContextPath()+URLConstantOfServlet.SEARCHDISH_WITH_NO_FILTER);
			
		} catch (DishException e) {
			request.setAttribute("categories", DishDAO.getCategories());
			request.setAttribute("SubCategories", SubCategoryDAO.getAllSubCategories());
			request.setAttribute("Restaurants", RestDAO.getAllRestaurants());
			request.setAttribute("errMsg", e.getMessage());
			doGet(request, response);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(URLConstantAdmin.ADDDISH_JSP);
			dispatcher.include(request, response);
		}
		
	}

}