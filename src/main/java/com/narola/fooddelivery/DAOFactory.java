package com.narola.fooddelivery;

import com.narola.fooddelivery.dishes.dao.DishDAOMYSQL;
import com.narola.fooddelivery.dishes.dao.DishDAOPostGRESQL;
import com.narola.fooddelivery.dishes.dao.IDishDAO;

public class DAOFactory {
	private static DAOFactory DAO_HELPER = null;
	public static String MYSQL = "MYSQL";
	public static String POSTGRESQL = "POSTGRESQL";

	public IDishDAO dishDAO = null;

	public static DAOFactory getInstance() {
		if (DAO_HELPER == null) {
			DAO_HELPER = new DAOFactory();
		}
		return DAO_HELPER;
	}

	public IDishDAO getDishDAO() {
		return dishDAO;
	}

	public void init(String daoType) throws Exception {
		if(daoType.equals(MYSQL)) {
			dishDAO = new DishDAOMYSQL();
		} else if (daoType.equals(POSTGRESQL)) {
			dishDAO = new DishDAOPostGRESQL();
		} else {
			throw new Exception("Type is not supported yet");
		}
	}

	
	public static void main(String[] args) {
		System.out.println(getInstance().getDishDAO().getAllDish());
	}
	// Still getting DAO and initializing them according to database is remaining

}
