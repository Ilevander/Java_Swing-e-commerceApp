package com.mySwin.Models;

import java.util.List;
  
//An Order is associated with one customer.

public class Customer {

	private int customerId;
    private String customerName;
    private String contactNumber;
    private String email;
    //cardinality with orderq
    private List<Order> orders;

    // Constructors, getters, setters

	public int getCustomerId() {
		return customerId;
	}      
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    //Constructors
	public Customer(int customerId, String customerName, String contactNumber) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.contactNumber = contactNumber;
	}
	
	public Customer() {
		super();
	}
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", contactNumber="
				+ contactNumber + ", orders=" + orders + "]";
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}

	
	
	
	
	
    
}
