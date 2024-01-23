package com.mySwing.Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.mySwing.Controllers.CustomerController;
import com.mySwin.Models.Customer;
import com.mySwing.database.DatabaseUtil;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
    private CustomerController customerController;

    public MainView() {
        this.customerController = new CustomerController();
        initComponents();
    }   

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("E-commerce App");

        // Main Panel
        JPanel mainPanel = new JPanel();  
   
//        JButton showCustomersButton = new JButton("Show Customers");
//        JButton adminDashboardButton = new JButton("Admin Dashboard");   
        JButton loginButton = new JButton("Login"); 
        
//        showCustomersButton.addActionListener(new ActionListener() { 
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                showCustomerManagementView();
//            }
//        });
//
//        adminDashboardButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                openAdminDashboard();
//            }
//        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginView();
            }
        });
        
//        mainPanel.add(showCustomersButton);
//        mainPanel.add(adminDashboardButton);
        mainPanel.add(loginButton);
        
        // Add mainPanel to the frame
        add(mainPanel);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

//    private void showCustomerManagementView() {
//        SwingUtilities.invokeLater(() -> new CustomerDashboard().setVisible(true));
//    }
//
//    private void openAdminDashboard() {
//        SwingUtilities.invokeLater(() -> new AdminDashboard().setVisible(true));
//    }  

    private void openLoginView() {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
    
    
       
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainView().setVisible(true));
    }
    
    

//    private void initComponents() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setTitle("E-commerce App");
//
//        // Main Panel
//        JPanel mainPanel = new JPanel();
//        JButton showCustomersButton = new JButton("Show Customers");
//
//        showCustomersButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                showCustomerManagementView();
//            }
//        });
//
//        mainPanel.add(showCustomersButton);
//
//        // Add mainPanel to the frame
//        add(mainPanel);
//
//        pack();
//        setLocationRelativeTo(null); // Center the frame on the screen
//    }
//
//    private void showCustomerManagementView() {
//        JFrame customerManagementFrame = new JFrame("Customer Management");
//        JPanel customerManagementPanel = new JPanel();
//
//        // Add buttons and actions related to customer operations
//        JButton addCustomerButton = new JButton("Add Customer");
//        JButton updateCustomerButton = new JButton("Update Customer");
//        JButton deleteCustomerButton = new JButton("Delete Customer");
//        JButton showAllCustomersButton = new JButton("Show All Customers");
//
//        addCustomerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Placeholder logic to add a customer
//                Customer customer = new Customer();
//                customer.setCustomerName("John Doe");
//                customer.setEmail("john@example.com");
//                customer.setContactNumber("1234567890");
//                customerController.addCustomer(customer);
//                JOptionPane.showMessageDialog(null, "Customer added successfully!");
//            }
//        });
//
//        updateCustomerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Placeholder logic to update a customer
//                Customer customer = new Customer();
//                customer.setCustomerId(1); // Provide an existing customer ID
//                customer.setCustomerName("Updated Name");
//                customer.setEmail("updated@example.com");
//                customer.setContactNumber("9876543210");
//                customerController.updateCustomer(customer);
//                JOptionPane.showMessageDialog(null, "Customer updated successfully!");
//            }
//        });
//
//        deleteCustomerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Placeholder logic to delete a customer
//                int customerIdToDelete = 1; // Provide an existing customer ID
//                customerController.deleteCustomer(customerIdToDelete);
//                JOptionPane.showMessageDialog(null, "Customer deleted successfully!");
//            }
//        });
//
//        showAllCustomersButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Use the new method to get all customers using DatabaseUtil
//                List<Customer> allCustomers = customerController.getAllCustomers();
//                for (Customer customer : allCustomers) {
//                    System.out.println(customer);
//                }
//            }
//        });
//
//        customerManagementPanel.add(addCustomerButton);
//        customerManagementPanel.add(updateCustomerButton);
//        customerManagementPanel.add(deleteCustomerButton);
//        customerManagementPanel.add(showAllCustomersButton);
//
//        // Add customerManagementPanel to the frame
//        customerManagementFrame.add(customerManagementPanel);
//        customerManagementFrame.setSize(400, 200);
//        customerManagementFrame.setLocationRelativeTo(null); // Center the frame on the screen
//        customerManagementFrame.setVisible(true);
//    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//        	public void run() {
//        		new MainView().setVisible(true);
//        		}
//        		});
//        }
   }
