package com.mySwin.Models;

import java.sql.Timestamp;
//An Order is associated with one customer.
//An Order can have multiple payments.
import java.util.List;

public class Order {
	private int orderId;
    private String customerName;
    private List<Product> products;
    //cardinality with customers
    private Customer customer;
    //cardinality to payement
    private List<Payement> payments;


    //Constructors
    public Order(int orderId, String customerName, List<Product> products) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.products = products;
    }

    public Order() {
		super();
	}

	// Getters and setters

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {	
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public List<Payement> getPayments() {
        return payments;
    }

    public void setPayments(List<Payement> payments) {
        this.payments = payments;
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", products=" + products +
                '}';
    }

    public void setOrderId(Timestamp timestamp) {
        long orderId = timestamp.getTime();
        this.orderId = (int) orderId; 
    }

}
