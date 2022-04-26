package com.narola.fooddelivery;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ProtocolException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class test {

	public static void main(String[] args) throws IOException, ProtocolException {
		CloseableHttpClient razorPay=HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("https://api.razorpay.com/v1/orders");
		httpPost.addHeader("Content-Type","application/json");
		String auth="Basic "+Base64.getEncoder().encodeToString("rzp_test_f2BZIUq23nSlLZ:c3xU697CvWyifZIhUZ6ikGuG".getBytes());
		httpPost.addHeader("Authorization", auth);
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node1=mapper.createObjectNode();
		node1.put("amount", 1000000);
		node1.put("currency", "INR");
		node1.put("receipt", "Receipt no. 1");
//		node1.put("notes", 1000000);
		
		String jsonstr=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node1);
		System.out.println(jsonstr);
		
		StringEntity ent = new StringEntity(jsonstr);
		httpPost.setEntity(ent);
		CloseableHttpResponse response2 = razorPay.execute(httpPost);
		try {
		    System.out.println(response2.toString());
		    HttpEntity entity2 = response2.getEntity();
		    
		    String jsonresp=EntityUtils.toString(entity2);
		    HashMap<?,?> map = mapper.readValue(jsonresp.getBytes(), HashMap.class);
		    
		    System.out.println(map.get("id"));
		    EntityUtils.consume(entity2);
		} finally {
		    response2.close();
		}


	}

}
