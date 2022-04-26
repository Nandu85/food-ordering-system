package com.narola.fooddelivery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

	private static Connection connection;

	public static Connection getConnection() throws DatabaseException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorderingsystem", "root",
						"123456");
			}
		} catch (ClassNotFoundException e) {
			throw new DatabaseException("Not able to find driver class", e);
		} catch (SQLException e) {
			throw new DatabaseException("Not able to connect database", e);
		}
		return connection;
	}

	public static void releaseResource(PreparedStatement ps) {
		releaseResource(ps, null);
	}

	public static void releaseResource(PreparedStatement ps, ResultSet resultSet) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
