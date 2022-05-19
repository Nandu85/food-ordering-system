package com.narola.fooddelivery;

import com.narola.fooddelivery.dishes.service.IDishService;
import com.narola.fooddelivery.dishes.service.impl.DishServiceImpl;
import com.narola.fooddelivery.restaurants.service.IRestaurantService;
import com.narola.fooddelivery.restaurants.service.impl.RestaurantServiceImpl;

public class ServiceFactory {

	private static ServiceFactory SERVICE_HELPER = null;

	public static IRestaurantService restaurantService = null;
	public static IDishService dishService = null;

	public static ServiceFactory getInstance() {
		if (SERVICE_HELPER == null) {
			SERVICE_HELPER = new ServiceFactory();
		}
		return SERVICE_HELPER;
	}

	public IRestaurantService getRestaurantService() {
		if(restaurantService == null)
			restaurantService = new RestaurantServiceImpl();
		return restaurantService;
	}
	
	public IDishService getDishService() {
		if(dishService == null)
			dishService = new DishServiceImpl();
		return dishService;
	}
}
