package com.narola.fooddelivery.paymentgateway;

public class OrderEntity {

//	private String razorPayOrderID;
	private int amount;
	private String currency;
	private String receipt;
//	private String notes="{}";
	private boolean partial_payment;

//	public String getRazorPayOrderID() {
//		return razorPayOrderID;
//	}
//
//	public void setRazorPayOrderID(String razorPayOrderID) {
//		this.razorPayOrderID = razorPayOrderID;
//	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

//	public String getNotes() {
//		return notes;
//	}
//
//	public void setNotes(String notes) {
//		this.notes = notes;
//	}

	public boolean getPartial_payment() {
		return partial_payment;
	}

	public void setPartial_payment(boolean partialPayment) {
		this.partial_payment = partialPayment;
	}
}
