package com.mySwin.Models;

import java.util.List;

public class User {
	
	    private String username;
	    private String password;   
	    private String fullName;
	    private String email;
	    private List<String> roles;
	    
	    
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public List<String> getRoles() {
			return roles;
		}
		public void setRoles(List<String> roles) {
			this.roles = roles;
		}
		
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public User(String username, String password, List<String> roles) {
			super();
			this.username = username;
			this.password = password;
			this.roles = roles;
		}
		
		public User() {
			super();
		}
		
		@Override
		public String toString() {
			return "User [username=" + username + ", password=" + password + ", roles=" + roles + "]";
		}

	    
		
}
