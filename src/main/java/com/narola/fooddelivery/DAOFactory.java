package com.narola.fooddelivery;

import com.narola.fooddelivery.dishes.dao.DishDAOMYSQL;
import com.narola.fooddelivery.dishes.dao.DishDAOPostGRESQL;
import com.narola.fooddelivery.dishes.dao.IDishDAO;
import com.narola.fooddelivery.restaurants.dao.IRestDAO;
import com.narola.fooddelivery.restaurants.dao.RestDAOMYSQL;
import com.narola.fooddelivery.restaurants.dao.RestDAOPOSTGRESQL;

public class DAOFactory {
	private static DAOFactory DAO_HELPER = null;
	public static String MYSQL = "MYSQL";
	public static String POSTGRESQL = "POSTGRESQL";
	public static String database = null;
	
	public IDishDAO dishDAO = null;
	public IRestDAO restDAO = null;

	public static DAOFactory getInstance() {
		if (DAO_HELPER == null) {
			DAO_HELPER = new DAOFactory();
		}
		return DAO_HELPER;
	}

	public IRestDAO getRestDAO() {
		if(restDAO==null) {
			if(database.equals(MYSQL)) {
				restDAO = new RestDAOMYSQL();
			} else if (database.equals(POSTGRESQL)) {
				restDAO = new RestDAOPOSTGRESQL();
			}
		}
		return restDAO;
	}

	public IDishDAO getDishDAO() {
		if(dishDAO==null) {
			if(database.equals(MYSQL)) {
				dishDAO = new DishDAOMYSQL();
			} else if (database.equals(POSTGRESQL)) {
				dishDAO = new DishDAOPostGRESQL();
			}
		}
		return dishDAO;
	}
	
	public void init(String daoType) throws DatabaseException {
		if(daoType.equals(MYSQL)) {
			database=MYSQL;
		} else if (daoType.equals(POSTGRESQL)) {
			database=POSTGRESQL;
		} else {
			throw new DatabaseException("Type is not supported yet");
		}
	}

	
	
	// Still getting DAO and initializing them according to database is remaining

}
