package com.narola.fooddelivery.order;

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

public class OrderItemDAO {

	public static OrderItem addOrderItem(OrderItem item) throws DatabaseException {
		return addOrderItem(item, null);
	}

	public static OrderItem addOrderItem(OrderItem item, Connection con) throws DatabaseException {
		if (con == null) {
			con = DBConnection.getConnection();
		}
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(
					"INSERT INTO `order_items`\r\n" + "(`OrderId`, `DishId`, `Qty`,\r\n" + "`Amount`) VALUES (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, item.getOrder().getOrderId());
			ps.setInt(2, item.getDish().getDishId());
			ps.setInt(3, item.getQty());
			ps.setInt(4, item.getAmount());

			ps.executeUpdate();
			resultSet = ps.getGeneratedKeys();
			if (resultSet.next()) {
				item.setOrderItemId(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get addOrderItem", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return item;
	}

	public static List<OrderItem> getItemsOfOrder(int id) throws IOException {
		List<OrderItem> items = null;
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("SELECT * FROM foodorderingsystem.order_items where OrderId=?");

			ps.setInt(1, id);
			resultSet = ps.executeQuery();

			items = new ArrayList<>();
			while (resultSet.next()) {
				OrderItem itm = new OrderItem();
				itm.setOrder(OrderDAO.getOrderFromId(id));
				itm.setDish(DishDAO.DishFromId(resultSet.getInt("DishId")));
				itm.setQty(resultSet.getInt("Qty"));
				itm.setOrderItemId(resultSet.getInt("OrderItemId"));

				items.add(itm);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get addOrderItem", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return items;

	}
}
