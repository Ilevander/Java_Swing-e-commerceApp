package com.mySwin.Models;

import java.util.List;

public class Supplier {

	private int supplierId;
    private String supplierName;
    private String contactInformation;
    private String email;
    private List<Product> products;

 
    
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getContactInformation() {
		return contactInformation;
	}
	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}
	
	public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
   
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Supplier(int supplierId, String supplierName, String contactInformation) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.contactInformation = contactInformation;
	}
	
	public Supplier() {
		super();
	}
	
	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + ", contactInformation="
				+ contactInformation + "]";
	}
	
	
	
	
    
    
}
