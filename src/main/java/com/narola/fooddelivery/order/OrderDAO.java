package com.narola.fooddelivery.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.narola.fooddelivery.DBConnection;
import com.narola.fooddelivery.DatabaseException;
import com.narola.fooddelivery.Cart.Cart;
import com.narola.fooddelivery.Cart.CartDAO;
import com.narola.fooddelivery.Transaction.TransactionDAO;
import com.narola.fooddelivery.location.LocationDAO;
import com.narola.fooddelivery.restaurants.RestDAO;
import com.narola.fooddelivery.user.UserDAO;

public class OrderDAO {

	public static Order PlaceOrder(Order order, Cart cart) {
		Connection con = DBConnection.getConnection();
		
		try {
			con.setAutoCommit(false);
			AddOrder(order, con);
			for (OrderItem orderItem : order.getItems()) {
				orderItem.setOrder(order);
				OrderItemDAO.addOrderItem(orderItem, con);
				CartDAO.removeCart(cart,con);
			}			
			con.commit();
		} catch (Exception e) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if (con != null) {
				try {
					con.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	public static Order AddOrder(Order order) {
		return AddOrder(order, null);
	}

	public static Order AddOrder(Order order, Connection con) {
		if (con == null) {
			con = DBConnection.getConnection();
		}
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("INSERT INTO `order`\r\n" + "(`UserId`, `RestId`, `Total`,\r\n" + "`Status`,\r\n"
					+ "`LocationId`)\r\n" + "VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, order.getUser().getUserId());
			ps.setInt(2, order.getRestaurant().getRestId());
			ps.setInt(3, order.getTotal());
			ps.setInt(4, order.getOrderStatus());
//			ps.setInt(5, order.getTransaction().getTransactionId());
			ps.setInt(5, order.getLocation().getLocationId());

			ps.executeUpdate();
			resultSet = ps.getGeneratedKeys();
			if (resultSet.next()) {
				order.setOrderId(resultSet.getInt(1));

			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while Place Order", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return order;
	}

	public static Order SetRazorpayId(Order order) {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("UPDATE `foodorderingsystem`.`order`\r\n" + "SET `RazorpayOrderId` = ?\r\n"
					+ "WHERE `OrderId` = ?");

			ps.setString(1, order.getRazorpayOrderId());
			ps.setInt(2, order.getOrderId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DatabaseException("Error while SetRazorpayId", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps);
		}

		return order;
	}

	public static String getRazorpayOrderId(Order order) {
		String id = null;
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("SELECT RazorpayOrderId " + "FROM order where OrderId=?");

			ps.setInt(1, order.getOrderId());

			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				order.setOrderId(resultSet.getInt(1));

			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while getRazorpayOrderId", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return id;
	}
	
	public static List<Order> getAllOrdersSorted(List<Order> orders,final String field) {
		
		Collections.sort(orders, new Comparator<Order>() {

		
			public int compare(Order o1, Order o2) {
				if(field.equals("OrderId"))
					return o1.compareTo(o1.getOrderId(), o2.getOrderId());
				else if(field.equals("Status"))
					return o1.compareTo(o1.getOrderStatus(), o2.getOrderStatus());
				else if(field.equals("Total"))
					return o1.compareTo(o1.getTotal(), o2.getTotal());
				else if(field.equals("CreatedOn")) 
					return o1.getDate().compareTo(o2.getDate());
				else if(field.equals("Restaurant")) 
					return o1.getRestaurant().getRestName().compareTo(o2.getRestaurant().getRestName());
				else if(field.equals("User")) 
					return o1.getUser().getUsername().compareTo(o2.getUser().getUsername());
				else return 0;
					
			}
		
			
		});
		
//		orders.sort(null);
		

		return orders;

	}

	public static Order getOrderFromId(int id) {
		Order order = null;
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("SELECT * FROM foodorderingsystem.order where OrderId=?");

			ps.setInt(1, id);

			resultSet = ps.executeQuery();
			order = new Order();
			if (resultSet.next()) {
				order.setOrderId(id);
				order.setUser(UserDAO.findByUserId(resultSet.getInt("UserId")));
				order.setRestaurant(RestDAO.getRestaurantFromId(resultSet.getInt("RestId")));
				order.setTotal(resultSet.getInt("Total"));
				order.setOrderStatus(resultSet.getInt("Status"));
				order.setTransaction(TransactionDAO.getTransactionFromId(resultSet.getInt("TransactionId")));
				order.setLocation(LocationDAO.getLocationFromId(resultSet.getInt("LocationId")));
				order.setRazorpayOrderId(resultSet.getString("RazorpayOrderId"));
				order.setDate(resultSet.getTimestamp("CreatedOn"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DatabaseException("Error while getOrderFromId", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return order;
	}

	
	public static Order getOrderFromRazorpayId(String id) {
		Order order = null;
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("SELECT * FROM `order` where RazorpayOrderId=?");

			ps.setString(1, id);

			resultSet = ps.executeQuery();
			order = new Order();
			if (resultSet.next()) {
				order.setOrderId(resultSet.getInt("OrderId"));
				order.setUser(UserDAO.findByUserId(resultSet.getInt("UserId")));
				order.setRestaurant(RestDAO.getRestaurantFromId(resultSet.getInt("RestId")));
				order.setTotal(resultSet.getInt("Total"));
				order.setOrderStatus(resultSet.getInt("Status"));
				order.setTransaction(TransactionDAO.getTransactionFromId(resultSet.getInt("TransactionId")));
				order.setLocation(LocationDAO.getLocationFromId(resultSet.getInt("LocationId")));
				order.setRazorpayOrderId(resultSet.getString("RazorpayOrderId"));

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DatabaseException("Error while getOrderFromId", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return order;
	}

	
	
	public static Order SetTransaction(Order order) {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("UPDATE `order`\r\n" + "SET `TransactionId` = ?\r\n" + "WHERE `OrderId` = ?");

			ps.setInt(1, order.getTransaction().getTransactionId());
			ps.setInt(2, order.getOrderId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DatabaseException("Error while SetTransaction", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps);
		}

		return order;
	}

	public static List<Order> getOrderofUser(int userId) {
		List<Order> orders = null;
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;

		try {
			ps = con.prepareStatement("SELECT * FROM `order` where UserId=? order by OrderId desc");

			ps.setInt(1, userId);

			resultSet = ps.executeQuery();
			orders = new ArrayList<>();
			while (resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt("OrderId"));
				order.setUser(UserDAO.findByUserId(resultSet.getInt("UserId")));
				order.setRestaurant(RestDAO.getRestaurantFromId(resultSet.getInt("RestId")));
				order.setTotal(resultSet.getInt("Total"));
				order.setOrderStatus(resultSet.getInt("Status"));
				order.setTransaction(TransactionDAO.getTransactionFromId(resultSet.getInt("TransactionId")));
				order.setLocation(LocationDAO.getLocationFromId(resultSet.getInt("LocationId")));
				order.setRazorpayOrderId(resultSet.getString("RazorpayOrderId"));
				order.setDate(resultSet.getTimestamp("CreatedOn"));
				orders.add(order);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DatabaseException("Error while getOrderFromId", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return orders;

	}

	public static List<Order> getAllOrders() {
		List<Order> orders = null;
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("SELECT * FROM `order` order by OrderId desc");

			resultSet = ps.executeQuery();
			orders = new ArrayList<>();
			while (resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt("OrderId"));
				order.setUser(UserDAO.findByUserId(resultSet.getInt("UserId")));
				order.setRestaurant(RestDAO.getRestaurantFromId(resultSet.getInt("RestId")));
				order.setTotal(resultSet.getInt("Total"));
				order.setOrderStatus(resultSet.getInt("Status"));
				order.setTransaction(TransactionDAO.getTransactionFromId(resultSet.getInt("TransactionId")));
				order.setLocation(LocationDAO.getLocationFromId(resultSet.getInt("LocationId")));
				order.setRazorpayOrderId(resultSet.getString("RazorpayOrderId"));
				order.setDate(resultSet.getTimestamp("CreatedOn"));
				orders.add(order);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DatabaseException("Error while getOrderFromId", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return orders;

	}

	public static void changeOrderStatus(int oId, int status) {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement("UPDATE `order`\r\n" + "SET `Status` = ?\r\n" + "WHERE `OrderId` = ?");
			ps.setInt(1, status);
			ps.setInt(2, oId);

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DatabaseException("Error while changeOrderStatus", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps);
		}
	}
	
	public static List<Order> getOrdersDatewise(Timestamp start, Timestamp end,String status) {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<Order> orders=null;
		
		try {
			String query="SELECT * FROM `order` ";
			
			if(start!=null || end!=null || (status!=null && !status.trim().isEmpty() && !status.contains("All")))
				query+="where ";
			if(start!=null) {
				query+="CreatedOn>=\""+String.valueOf(start)+"\" ";
				if(end!=null || status!=null)
					query+="and ";
			}
				
			if(end!=null) {
				query+="CreatedOn<=\""+String.valueOf(end)+"\" ";
				if(status!=null)
					query+="and ";
			}
			System.out.println();
			if(status!=null && !status.contains("All"))
				query+="Status="+status+" ";
				
			query+="order by OrderId desc";
			
			ps = con.prepareStatement(query);
//			ps.setTimestamp(1, start);
//			ps.setTimestamp(2, end);

			rs=ps.executeQuery();

			orders = new ArrayList<>();
			while (rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt("OrderId"));
				order.setUser(UserDAO.findByUserId(rs.getInt("UserId")));
				order.setRestaurant(RestDAO.getRestaurantFromId(rs.getInt("RestId")));
				order.setTotal(rs.getInt("Total"));
				order.setOrderStatus(rs.getInt("Status"));
				order.setTransaction(TransactionDAO.getTransactionFromId(rs.getInt("TransactionId")));
				order.setLocation(LocationDAO.getLocationFromId(rs.getInt("LocationId")));
				order.setRazorpayOrderId(rs.getString("RazorpayOrderId"));
				order.setDate(rs.getTimestamp("CreatedOn"));
				orders.add(order);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DatabaseException("Error while changeOrderStatus", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps);
		}
		
		return orders;
		
	}

	public static void main(String[] args) {
//		System.out.println(getOrderFromRazorpayId("order_JJB62UAfBIPiQ8").getOrderId());
//		System.out.println(getAllOrdersSorted("OrderId"));
	}
}
