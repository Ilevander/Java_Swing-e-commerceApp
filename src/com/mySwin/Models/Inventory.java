package com.mySwin.Models;

//to track the inventory of products, I create an InventoryItem class that represents the stock quantity of each product.

public class Inventory {
	
	private Product product;
    private int quantity;
    
       
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Inventory(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Inventory [product=" + product + ", quantity=" + quantity + "]";
	}
	
	
    
    
}
