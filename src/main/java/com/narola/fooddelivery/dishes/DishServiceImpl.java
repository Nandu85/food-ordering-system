package com.narola.fooddelivery.dishes;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.narola.fooddelivery.DAOFactory;
import com.narola.fooddelivery.category.SubCategoryDAO;

public class DishServiceImpl implements IDishService {

	public void addDish(HttpServletRequest request) {
		try {
			String dname = request.getParameter("DishName");
			String price = request.getParameter("Price");
			String ingr = request.getParameter("ingrediant");
			String category = request.getParameter("category");
			String dtype = request.getParameter("DishType");

			Part photopart;

			photopart = request.getPart("DishPic");
			
			InputStream file = photopart.getInputStream();

			Dish dish = new Dish();
			dish.setDishName(dname);
			dish.setPrice(Integer.valueOf(price));
			dish.setIngrident(ingr);
			dish.setPhoto(file);
			dish.setSubCategory(SubCategoryDAO.getSubCategoryById(Integer.parseInt(category)));

			dish.setCategoryId(
					SubCategoryDAO.getSubCategoryById(Integer.parseInt(category)).getCategory().getCategoryId());
			dish.setDishtype(Integer.parseInt(dtype));
			dish.setRestId(Integer.parseInt(request.getParameter("restaurant")));

			DishValidator.validate(dish);

			DAOFactory.getInstance().getDishDAO().addDish(dish);

		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
