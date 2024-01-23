package com.mySwing.Views;

import com.mySwin.Models.Customer;
import com.mySwing.Controllers.CustomerController;
import com.mySwing.database.DatabaseUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerProfileView extends JFrame {

	private static final long serialVersionUID = 1L;

    private CustomerController customerController;
    private int customerId;

    private JTextField nameField;
    private JTextField emailField;
    private JTextField contactNumberField;

    public CustomerProfileView(int customerId) {
        this.customerController = new CustomerController();
        this.customerId = customerId;

        initComponents();
        loadCustomerData();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Customer Profile");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel contactNumberLabel = new JLabel("Contact Number:");
        contactNumberField = new JTextField();

        JButton updateButton = new JButton("Update");
        JButton registerButton = new JButton("Register");
        JButton showButton = new JButton("Show Details");

        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(emailLabel);
        mainPanel.add(emailField);
        mainPanel.add(contactNumberLabel);
        mainPanel.add(contactNumberField);
        mainPanel.add(updateButton);
        mainPanel.add(registerButton);
        mainPanel.add(showButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomer();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerCustomer();
            }
        });
        
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call showCustomerDetails with the customer ID (replace 1 with the actual customer ID)
                showCustomerDetails(1);
            }
        });
        showButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void loadCustomerData() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            Customer customer = customerController.getCustomerById(connection, customerId);
            if (customer != null) {
                nameField.setText(customer.getCustomerName());
                emailField.setText(customer.getEmail());
                contactNumberField.setText(customer.getContactNumber());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateCustomer() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            Customer customer = new Customer();
            customer.setCustomerId(customerId);
            customer.setCustomerName(nameField.getText());
            customer.setEmail(emailField.getText());
            customer.setContactNumber(contactNumberField.getText());

            customerController.updateCustomer(connection, customer);
            JOptionPane.showMessageDialog(this, "Customer information updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void registerCustomer() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            Customer customer = new Customer();
            customer.setCustomerName(nameField.getText());
            customer.setEmail(emailField.getText());
            customer.setContactNumber(contactNumberField.getText());

            customerController.updateCustomer(connection, customer);
            JOptionPane.showMessageDialog(this, "New customer registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void showCustomerDetails(int customerId) {
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

                        JOptionPane.showMessageDialog(this, "Customer Details:\n" +
                                "ID: " + customer.getCustomerId() +
                                "\nName: " + customer.getCustomerName() +
                                "\nEmail: " + customer.getEmail() +
                                "\nContact Number: " + customer.getContactNumber());
                    } else {
                        JOptionPane.showMessageDialog(this, "Customer not found!");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showCustomerDetails(Connection connection, int customerId) {
        Customer customer = getCustomerById(connection, customerId);
		if (customer != null) {
		    System.out.println("Customer Details:\n" +
		            "ID: " + customer.getCustomerId() +
		            "\nName: " + customer.getCustomerName() +
		            "\nEmail: " + customer.getEmail() +
		            "\nContact Number: " + customer.getContactNumber());
		} else {
		    System.out.println("Customer not found for ID: " + customerId);
		}
    }

	private Customer getCustomerById(Connection connection, int customerId2) {
		// TODO Auto-generated method stub
		return null;
	}
}
