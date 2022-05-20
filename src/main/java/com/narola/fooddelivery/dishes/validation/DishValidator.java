package com.narola.fooddelivery.dishes.validation;

import com.narola.fooddelivery.category.SubCategoryDAO;
import com.narola.fooddelivery.dishes.DishException;
import com.narola.fooddelivery.dishes.model.Dish;
import com.narola.fooddelivery.utility.Constant;

public class DishValidator {

	public static void validate(Dish dish) throws DishException {
		if (dish.getDishName() == null || dish.getDishName().trim().isEmpty()) {
			throw new DishException("Please enter valid dish name");
		}

		if (dish.getPrice() <= 0) {
			throw new DishException("Please enter valid price");
		}
		
		if(dish.getDishtype()!=0 && dish.getDishtype()!=1) {
			throw new DishException("Please enter valid dishtype!");
		}
	}
	
	public static void validate(String dname, String price, String ingr, String category, String dtype ) {
		if (dname == null || dname.trim().isEmpty()) {
			throw new DishException("Please enter dish name");
		} else if (price == null || price.trim().isEmpty()) {
			throw new DishException("Please enter price");
		} else if (dtype == null || dtype.trim().isEmpty()) {
			throw new DishException("Please enter dish type");
		}

		else if (Integer.parseInt(dtype) != Constant.DISH_TYPE_VEG
				&& Integer.parseInt(dtype) != Constant.DISH_TYPE_NON_VEG) {
			throw new DishException("Please enter valid dish type");
		} else if (category == null || SubCategoryDAO.getSubCategoryById(Integer.parseInt(category)) == null) {
			throw new DishException("Please enter valid category");
		}
	}

}
