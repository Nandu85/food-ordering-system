package com.narola.fooddelivery.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class PaymentServlet
 */
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		CloseableHttpClient razorPay=HttpClients.createDefault();
//		HttpPost httpPost = new HttpPost("https://api.razorpay.com/v1/orders");
//		List<NameValuePair> nvps=new ArrayList<>();
//		nvps.add(new BasicNameValuePair("key_id", "rzp_test_f2BZIUq23nSlLZ"));
//		nvps.add(new BasicNameValuePair("key_secret", "c3xU697CvWyifZIhUZ6ikGuG"));
//		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
//		CloseableHttpResponse response2 = razorPay.execute(httpPost);
//		try {
//		    System.out.println(response2.getHeaders());
//		    HttpEntity entity2 = response2.getEntity();
//		    // do something useful with the response body
//		    // and ensure it is fully consumed
//		    EntityUtils.consume(entity2);
//		} finally {
//		    response2.close();
//		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
