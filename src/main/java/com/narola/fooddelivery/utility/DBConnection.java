package com.narola.fooddelivery.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.narola.fooddelivery.exception.DatabaseException;

public class DBConnection {

	
	private static DBConnection dbConnection = null;

	private Connection connection = null;
	private String dbname = null;
	private String url = null;
	private String username = null;
	private String password = null;

	public static DBConnection getInstance() {
		if(dbConnection==null) {
			dbConnection=new DBConnection();
		}
			return dbConnection;
	}

	public Connection getConnection() throws DatabaseException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(DBConnection.getInstance().getUrl()+DBConnection.getInstance().getDbname(),
						DBConnection.getInstance().getUsername(),DBConnection.getInstance().getPassword());
				
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

	/**
	 * @return the dbname
	 */
	public String getDbname() {
		return dbname;
	}

	/**
	 * @param dbname the dbname to set
	 */
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static void main(String[] args) {
		Connection connection = DBConnection.getInstance().getConnection();
		if(connection!=null)
			System.out.println("Successful  :)");
		System.out.println(DBConnection.getInstance().getUrl()+DBConnection.getInstance().getDbname()+DBConnection.getInstance().getUsername()+DBConnection.getInstance().getPassword());
	}

}
