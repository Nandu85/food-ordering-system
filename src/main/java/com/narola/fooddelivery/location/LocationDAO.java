package com.narola.fooddelivery.location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.narola.fooddelivery.DBConnection;
import com.narola.fooddelivery.DatabaseException;
import com.narola.fooddelivery.user.UserDAO;

public class LocationDAO {

	public static Location addLocation(Location location) throws DatabaseException{
		if(getLocationId(location)==0) {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps=null;
			ResultSet resultSet=null;
			try {
				ps = con.prepareStatement(
						"INSERT INTO `location_restaurants`\r\n"
								+ "(`AddressLine`,`Area`,`City`,`State`,`Pincode`)\r\n" + " VALUES(?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);

				ps.setString(1, location.getAddressLine());
				ps.setString(2, location.getArea());
				ps.setString(3, location.getCity());
				ps.setString(4, location.getState());
				ps.setInt(5, location.getPincode());

				ps.executeUpdate();
				resultSet = ps.getGeneratedKeys();
				if (resultSet.next()) {
					location.setLocationId(resultSet.getInt(1));

				}
			} catch (SQLException e) {
				throw new DatabaseException("Error while insert Loc", e);
			} catch (DatabaseException e) {
				throw e;
			} finally {
				DBConnection.releaseResource(ps, resultSet);
			}

		}
		
		return location;

	}
	
	public static Location setUserLocation(Location location) {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		
		try {
			ps = con.prepareStatement(
					"UPDATE `foodorderingsystem`.`location_restaurants` set userID=? where LocId=?");

			ps.setInt(1, location.getUser().getUserId());
			ps.setInt(2, location.getLocationId());

			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DatabaseException("Error while insert Loc", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return location;
	}
	
	
	public static Location UpdateLocation(Location location) {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		
		try {
			ps = con.prepareStatement("UPDATE `location_restaurants`\r\n"
					+ "SET `AddressLine` = ?,\r\n"
					+ "`Area` = ?, `City` = ?,\r\n"
					+ "`State` = ?, `Pincode` = ?\r\n"
					+ "WHERE `LocId` = ?");

			ps.setString(1, location.getAddressLine());
			ps.setString(2, location.getArea());
			ps.setString(3, location.getCity());
			ps.setString(4, location.getState());
			ps.setInt(5, location.getPincode());
			ps.setInt(6, location.getLocationId());

			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DatabaseException("Error while update Loc", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return location;
	}
	
	public static int getLocationId(Location location) throws DatabaseException{
		Connection con = DBConnection.getConnection();
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			String query = "select LocId from `location_restaurants` where AddressLine=? and Area=? and City=? and State=? and Pincode=?";
							
			ps=con.prepareStatement(query);
			ps.setString(1, location.getAddressLine());
			ps.setString(2, location.getArea());
			ps.setString(3, location.getCity());
			ps.setString(4, location.getState());
			ps.setInt(5, location.getPincode());
			resultSet = ps.executeQuery();
			
			
			if (resultSet.next()) {
				location.setLocationId(resultSet.getInt(1));

			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get location id", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return location.getLocationId();
	}
	
	public static Location getLocationFromId(int id) throws DatabaseException{
		Location location = new Location();
		Connection con = DBConnection.getConnection();
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			
			String query = "select * from `location_restaurants` where LocId=?";
							
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			
			
			if (resultSet.next()) {
				location.setLocationId(id);
				location.setAddressLine(resultSet.getString("AddressLine"));
				location.setArea(resultSet.getString("Area"));
				location.setCity(resultSet.getString("City"));
				location.setState(resultSet.getString("State"));
				location.setPincode(resultSet.getInt("PinCode"));

			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get location from id", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return location;
	}
	
	
	public static List<String> getAreas() throws DatabaseException{
		List<String> area = null;
		
		Connection con = DBConnection.getConnection();
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			area= new ArrayList<>();
			String query = "SELECT distinct Area FROM location_restaurants";
			ps=con.prepareStatement(query);
			resultSet = ps.executeQuery();
			
			
			while (resultSet.next()) {
				area.add(resultSet.getString(1));

			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while getArea location", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return area;
	}
	
	public static List<Location> getLocationFromuserId(int id) throws DatabaseException{
		
		Connection con = DBConnection.getConnection();
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		List<Location> locations=null;
		try {
			
			String query = "select * from `location_restaurants` where userID=?";
							
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			
			locations=new ArrayList<>();
			while (resultSet.next()) {
				Location location = new Location();
				location.setLocationId(resultSet.getInt("LocId"));
				location.setAddressLine(resultSet.getString("AddressLine"));
				location.setArea(resultSet.getString("Area"));
				location.setCity(resultSet.getString("City"));
				location.setState(resultSet.getString("State"));
				location.setPincode(resultSet.getInt("PinCode"));
				location.setUser(UserDAO.findByUserId(resultSet.getInt("userID")));
				locations.add(location);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get location from id", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return locations;
	}	
		
	public static void main(String[] args) {
		

	}

}
