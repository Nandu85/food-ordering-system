package com.narola.fooddelivery.restaurants.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.narola.fooddelivery.category.SubCategory;
import com.narola.fooddelivery.category.SubCategoryDAO;
import com.narola.fooddelivery.exception.ApplicationException;
import com.narola.fooddelivery.location.Location;
import com.narola.fooddelivery.location.LocationDAO;
import com.narola.fooddelivery.restaurants.model.Restaurant;
import com.narola.fooddelivery.restaurants.service.IRestaurantService;
import com.narola.fooddelivery.utility.Constant;
import com.narola.fooddelivery.utility.DAOFactory;

public class RestaurantServiceImpl implements IRestaurantService {

	public void addRestaurant(Location location, Part part, Restaurant restaurant) {
		InputStream is;
		try {
			LocationDAO.addLocation(location);
			is = part.getInputStream();
			byte[] bytes = new byte[is.available()];
			IOUtils.readFully(is, bytes);
			String imageAsBase64 = Base64.getEncoder().encodeToString(bytes);
			restaurant.setLocId(location.getLocationId());
			restaurant.setRestphotoAsBase64(imageAsBase64);
			DAOFactory.getInstance().getRestDAO().addRestaurant(restaurant);
		} catch (IOException e) {
			throw new ApplicationException(Constant.ERR_SOMETHING_WRONG, e);
		}
	}

	public List<Restaurant> searchRestaurants(String restaurantName, String area) {
		try {
			List<Restaurant> restaurants = null;
			if (restaurantName != null && !restaurantName.trim().isEmpty())
				restaurants = DAOFactory.getInstance().getRestDAO().searchRestaurantFromName(restaurantName);
			else if (area != null && !area.trim().isEmpty())
				restaurants = DAOFactory.getInstance().getRestDAO().searchRestaurantFromArea(area);
			else
				restaurants = DAOFactory.getInstance().getRestDAO().getAllRestaurants();

			return restaurants;
		} catch (Exception e) {
			throw new ApplicationException(Constant.ERR_SOMETHING_WRONG, e);
		}
	}

	public void updateRestaurant(Location location, Part restImage, String restaurantName, String email,
			String restaurantId, int disableFlag) {
		InputStream is;
		try {
			LocationDAO.addLocation(location);
			is = restImage.getInputStream();
			byte[] bytes = new byte[is.available()];
			IOUtils.readFully(is, bytes);
			String imgToString = Base64.getEncoder().encodeToString(bytes);

			Restaurant restaurant = DAOFactory.getInstance().getRestDAO()
					.getRestaurantFromId(Integer.parseInt(restaurantId));

			restaurant.setRestName(restaurantName);
			restaurant.setEmail(email);
			restaurant.setLocation(location);
			restaurant.setLocId(LocationDAO.getLocationId(location));
			if (!imgToString.isEmpty())
				restaurant.setRestphotoAsBase64(imgToString);
			restaurant.setDisableFlag(disableFlag);

			DAOFactory.getInstance().getRestDAO().updateRestaurant(restaurant);
		} catch (IOException e) {
			throw new ApplicationException(Constant.ERR_SOMETHING_WRONG, e);
		}
	}

	public SubCategory getSubCategoryById(String subCatId) {
		try {
			return SubCategoryDAO.getSubCategoryById(Integer.parseInt(subCatId));
		} catch (Exception e) {
			throw new ApplicationException(Constant.ERR_SOMETHING_WRONG, e);
		}
	}

	public List<Restaurant> getRestaurantFromSubCategory(String subCatId) {
		try {
			return DAOFactory.getInstance().getRestDAO().getRestaurantsFromSubCategory(Integer.parseInt(subCatId));
		} catch (Exception e) {
			throw new ApplicationException(Constant.ERR_SOMETHING_WRONG, e);
		}
	}

	public Restaurant getRestaurantFromId(String restaurantId) {
		try {
			return DAOFactory.getInstance().getRestDAO().getRestaurantFromId(Integer.parseInt(restaurantId));
		} catch (Exception e) {
			throw new ApplicationException(Constant.ERR_SOMETHING_WRONG, e);
		}
	}

	public List<Restaurant> getRestaurants() {
		try {
			return DAOFactory.getInstance().getRestDAO().getAllRestaurants();
		} catch (Exception e) {
			throw new ApplicationException(Constant.ERR_SOMETHING_WRONG, e);
		}
	}

	public List<String> getAreas() {
		try {
			return LocationDAO.getAreas();
		} catch (Exception e) {
			throw new ApplicationException(Constant.ERR_SOMETHING_WRONG, e);
		}
	}
}
