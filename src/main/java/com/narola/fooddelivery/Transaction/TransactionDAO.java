package com.narola.fooddelivery.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.narola.fooddelivery.exception.DatabaseException;
import com.narola.fooddelivery.user.UserDAO;
import com.narola.fooddelivery.utility.DBConnection;

public class TransactionDAO {
	
	public static Transaction addTransaction(Transaction transaction) {
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			ps = con.prepareStatement("INSERT INTO `foodorderingsystem`.`transaction`\r\n"
					+ "(`UserId`,`OrderId`,\r\n"
					+ "`TotalAmount`,`PaymentStatus`,\r\n"
					+ "`RazorpayTransactionId`,\r\n"
					+ "`Reason`)\r\n"
					+ "VALUES (?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, transaction.getUser().getUserId());
			ps.setInt(2, transaction.getOrder());
			ps.setInt(3, transaction.getTotalAmount());
			ps.setInt(4, transaction.getPaymentStatus());
			ps.setString(5, transaction.getRazerPaymentId());
			ps.setString(6, transaction.getReason());

			ps.executeUpdate();
			resultSet = ps.getGeneratedKeys();
			if (resultSet.next()) {
				transaction.setTransactionId(resultSet.getInt(1));

			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while insert transaction", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		
		return transaction;
	}
	
	
	public static Transaction getTransactionFromId(int id) {
		Transaction transaction=null;
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			ps = con.prepareStatement("SELECT * FROM transaction where TransactionId=?");

			ps.setInt(1, id);
			
			resultSet = ps.executeQuery();
			
			if (resultSet.next()) {
				transaction=new Transaction();
				transaction.setTransactionId(id);
				transaction.setPaymentStatus(resultSet.getInt("PaymentStatus"));
				transaction.setUser(UserDAO.findByUserId(resultSet.getInt("UserId")));
				transaction.setTotalAmount(resultSet.getInt("TotalAmount"));
				transaction.setOrder(resultSet.getInt("OrderId"));
				transaction.setReason(resultSet.getString("Reason"));
				transaction.setRazerPaymentId(resultSet.getString("RazorpayTransactionId"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DatabaseException("Error while getTransactionFromId", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		
		return transaction;
	}
}
