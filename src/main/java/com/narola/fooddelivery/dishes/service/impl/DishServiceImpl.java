package com.narola.fooddelivery.dishes.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.narola.fooddelivery.category.Category;
import com.narola.fooddelivery.category.CategoryDAO;
import com.narola.fooddelivery.category.SubCategoryDAO;
import com.narola.fooddelivery.dishes.DishException;
import com.narola.fooddelivery.dishes.model.Dish;
import com.narola.fooddelivery.dishes.service.IDishService;
import com.narola.fooddelivery.dishes.validation.DishValidator;
import com.narola.fooddelivery.exception.ApplicationException;
import com.narola.fooddelivery.exception.DatabaseException;
import com.narola.fooddelivery.user.User;
import com.narola.fooddelivery.utility.Constant;
import com.narola.fooddelivery.utility.DAOFactory;

public class DishServiceImpl implements IDishService {

	public void addDish(Dish dish, Part photoPart) {
		try {

			InputStream file = photoPart.getInputStream();
			dish.setPhoto(file);
			dish.setCategoryId(
					SubCategoryDAO.getSubCategoryById(dish.getSubcategoryId()).getCategory().getCategoryId());

			DishValidator.validate(dish);

			DAOFactory.getInstance().getDishDAO().addDish(dish);

		} catch (IOException e) {
			throw new ApplicationException(Constant.ERR_SOMETHING_WRONG, e);
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
			throw new ApplicationException(Constant.ERR_SOMETHING_WRONG, e);
		}
	}

	public List<Dish> searchDish(HttpServletRequest request, HttpServletResponse response, String dname,
			String categoryId, String dtype1, String isFilter) {
		List<Dish> dl = null;
		try {
			if (isFilter != null && Boolean.parseBoolean(isFilter)) {
				if (dname == null && categoryId == null && dtype1 == null) {
					dl = DAOFactory.getInstance().getDishDAO().getAllDish();
				} else {
					dl = DAOFactory.getInstance().getDishDAO().getAllDish(dname, categoryId, dtype1);
				}
			} else {
				dl = DAOFactory.getInstance().getDishDAO().getAllDish();
			}

			User sessionUser = (User) request.getSession().getAttribute("user");
			if (sessionUser.getAdmin() == Constant.ADMIN_RESTAURANTADMIN && sessionUser.getRestaurantId() != 0) {
				int restId = sessionUser.getRestaurantId();
				List<Dish> dlForRest = new ArrayList<>();
				for (Dish dish : dl) {
					if (dish.getRestId() == restId)
						dlForRest.add(dish);
				}

				return dlForRest;
			}
		} catch (DatabaseException e) {
			throw new ApplicationException(Constant.ERR_SOMETHING_WRONG, e);
		}
		return dl;
	}

	public List<Category> getCategories() {
		return CategoryDAO.getAllCategories();
	}

	public void deleteDish(String dishId) {
		try {
			DAOFactory.getInstance().getDishDAO()
					.DeletedDish(DAOFactory.getInstance().getDishDAO().DishFromId(Integer.parseInt(dishId)));
		} catch (NumberFormatException | DatabaseException | IOException e) {
			throw new DishException(Constant.ERR_SOMETHING_WRONG, e);
		}
	}
}
