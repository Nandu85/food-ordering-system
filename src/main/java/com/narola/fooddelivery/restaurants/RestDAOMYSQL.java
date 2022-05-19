package com.narola.fooddelivery.restaurants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.narola.fooddelivery.DBConnection;
import com.narola.fooddelivery.DatabaseException;
import com.narola.fooddelivery.location.LocationDAO;

public class RestDAOMYSQL implements IRestDAO{

	public Restaurant addRestaurant(Restaurant restaurant) throws DatabaseException {
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			ps = con.prepareStatement("INSERT INTO `restaurants`\r\n"
					+ "(`restname`,`Location`,`email`,`Pic`)\r\n" + "VALUES\r\n" + "(?,?,?,?);\r\n" + "",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, restaurant.getRestName());
			ps.setInt(2, restaurant.getLocId());
			ps.setString(3, restaurant.getEmail());
			ps.setString(4, restaurant.getRestphotoAsBase64());

			ps.executeUpdate();
			resultSet = ps.getGeneratedKeys();
			if (resultSet.next()) {
				restaurant.setRestId(resultSet.getInt(1));

			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get addRestaurant", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return restaurant;
	}

	/**
	 * @param RestaurantName
	 * @return
	 * @throws DatabaseException
	 */
	public List<Restaurant> searchRestaurantFromName(String RestaurantName) throws DatabaseException {
		List<Restaurant> restaurant = null;
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			String query="SELECT * FROM restaurants where restname like \""+RestaurantName+"\"\'%\'";
			ps = con.prepareStatement(query);

			resultSet = ps.executeQuery();
			restaurant = new ArrayList<>();
			while (resultSet.next()) {
				Restaurant rest = new Restaurant();
				rest.setRestId(resultSet.getInt("restid"));
				rest.setRestName(resultSet.getString("restname"));
				rest.setLocId(resultSet.getInt("Location"));
				rest.setEmail(resultSet.getString("email"));
				rest.setRestphotoAsBase64(resultSet.getString("Pic"));
				rest.setLocation(LocationDAO.getLocationFromId(rest.getLocId()));
				rest.setDisableFlag(resultSet.getInt("Disabled"));
				rest.setUserId(resultSet.getInt("RestaurantAdmin"));

				restaurant.add(rest);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get searchRestaurantFromName", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return restaurant;

	}

	public List<Restaurant> searchRestaurantFromArea(String area) throws DatabaseException {
		List<Restaurant> restaurant = null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		Connection con = DBConnection.getInstance().getConnection();
		try {
			ps=con.prepareStatement("SELECT * FROM restaurants where Location IN(SELECT LocId FROM location_restaurants where Area=?)");
			ps.setString(1, area);
			resultSet = ps.executeQuery();
			restaurant=new ArrayList<>();
			while (resultSet.next()) {
				Restaurant rest = new Restaurant();
				rest.setRestId(resultSet.getInt("restid"));
				rest.setRestName(resultSet.getString("restname"));
				rest.setLocId(resultSet.getInt("Location"));
				rest.setEmail(resultSet.getString("email"));
				rest.setRestphotoAsBase64(resultSet.getString("Pic"));
				rest.setLocation(LocationDAO.getLocationFromId(rest.getLocId()));
				rest.setDisableFlag(resultSet.getInt("Disabled"));
				rest.setUserId(resultSet.getInt("RestaurantAdmin"));

				restaurant.add(rest);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get searchRestaurantFromArea", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return restaurant;

	}

	public List<Restaurant> getAllRestaurants() throws DatabaseException {
		List<Restaurant> restaurant = null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		Connection con = DBConnection.getInstance().getConnection();
		try {
			ps=con.prepareStatement("SELECT * FROM restaurants");
			resultSet = ps.executeQuery();
			restaurant=new ArrayList<>();
			while (resultSet.next()) {
				Restaurant rest = new Restaurant();
				rest.setRestId(resultSet.getInt("restid"));
				rest.setRestName(resultSet.getString("restname"));
				rest.setLocId(resultSet.getInt("Location"));
				rest.setEmail(resultSet.getString("email"));
				rest.setRestphotoAsBase64(resultSet.getString("Pic"));
				rest.setLocation(LocationDAO.getLocationFromId(rest.getLocId()));
				rest.setDisableFlag(resultSet.getInt("Disabled"));
				rest.setUserId(resultSet.getInt("RestaurantAdmin"));

				restaurant.add(rest);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while getAllRestaurants", e);
		} catch (DatabaseException e) {
			throw e;
		}finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return restaurant;
	}

	public Restaurant getRestaurantFromId(int id) throws DatabaseException {
		Restaurant restaurant = null;
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			ps=con.prepareStatement("SELECT * FROM restaurants where restid=?");
			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				restaurant = new Restaurant();
				restaurant.setRestId(resultSet.getInt("restid"));
				restaurant.setRestName(resultSet.getString("restname"));
				restaurant.setLocId(resultSet.getInt("Location"));
				restaurant.setEmail(resultSet.getString("email"));
				restaurant.setRestphotoAsBase64(resultSet.getString("Pic"));
				restaurant.setLocation(LocationDAO.getLocationFromId(restaurant.getLocId()));
				restaurant.setDisableFlag(resultSet.getInt("Disabled"));
				restaurant.setPassword(resultSet.getString("password"));
				restaurant.setUserId(resultSet.getInt("RestaurantAdmin"));
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while getRestaurantFromId", e);
		} catch (DatabaseException e) {
			throw e;
		}finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return restaurant;
	}

	public Restaurant updateRestaurant(Restaurant restaurant) throws DatabaseException {
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			ps = con.prepareStatement("UPDATE restaurants\r\n"
					+ "SET `restname` = ?, `Location` = ?, `email` = ?, `Pic` = ?, `Disabled`=?\r\n"
					+ " WHERE `restid` = ?");

			ps.setString(1, restaurant.getRestName());
			ps.setInt(2, restaurant.getLocId());
			ps.setString(3, restaurant.getEmail());
			ps.setString(4, restaurant.getRestphotoAsBase64());
			ps.setInt(5, restaurant.getDisableFlag());
			ps.setInt(6, restaurant.getRestId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DatabaseException("Error while DishesFromSubCategory", e);
		} catch (DatabaseException e) {
			throw e;
		}finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return restaurant;
	}

	public Restaurant setRestaurantAdmin(Restaurant restaurant) throws DatabaseException {
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			
			con.setAutoCommit(false);
			
			ps = con.prepareStatement("UPDATE `restaurants`\r\n" + "SET `RestaurantAdmin`=NULL\r\n"
					+ " WHERE `RestaurantAdmin` = ?");
			ps.setInt(1, restaurant.getUserId());
			ps.executeUpdate();
			
			
			ps = con.prepareStatement("UPDATE `restaurants`\r\n" + "SET `RestaurantAdmin`=?\r\n"
					+ " WHERE `restid` = ?");

			ps.setInt(1, restaurant.getUserId());
			ps.setInt(2, restaurant.getRestId());
			ps.executeUpdate();

			con.commit();
			
		} catch (SQLException e) {
			
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DatabaseException("Error while setRestaurantAdmin", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return restaurant;
	}

	public List<String> getRestaurantCategories(int restId) throws DatabaseException {
		Connection con = DBConnection.getInstance().getConnection();
		List<String> catList = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps = con.prepareStatement("SELECT CategoryName FROM category where categoryId IN(SELECT distinct categoryid FROM dishes where Restaurant=?)");
			ps.setInt(1, restId);
			rs = ps.executeQuery();
			catList=new ArrayList<>();
			while (rs.next()) {
				catList.add(rs.getString(1));
			}

		} catch (SQLException e) {
			throw new DatabaseException("Error while getRestaurantCategories", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, rs);
		}
		return catList;
	}

	public Restaurant getRestaurantFromUserId(int id) throws DatabaseException {
		Restaurant restaurant = new Restaurant();
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			ps=con.prepareStatement("SELECT * FROM `restaurants` where RestaurantAdmin=?");
			ps.setInt(1, id);
			resultSet = ps.executeQuery();

			if (resultSet.next()) {

				restaurant.setRestId(resultSet.getInt("restid"));
				restaurant.setRestName(resultSet.getString("restname"));
				restaurant.setLocId(resultSet.getInt("Location"));
				restaurant.setEmail(resultSet.getString("email"));
				restaurant.setRestphotoAsBase64(resultSet.getString("Pic"));
				restaurant.setLocation(LocationDAO.getLocationFromId(restaurant.getLocId()));
				restaurant.setDisableFlag(resultSet.getInt("Disabled"));
				restaurant.setPassword(resultSet.getString("password"));
				restaurant.setUserId(id);

			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while getRestaurantFromUserId", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return restaurant;
	}

	public List<Restaurant> getRestaurantsFromSubCategory(int id) throws DatabaseException {
		List<Restaurant> restaurant = null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		Connection con = DBConnection.getInstance().getConnection();
		try {
			ps=con.prepareStatement("SELECT * FROM restaurants where restid IN(select Restaurant from dishes "
					+ "where SubCategory=?) and Disabled=0");
			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			restaurant=new ArrayList<>();
			while (resultSet.next()) {
				Restaurant rest = new Restaurant();
				rest.setRestId(resultSet.getInt("restid"));
				rest.setRestName(resultSet.getString("restname"));
				rest.setLocId(resultSet.getInt("Location"));
				rest.setEmail(resultSet.getString("email"));
				rest.setRestphotoAsBase64(resultSet.getString("Pic"));
				rest.setLocation(LocationDAO.getLocationFromId(rest.getLocId()));
				rest.setDisableFlag(resultSet.getInt("Disabled"));
				rest.setUserId(resultSet.getInt("RestaurantAdmin"));

				restaurant.add(rest);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while getRestaurantsFromSubCategory", e);
		} catch (DatabaseException e) {
			throw e;
		}finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return restaurant;
	}
	
	public Timestamp getJoinDate(int restId) {
		Timestamp timestamp=null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		Connection con = DBConnection.getInstance().getConnection();
		
		try {
			ps=con.prepareStatement("SELECT * FROM restaurants where restid=?");
			ps.setInt(1, restId);
			resultSet = ps.executeQuery();
			
			if (resultSet.next()) {
				timestamp=resultSet.getTimestamp("CreatedOn");
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while getRestaurantsFromSubCategory", e);
		} catch (DatabaseException e) {
			throw e;
		}finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		
		return timestamp;
	}
	
	
}
