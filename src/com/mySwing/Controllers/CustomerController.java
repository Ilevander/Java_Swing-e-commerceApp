package com.mySwing.Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mySwin.Models.Customer;
import com.mySwing.Services.CustomerService;
import com.mySwing.database.DatabaseUtil;

public class CustomerController implements CustomerService {

    @Override
    public void addCustomer(Customer customer) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO customer (customerName, email, contactNumber) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, customer.getCustomerName());
                preparedStatement.setString(2, customer.getEmail());
                preparedStatement.setString(3, customer.getContactNumber());

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Adding customer failed, no rows affected.");
                }

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        customer.setCustomerId(generatedKeys.getInt(1)); // Use index 1 for ID
                    } else {
                        throw new SQLException("Adding customer failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCustomer(Connection connection2, Customer customer) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "UPDATE customer SET customerName = ?, email = ?, contactNumber = ? WHERE customerId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, customer.getCustomerName());
                preparedStatement.setString(2, customer.getEmail());
                preparedStatement.setString(3, customer.getContactNumber());
                preparedStatement.setInt(4, customer.getCustomerId());
                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Customer updated: " + customer);
                } else {
                    System.out.println("Customer not found for update: " + customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(int customerId) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "DELETE FROM customer WHERE customerId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, customerId);
                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Customer deleted with ID: " + customerId);
                } else {
                    System.out.println("Customer not found for deletion with ID: " + customerId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> allCustomers = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM customer";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        Customer customer = new Customer();
                        customer.setCustomerId(resultSet.getInt("customerId"));
                        customer.setCustomerName(resultSet.getString("customerName"));
                        customer.setEmail(resultSet.getString("email"));
                        customer.setContactNumber(resultSet.getString("contactNumber"));

                        allCustomers.add(customer);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allCustomers;
    }

    @Override
    public Customer getCustomerById(Connection connection2, int customerId) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM customer WHERE customerId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, customerId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Customer customer = new Customer();
                        customer.setCustomerId(resultSet.getInt("customerId"));
                        customer.setCustomerName(resultSet.getString("customerName"));
                        customer.setEmail(resultSet.getString("email"));
                        customer.setContactNumber(resultSet.getString("contactNumber"));
                        System.out.println("Getting customer by ID: " + customerId + " - " + customer);
                        return customer;
                    } else {
                        System.out.println("Customer not found for ID: " + customerId);
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateCustomer1(Connection connection, Customer customer) throws SQLException {
        String sql = "UPDATE customer SET customerName = ?, email = ?, contactNumber = ? WHERE customerId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getContactNumber());
            preparedStatement.setInt(4, customer.getCustomerId());

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Customer updated: " + customer);
            } else {
                System.out.println("Customer not found for update: " + customer);
            }
        }
    }

    @Override
    public Customer getCustomerById1(Connection connection, int customerId) throws SQLException {
        String sql = "SELECT * FROM customer WHERE customerId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(resultSet.getInt("customerId"));
                    customer.setCustomerName(resultSet.getString("customerName"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setContactNumber(resultSet.getString("contactNumber"));
                    System.out.println("Getting customer by ID: " + customerId + " - " + customer);
                    return customer;
                } else {
                    System.out.println("Customer not found for ID: " + customerId);
                    return null;
                }
            }
        }
    }

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
