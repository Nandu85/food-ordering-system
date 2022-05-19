package com.narola.fooddelivery.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.narola.fooddelivery.DAOFactory;
import com.narola.fooddelivery.DBConnection;
import com.narola.fooddelivery.DatabaseException;
import com.narola.fooddelivery.order.Order;
import com.narola.fooddelivery.order.OrderDAO;
import com.narola.fooddelivery.restaurants.RestDAOMYSQL;
import com.narola.fooddelivery.restaurants.Restaurant;



public class ReviewDAO {

	public static Review insertReview(Review review) {
		Connection con=DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=con.prepareStatement("INSERT INTO `foodorderingsystem`.`reviews`\r\n"
					+ "(`orderId`,`userId`,\r\n"
					+ "`restaurantId`,`Rating`,\r\n"
					+ "`Comment`)\r\n"
					+ "VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, review.getOrder().getOrderId());
			ps.setInt(2, review.getUser().getUserId());
			ps.setInt(3, review.getRestaurant().getRestId());
			ps.setInt(4, review.getRating());
			ps.setString(5, review.getComment());
			ps.executeUpdate();
			
			rs=ps.getGeneratedKeys();
			
			if(rs.next())
				review.setReviewId(rs.getInt(1));
		}
		catch (SQLException e) {
			throw new DatabaseException("Error while get addRestaurant", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, rs);
		}
		
		return review;
	}
	
	
	public static Review getReviewofOrder(Order order) {
		Connection con=DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Review review=null;
		try {
			ps=con.prepareStatement("SELECT * FROM reviews where orderId=?");
			ps.setInt(1, order.getOrderId());
						
			rs=ps.executeQuery();
			
			if(rs.next()) {
				review=new Review();
				review.setReviewId(rs.getInt("reviewId"));
				review.setUser(order.getUser());
				review.setOrder(order);
				review.setRestaurant(order.getRestaurant());
				review.setRating(rs.getInt("Rating"));
				review.setComment(rs.getString("Comment"));
				review.setTime(rs.getTimestamp("CreatedOn"));
			}
				
		}
		catch (SQLException e) {
			throw new DatabaseException("Error while get addRestaurant", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, rs);
		}
		
		return review;	
	}
	
	
	public static List<Review> getReviewofRestaurant(Restaurant rest) {
		Connection con=DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Review> reviews=null;
		try {
			ps=con.prepareStatement("SELECT * FROM reviews where restaurantId=?");
			ps.setInt(1, rest.getRestId());
						
			rs=ps.executeQuery();
			reviews=new ArrayList<>();
			while(rs.next()) {
				Review review=new Review();
				review.setReviewId(rs.getInt("reviewId"));
				review.setUser(rest.getUser());
				review.setOrder(OrderDAO.getOrderFromId(rs.getInt("orderId")));
				review.setRestaurant(rest);
				review.setRating(rs.getInt("Rating"));
				review.setComment(rs.getString("Comment"));
				review.setTime(rs.getTimestamp("CreatedOn"));
				
				reviews.add(review);
			}
				
		}
		catch (SQLException e) {
			throw new DatabaseException("Error while get addRestaurant", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, rs);
		}
		
		return reviews;	
	}
	
	
	public static void main(String[] args) {
		Order order=OrderDAO.getOrderFromId(35);
		Restaurant rest=DAOFactory.getInstance().getRestDAO().getRestaurantFromId(1);
		System.out.println(getReviewofOrder(order).getReviewId());
		System.out.println(getReviewofRestaurant(rest));
	}
	
}
