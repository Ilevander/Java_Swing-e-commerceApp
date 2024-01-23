package com.mySwin.Models;
	
//An Order can have multiple payments.
//A Product can be associated with a single supplier.

import java.sql.Date;

public class Payement {

	private int paymentId;
    private Order order;
    private double amount;
    private Date paymentDate;
     
      
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
	public Payement(int paymentId, Order order, double amount, Date paymentDate) {
		super();
		this.paymentId = paymentId;
		this.order = order;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}
	
	public Payement() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "Payement [paymentId=" + paymentId + ", order=" + order + ", amount=" + amount + ", paymentDate="
				+ paymentDate + "]";
	}
	
	
	
	
    
    
}
