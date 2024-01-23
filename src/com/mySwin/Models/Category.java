package com.mySwin.Models;

import java.util.List;

//A Product can belong to one or more categories.

public class Category {

	    private int categoryId;
	    private String categoryName;
	    //cardinality with products:
	    private List<Product> products;
	    
	       
	    
		public int getCategoryId() {   
			return categoryId;
		}
		public void setCategoryId(int categoryId) {
			this.categoryId = categoryId;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		public List<Product> getProducts() {
	        return products;
	    }

	    public void setProducts(List<Product> products) {
	        this.products = products;
	    }
	    
		
		public Category(int categoryId, String categoryName) {
			super();
			this.categoryId = categoryId;
			this.categoryName = categoryName;
		}
		
		public Category() {
			super();
		}
		
		@Override
		public String toString() {
			return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
		}
	    
		
		
	    
}
