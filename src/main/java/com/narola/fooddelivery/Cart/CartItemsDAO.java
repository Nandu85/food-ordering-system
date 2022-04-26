package com.narola.fooddelivery.Cart;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.narola.fooddelivery.DBConnection;
import com.narola.fooddelivery.DatabaseException;
import com.narola.fooddelivery.dishes.DishDAO;
import com.narola.fooddelivery.restaurants.RestDAO;

public class CartItemsDAO {

	public static CartItem AddCartItem(CartItem item) throws DatabaseException, IOException {
		if (item.getDish().getRestId() == item.getCart().getRestaurant().getRestId()) {
			int id = getIdFromItem(item);
			if (id == 0) {
				Connection con = DBConnection.getConnection();
				PreparedStatement ps = null;
				ResultSet resultSet = null;
				try {
					ps = con.prepareStatement("INSERT INTO `cart_items`\r\n" + "(`CartId`,`DishId`,`Qty`,`Amount`)\r\n"
							+ "VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, item.getCart().getCartId());
					ps.setInt(2, item.getDish().getDishId());
					ps.setInt(3, item.getQty());
					ps.setInt(4, item.getAmount());
					ps.executeUpdate();
					resultSet = ps.getGeneratedKeys();
					if (resultSet.next()) {
						item.setItemId(resultSet.getInt(1));
					}
				} catch (SQLException e) {
					throw new DatabaseException("Error while insert cartItem", e);
				} catch (DatabaseException e) {
					throw e;
				} finally {
					DBConnection.releaseResource(ps, resultSet);
				}
				CartDAO.SetCartTotal(item.getCart());
			} else if (item.getQty() != getItemFromId(id).getQty()) {
				item.setItemId(id);
				updateItem(item);
			}
		} else {
			removeCartItems(item.getCart().getCartId());
			item.getCart().setRestaurant(item.getDish().getRestaurant());
			CartDAO.SetRestaurantId(item.getCart());
			AddCartItem(item);
		}
		return item;
	}

	public static CartItem updateItem(CartItem item) {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("UPDATE `cart_items` SET `Qty` = ?, " + "`Amount` = ? WHERE (`Itemid` = ?)");
			ps.setInt(1, item.getQty());
			ps.setInt(2, item.getAmount());
			ps.setInt(3, item.getItemId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DatabaseException("Error while updateItem", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		CartDAO.SetCartTotal(item.getCart());
		if (item.getQty() == 0)
			removeItem(item);
		return item;
	}

	public static CartItem removeItem(CartItem item) {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("DELETE FROM `cart_items`\r\n" + "WHERE Itemid=?");
			ps.setInt(1, item.getItemId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DatabaseException("Error while remove cartItem", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return item;
	}

	public static CartItem getItemFromId(int id) throws IOException {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		CartItem item = null;
		try {
			ps = con.prepareStatement("select * FROM `cart_items`\r\n" + "WHERE Itemid=?");
			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				item = new CartItem();
				item.setItemId(id);
				item.setCart(CartDAO.GetCartfromId(resultSet.getInt("CartId")));
				item.setDish(DishDAO.DishFromId(resultSet.getInt("DishId")));
				item.setQty(resultSet.getInt("Qty"));
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get cartItem from id", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return item;
	}

	public static List<CartItem> getItemsofCart(int cartId) throws IOException {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		List<CartItem> items = null;
		try {
			ps = con.prepareStatement("select * FROM `cart_items`\r\n" + "WHERE CartId=?");
			ps.setInt(1, cartId);
			resultSet = ps.executeQuery();
			items = new ArrayList<>();
			while (resultSet.next()) {
				CartItem item = new CartItem();
				item.setItemId(resultSet.getInt("Itemid"));
				item.setCart(CartDAO.GetCartfromId(resultSet.getInt("CartId")));
				item.setDish(DishDAO.DishFromId(resultSet.getInt("DishId")));
				item.setQty(resultSet.getInt("Qty"));
				items.add(item);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get cartItem", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return items;
	}

	public static void removeCartItems(int cartId) {
		removeCartItems(cartId, null);
	}

	public static void removeCartItems(int cartId, Connection con) {
		
		if (con == null) {
			con = DBConnection.getConnection();
		}
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("DELETE FROM `cart_items`\r\n" + "WHERE CartId=?");
			ps.setInt(1, cartId);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DatabaseException("Error while remove removeCartItems", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

	}

	public static int getIdFromItem(CartItem item) throws IOException {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		int id = 0;
		try {
			ps = con.prepareStatement("SELECT Itemid FROM " + "cart_items where CartId=? and DishId=?");
			ps.setInt(1, item.getCart().getCartId());
			ps.setInt(2, item.getDish().getDishId());
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt("Itemid");
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get cartItem from id", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return id;
	}

	public static void main(String[] args) throws IOException {
		CartItem item = new CartItem();
		item.setDish(DishDAO.DishFromId(60));
		item.setCart(CartDAO.GetCartfromId(14));
		item.setQty(2);
		item.setItemId(getIdFromItem(item));
		updateItem(item);

	}
}
