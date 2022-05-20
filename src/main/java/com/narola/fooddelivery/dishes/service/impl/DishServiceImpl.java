package com.narola.fooddelivery.dishes.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.narola.fooddelivery.category.CategoryDAO;
import com.narola.fooddelivery.category.SubCategoryDAO;
import com.narola.fooddelivery.dishes.DishException;
import com.narola.fooddelivery.dishes.model.Dish;
import com.narola.fooddelivery.dishes.service.IDishService;
import com.narola.fooddelivery.dishes.validation.DishValidator;
import com.narola.fooddelivery.exception.DatabaseException;
import com.narola.fooddelivery.user.User;
import com.narola.fooddelivery.utility.DAOFactory;

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
			e.printStackTrace();
		}
	}

	public void updateDish(Dish dish, HttpServletRequest request) {
		try {
			dish.setCategoryId(
					SubCategoryDAO.getSubCategoryById(dish.getSubcategoryId()).getCategory().getCategoryId());
			Part photopart;
			photopart = request.getPart("DishPic");
			InputStream file;
			file = photopart.getInputStream();
			if (photopart.getSize() > 0)
				dish.setPhoto(file);
			else
				dish.setPhoto(DAOFactory.getInstance().getDishDAO().DishFromId(dish.getDishId()).getPhoto());
			DAOFactory.getInstance().getDishDAO().updateDish(dish);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

	public HttpServletRequest searchDish(HttpServletRequest request, HttpServletResponse response, String dname,
			String categoryId, String dtype1, String isFilter) throws ServletException, IOException {
		List<Dish> dl = null;
		if (isFilter != null && Boolean.parseBoolean(isFilter)) {
			if (dname == null && categoryId == null && dtype1 == null) {
				dl = DAOFactory.getInstance().getDishDAO().getAllDish();
			} else {
				dl = DAOFactory.getInstance().getDishDAO().getAllDish(dname, categoryId, dtype1);
			}
		} else {
			dl = DAOFactory.getInstance().getDishDAO().getAllDish();
		}
		request.setAttribute("dishList", dl);
		request.setAttribute("categories", CategoryDAO.getAllCategories());

		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser.getAdmin() == 3 && sessionUser.getRestaurantId() != 0) {
			int restId = sessionUser.getRestaurantId();
			List<Dish> dlForRest = new ArrayList<>();
			for (Dish dish : dl) {
				if (dish.getRestId() == restId)
					dlForRest.add(dish);
			}
			request.setAttribute("dishList", dlForRest);
		}

		return request;
	}

	public void deleteDish(String dishId) {
		try {
			DAOFactory.getInstance().getDishDAO()
					.DeletedDish(DAOFactory.getInstance().getDishDAO().DishFromId(Integer.parseInt(dishId)));
		} catch (NumberFormatException | DatabaseException | IOException e) {
			throw new DishException("Opps, something went wrong", e);
		}
	}
}
