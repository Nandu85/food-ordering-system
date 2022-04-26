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

public class DishUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		try {
			String did = request.getParameter("DishId");
			String dname = request.getParameter("DishName");
			String price = request.getParameter("Price");
			String ingr = request.getParameter("ingrediant");

			Part photopart = request.getPart("DishPic");
//			if(photopart.getSize()<=0) {
//				photopart=request.getPart("DishPic1");
//			}
			System.out.println(photopart.getContentType());
			System.out.println(photopart.getName());
			System.out.println(photopart.getSize());
			System.out.println(photopart.getSubmittedFileName());

			String category = request.getParameter("category");
			String dtype = request.getParameter("DishType");
			InputStream file;

//			if(photopart.getSize()<=0)
//				file=DishDAO.DishFromId(Integer.parseInt(did)).getPhoto();
//			else
			file = photopart.getInputStream();

			Dish dish = DishDAO.DishFromId(Integer.parseInt(did)); // new Dish();
			dish.setDishId(Integer.parseInt(did));
			dish.setDishName(dname);
			dish.setPrice(Integer.parseInt(price));
			dish.setIngrident(ingr);
			if (photopart.getSize() > 0)
				dish.setPhoto(file);

			dish.setSubCategory(SubCategoryDAO.getSubCategoryById(Integer.parseInt(category)));
			
			dish.setCategoryId(SubCategoryDAO.getSubCategoryById(Integer.parseInt(category)).getCategory().getCategoryId());
			dish.setDishtype(Integer.parseInt(dtype));
			dish.setRestId(Integer.parseInt(request.getParameter("restaurant")));

			DishValidator.validate(dish);

			DishDAO.updateDish(dish);

			response.sendRedirect(request.getContextPath() + URLConstantOfServlet.SEARCHDISH_WITH_NO_FILTER);

		} catch (Exception e2) {
			System.out.println(e2);
			e2.printStackTrace();
			request.setAttribute("Restaurants", RestDAO.getAllRestaurants());
			request.setAttribute("Dish", DishDAO.DishFromId(Integer.parseInt(request.getParameter("DishId"))));
			// request.setAttribute("categoryOfDish",
			// DishDAO.CategoryFromId(DishDAO.DishFromId(Integer.parseInt(request.getParameter("DishId"))).getCategoryId()));
			request.setAttribute("categories", DishDAO.getCategories());
			request.setAttribute("SubCategories", SubCategoryDAO.getAllSubCategories());

			request.setAttribute("errMsg", e2.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher(URLConstantAdmin.UPDATEDISH_JSP);
			dispatcher.forward(request, response);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String str = URLConstantAdmin.UPDATEDISH_JSP + "?DishId=" + String.valueOf(req.getParameter("DishId"));
		req.setAttribute("Restaurants", RestDAO.getAllRestaurants());
		req.setAttribute("SubCategories", SubCategoryDAO.getAllSubCategories());

		req.setAttribute("categories", CategoryDAO.getAllCategories());
		req.setAttribute("Dish", DishDAO.DishFromId(Integer.parseInt(req.getParameter("DishId"))));
		// req.setAttribute("categoryOfDish",
		// DishDAO.CategoryFromId(DishDAO.DishFromId(Integer.parseInt(req.getParameter("DishId"))).getCategoryId()));
		getServletContext().getRequestDispatcher(str).forward(req, resp);

	}

}
