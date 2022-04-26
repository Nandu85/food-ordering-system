package com.narola.fooddelivery.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.narola.fooddelivery.Constant;
import com.narola.fooddelivery.URLConstantOfServlet;
import com.narola.fooddelivery.URLConstantUser;
import com.narola.fooddelivery.Cart.Cart;
import com.narola.fooddelivery.Cart.CartItem;
import com.narola.fooddelivery.location.LocationDAO;
import com.narola.fooddelivery.paymentgateway.OrderEntity;
import com.narola.fooddelivery.paymentgateway.RazerPayPaymentGateway;
import com.narola.fooddelivery.user.User;

/**
 * Servlet implementation class PlaceOrderServlet
 */
public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String LocId = null;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("Cart");
		User user = (User) request.getSession().getAttribute("user");
		LocId = request.getParameter("Location");

		if (cart != null && user != null && LocId != null) {

			Order order = new Order();
			order.setUser(user);
			order.setRestaurant(cart.getRestaurant());
			order.setTotal(cart.getTotal());
			order.setOrderStatus(Constant.ORDER_NEW);
//			order.setTransaction(transaction);
			order.setLocation(LocationDAO.getLocationFromId(Integer.parseInt(LocId)));

			List<OrderItem> orderItems = new ArrayList<OrderItem>();
			for (CartItem item : cart.getItems()) {
				OrderItem oItem = new OrderItem();
				oItem.setOrder(order);
				oItem.setDish(item.getDish());
				oItem.setQty(item.getQty());
				oItem.setAmount(item.getAmount());
				orderItems.add(oItem);
			}
			order.setItems(orderItems);

			OrderDAO.PlaceOrder(order, cart);

			OrderEntity orderEntity = new OrderEntity();
			orderEntity.setAmount(order.getTotal());
			orderEntity.setReceipt(String.valueOf(order.getOrderId()));

			String razorPayOrderId=RazerPayPaymentGateway.createOrder(orderEntity);
			order.setRazorpayOrderId(razorPayOrderId);
			OrderDAO.SetRazorpayId(order);
			request.getSession().removeAttribute("Cart");			

			request.setAttribute("total", order.getTotal());
			request.setAttribute("orderId", order.getOrderId());
			request.setAttribute("RazorPayId", order.getRazorpayOrderId());
			request.setAttribute("Location", LocationDAO.getLocationFromId(Integer.parseInt(LocId)));
			
			request.setAttribute("returnURI", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+URLConstantOfServlet.TRANSACTION_SUCCESS);
			
			request.getRequestDispatcher(URLConstantUser.PLACE_ORDER_JSP).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String LocId=request.getParameter("Location");

//		if(LocId!=null && !LocId.trim().isEmpty()) {
//			Cart cart=(Cart) request.getSession().getAttribute("Cart");
//			User user=(User) request.getSession().getAttribute("user");
//			
//			Transaction transaction = new Transaction();
//			transaction.setUser(user);
//			transaction.setTotalAmount(cart.getTotal());
//			transaction.setPaymentStatus(Constant.PAYMENT_SUCCESS);
//			transaction.setReason("");
//			
//			TransactionDAO.addTransaction(transaction);
//			
//			Order order=new Order();
//			order.setUser(user);
//			order.setRestaurant(cart.getRestaurant());
//			order.setTotal(cart.getTotal());
//			order.setOrderStatus(Constant.ORDER_NEW);
//			order.setTransaction(transaction);
//			order.setLocation(LocationDAO.getLocationFromId(Integer.parseInt(LocId)));
//			
//			OrderDAO.AddOrder(order);
//			
//			for (CartItem item : cart.getItems()) {
//				OrderItem oItem = new OrderItem();
//				oItem.setOrder(order);
//				oItem.setDish(item.getDish());
//				oItem.setQty(item.getQty());
//				oItem.setAmount(item.getAmount());
//				
//				OrderItemDAO.addOrderItem(oItem);
//			}
//			
//			CartDAO.removeCart(cart);
//			request.getSession().setAttribute("Cart", null);
//		}
//		response.sendRedirect(request.getContextPath()+URLConstantOfServlet.PROFILE);
		doGet(request, response);
	}

}
