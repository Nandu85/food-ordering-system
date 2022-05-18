package com.narola.fooddelivery;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DatabaseServlet extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		try {
			String dbType = getServletContext().getInitParameter("DB-IN-USE");

			DAOFactory.getInstance().init(dbType);
			String dbUrl = getServletContext().getInitParameter(dbType+"_dburl");
			String dbName = getServletContext().getInitParameter(dbType+"_dbname");
			String username = getServletContext().getInitParameter(dbType+"_username");
			String password = getServletContext().getInitParameter(dbType+"_password");
			
			DBConnection.getInstance().setUrl(dbUrl);
			DBConnection.getInstance().setDbname(dbName);
			DBConnection.getInstance().setUsername(username);
			DBConnection.getInstance().setPassword(password);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}

}
