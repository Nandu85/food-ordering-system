package com.narola.fooddelivery.Transaction;

import com.narola.fooddelivery.order.Order;
import com.narola.fooddelivery.user.User;

public class Transaction {
	private int transactionId;
	private User user;
	private int order;
	private int totalAmount;
	private int paymentStatus;
	private String Reason;
	private String RazerPaymentId;
	
	public int getOrder() {
		return order;
	}
	public void setOrder(int i) {
		this.order = i;
	}
	public String getRazerPaymentId() {
		return RazerPaymentId;
	}
	public void setRazerPaymentId(String razerPaymentId) {
		RazerPaymentId = razerPaymentId;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	
	
}
