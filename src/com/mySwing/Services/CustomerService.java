package com.mySwing.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.mySwin.Models.Customer;

public interface CustomerService {
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int customerId);
    List<Customer> getAllCustomers();
    Customer getCustomerById(int customerId);
	Customer getCustomerById(Connection connection2, int customerId);
	void updateCustomer(Connection connection2, Customer customer);
	void updateCustomer1(Connection connection, Customer customer) throws SQLException;
	Customer getCustomerById1(Connection connection, int customerId) throws SQLException;

}
