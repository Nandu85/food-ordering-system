package com.narola.fooddelivery.Transaction;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.narola.fooddelivery.order.Order;
import com.narola.fooddelivery.order.OrderDAO;
import com.narola.fooddelivery.order.OrderException;
import com.narola.fooddelivery.user.User;
import com.narola.fooddelivery.user.UserDAO;
import com.narola.fooddelivery.utility.Constant;
import com.narola.fooddelivery.utility.URLConstantOfServlet;

/**
 * Servlet implementation class TransactionSuccessServlet
 */
public class TransactionSuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("id");
		String userid = request.getParameter("user");
		String url=request.getContextPath()+URLConstantOfServlet.PROFILE;
		User user = null;
		Order order=null;
//		if(id!=null && !id.trim().isEmpty() && userid!=null && !userid.trim().isEmpty()) {
//			order=OrderDAO.getOrderFromId(Integer.parseInt(id));
//			request.getSession().setAttribute("order", order);
//			User user =UserDAO.findByUserId(Integer.parseInt(userid));
//			request.getSession().setAttribute("user",user);
			
			HttpSession session = request.getSession();
			
			String razPayId =  request.getParameter("razorpay_order_id");
			if(razPayId != null) {
				order = OrderDAO.getOrderFromRazorpayId(razPayId);
				user = order.getUser();
				session.setAttribute("user",user);
				
				Transaction transaction = new Transaction();
				transaction.setPaymentStatus(Constant.PAYMENT_SUCCESS);
				transaction.setReason("");
				transaction.setTotalAmount(order.getTotal());
				transaction.setUser(user);
				transaction.setOrder(order.getOrderId());
				transaction.setRazerPaymentId(razPayId);
				
				transaction=TransactionDAO.addTransaction(transaction);
				order.setTransaction(transaction);
				OrderDAO.SetTransaction(order);
				
			}
			else {
				request.setAttribute("ErrMsg","Payment Failed");
				String x = request.getParameter("error[metadata]");
				ObjectMapper mapper=new ObjectMapper();
				Map<String,String> maps=mapper.readValue(x, Map.class);
				String razorPayOID = maps.get("order_id");
				order=OrderDAO.getOrderFromRazorpayId(razorPayOID);
				session.setAttribute("user", order.getUser());
				url+="?transaction=fail";
//				throw new OrderException("Payment Failed");
			}
			
			
				
			
//			CloseableHttpClient razorPay=HttpClients.createDefault();
//			HttpGet httpGet = new HttpGet("https://api.razorpay.com/v1/orders/"+order.getRazorpayOrderId()+"/payments");
//			httpGet.addHeader("Content-Type","application/json");
//			String auth="Basic "+Base64.getEncoder().encodeToString("rzp_test_f2BZIUq23nSlLZ:c3xU697CvWyifZIhUZ6ikGuG".getBytes());
//			httpGet.addHeader("Authorization", auth);
//			
//			ObjectMapper mapper = new ObjectMapper();
//			
//			CloseableHttpResponse closeableHttpResponse=razorPay.execute(httpGet);
//			HttpEntity entity = closeableHttpResponse.getEntity();
//			String jsonResp = null;
//			try {
//				jsonResp = EntityUtils.toString(entity);
//				System.out.println(jsonResp);
//			} catch (ParseException | IOException e) {
//				e.printStackTrace();
//			}
//			
			
			Map<String, ?> map=request.getParameterMap();
 			for(Entry<String, ?> pair:map.entrySet()) {
				System.out.print(pair.getKey()+"   ");
				System.out.println(request.getParameter(pair.getKey()));
			}
			
			
//		}
			
		response.sendRedirect(url);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
