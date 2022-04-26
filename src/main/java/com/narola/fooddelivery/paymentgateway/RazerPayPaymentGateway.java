package com.narola.fooddelivery.paymentgateway;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RazerPayPaymentGateway {
	
	private static final boolean PARTIAL_PAYMENT = false;
	private static final String CURRENCY = "INR";
	private static final String KEY_ID = "rzp_test_f2BZIUq23nSlLZ";
	private static final String KEY_SECRET = "c3xU697CvWyifZIhUZ6ikGuG";
	private static String ORDER_API_POST = "https://api.razorpay.com/v1/orders";

	public static String createOrder(OrderEntity orderEntity) {
		orderEntity.setCurrency(CURRENCY);
		orderEntity.setPartial_payment(PARTIAL_PAYMENT);
		ObjectMapper mapper = new ObjectMapper();
		String jsonstr = null;
		String razorPayOrderID=null;
		try {
			jsonstr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderEntity);
		} catch (JsonProcessingException e2) {
			e2.printStackTrace();
		}
		System.out.println(jsonstr);

		CloseableHttpClient razorPay = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(ORDER_API_POST);
		httpPost.addHeader("Content-Type", "application/json");
		String auth = "Basic " + Base64.getEncoder().encodeToString((KEY_ID + ":" + KEY_SECRET).getBytes());
		httpPost.addHeader("Authorization", auth);

		StringEntity ent = new StringEntity(jsonstr);
		httpPost.setEntity(ent);
		try (CloseableHttpResponse response2 = razorPay.execute(httpPost)) {
			System.out.println(response2.toString());
			HttpEntity entity2 = response2.getEntity();
			String jsonresp = null;
			try {
				jsonresp = EntityUtils.toString(entity2);
			} catch (ParseException | IOException e) {
				e.printStackTrace();
			}
			if (jsonresp != null) {
				System.out.println(jsonresp);
				Map<String, String> map = mapper.readValue(jsonresp.getBytes(), Map.class);
				razorPayOrderID=(String) map.get("id");				
				EntityUtils.consume(entity2);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return razorPayOrderID;
	}
	
	
	public static void main(String[] args) {
//		OrderEntity orderEntity = new OrderEntity();
//		orderEntity.setAmount(200);
//		orderEntity.setReceipt("5");
//		System.out.println("Hiiiiiiii");
//		createOrder(orderEntity);
		
	}

}
