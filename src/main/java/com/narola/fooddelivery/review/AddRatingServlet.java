package com.narola.fooddelivery.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.fooddelivery.order.Order;
import com.narola.fooddelivery.order.OrderDAO;
import com.narola.fooddelivery.user.User;

/**
 * Servlet implementation class AddRatingServlet
 */
public class AddRatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId=request.getParameter("orderId");
		String rating=request.getParameter("rating");
		String comments=request.getParameter("comments");
		
		Order order=OrderDAO.getOrderFromId(Integer.parseInt(orderId));
		
		Review review=new Review();
		review.setOrder(order);
		review.setRating(Integer.parseInt(rating));
		review.setComment(comments);
		review.setRestaurant(order.getRestaurant());
		review.setUser((User) request.getSession().getAttribute("user"));
		
		ReviewDAO.insertReview(review);
//		doGet(request, response);
	}

}
