package com.narola.fooddelivery.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.narola.fooddelivery.DBConnection;
import com.narola.fooddelivery.DatabaseException;
import com.narola.fooddelivery.restaurants.RestDAO;
import com.narola.fooddelivery.user.UserDAO;

public class CartDAO {

	public static Cart AddCart(int restId) {
		Connection con = DBConnection.getInstance().getConnection();
		Cart cart = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("INSERT INTO `cart`\r\n" + "(`RestaurantId`) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, restId);
			ps.executeUpdate();
			resultSet = ps.getGeneratedKeys();
			if (resultSet.next()) {
				cart = new Cart();
				cart.setCartId(resultSet.getInt(1));
				cart.setRestaurant(RestDAO.getRestaurantFromId(restId));

			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while insert cart", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return cart;
	}
	
	
	public static Cart GetCartfromId(int cartId) {
		Connection con = DBConnection.getInstance().getConnection();
		Cart cart = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("select * from cart where cartId=?");
			ps.setInt(1, cartId);
			
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				cart = new Cart();
				cart.setCartId(resultSet.getInt("cartId"));
				cart.setRestaurant(RestDAO.getRestaurantFromId(resultSet.getInt("RestaurantId")));
				cart.setTotal(resultSet.getInt("TotalAmount"));
				cart.setUser(UserDAO.findByUserId(resultSet.getInt("UserId")));

			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while insert cart", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return cart;
	}
	
	
	public static Cart GetCartfromUserId(int userId) {
		Connection con = DBConnection.getInstance().getConnection();
		Cart cart = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("select * from cart where UserId=?");
			ps.setInt(1, userId);
			
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				cart = new Cart();
				cart.setCartId(resultSet.getInt("cartId"));
				cart.setRestaurant(RestDAO.getRestaurantFromId(resultSet.getInt("RestaurantId")));
				cart.setTotal(resultSet.getInt("TotalAmount"));
				cart.setUser(UserDAO.findByUserId(resultSet.getInt("UserId")));

			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while GetCartfromUserId", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return cart;
	}
	
	
	public static Cart SetCartUser(Cart cart) {
		Connection con = DBConnection.getInstance().getConnection();
		
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("UPDATE `cart`\r\n"
					+ "SET `UserId` = ?\r\n"
					+ "WHERE `cartId` = ?");
			ps.setInt(1, cart.getUser().getUserId());
			ps.setInt(2, cart.getCartId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DatabaseException("Error while SetCartUser", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return cart;
	}
	
	
	public static Cart SetCartTotal(Cart cart) {
		Connection con = DBConnection.getInstance().getConnection();
		
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("UPDATE `foodorderingsystem`.`cart`\r\n"
					+ "SET `TotalAmount`=(SELECT SUM(Amount) FROM"
					+ " foodorderingsystem.cart_items WHERE CartId=?)\r\n"
					+ "WHERE `cartId` = ?");
			ps.setInt(1, cart.getCartId());
			ps.setInt(2, cart.getCartId());
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new DatabaseException("Error while set cart total", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		cart=GetCartfromId(cart.getCartId());
  		return cart;
	}
	
	
	public static int getCartTotal(int cartId) {
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		int total=0;
		try {
			ps = con.prepareStatement("SELECT TotalAmount FROM foodorderingsystem.cart where cartId=?");
			ps.setInt(1, cartId);
			resultSet= ps.executeQuery();
			if(resultSet.next())
				total=resultSet.getInt(1);
			
		} catch (SQLException e) {
			throw new DatabaseException("Error while set cart total", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		
  		return total;
	}
	
	
	public static Cart SetRestaurantId(Cart cart) {
		Connection con = DBConnection.getInstance().getConnection();
		
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("UPDATE `foodorderingsystem`.`cart`\r\n"
					+ "SET `RestaurantId`=?\r\n"
					+ "WHERE `cartId` = ?");
			ps.setInt(1, cart.getRestaurant().getRestId());
			ps.setInt(2, cart.getCartId());
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new DatabaseException("Error while SetRestaurantId", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		cart=GetCartfromId(cart.getCartId());
  		return cart;
	}
	
	
	public static Cart removeCart(Cart cart) {
		return removeCart(cart,null);
	}
	
	public static Cart removeCart(Cart cart,Connection con) {
		if(con==null)
			con = DBConnection.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("DELETE FROM `cart`\r\n"
					+ "WHERE `cartId` = ?");
			
			ps.setInt(1, cart.getCartId());
			ps.executeUpdate();
			CartItemsDAO.removeCartItems(cart.getCartId());
			
		} catch (SQLException e) {
			throw new DatabaseException("Error while removeCart", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		
		CartItemsDAO.removeCartItems(cart.getCartId(),con);
		return cart;
	}
	
	public static void main(String[] args) {
		Cart cart = GetCartfromUserId(23);
//		removeCart(cart);
//		cart.setUser(UserDAO.findByUserId(25));
//		SetCartUser(cart);
//		cart.setRestaurant(RestDAO.getRestaurantFromId(2));
//		SetRestaurantId(cart);
//		System.out.println(getCartTotal(16));
	}

}
