package com.narola.fooddelivery.utility;

import com.narola.fooddelivery.dishes.dao.DishDAOMYSQL;
import com.narola.fooddelivery.dishes.dao.DishDAOPostGRESQL;
import com.narola.fooddelivery.dishes.dao.IDishDAO;
import com.narola.fooddelivery.exception.DatabaseException;
import com.narola.fooddelivery.restaurants.dao.IRestDAO;
import com.narola.fooddelivery.restaurants.dao.RestDAOMYSQL;
import com.narola.fooddelivery.restaurants.dao.RestDAOPOSTGRESQL;

public class DAOFactory {
	private static DAOFactory DAO_HELPER = null;
	public static String MYSQL = "MYSQL";
	public static String POSTGRESQL = "POSTGRESQL";
	
	public static IDishDAO dishDAO = null;
	public static IRestDAO restDAO = null;

	public static DAOFactory getInstance() {
		if (DAO_HELPER == null) {
			DAO_HELPER = new DAOFactory();
		}
		return DAO_HELPER;
	}

	public IRestDAO getRestDAO() {
		return restDAO;
	}

	public IDishDAO getDishDAO() {
		return dishDAO;
	}

	public void init(String daoType) throws DatabaseException {
		if (daoType.equals(MYSQL)) {
			restDAO = new RestDAOMYSQL();
			dishDAO = new DishDAOMYSQL();
		} else if (daoType.equals(POSTGRESQL)) {
			restDAO = new RestDAOPOSTGRESQL();
			dishDAO = new DishDAOPostGRESQL();
		} else {
			throw new DatabaseException("Type is not supported yet");
		}
	}

}
