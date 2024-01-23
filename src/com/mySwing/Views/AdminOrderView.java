package com.mySwing.Views;

import com.mySwing.database.DatabaseUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminOrderView extends JFrame {

    public AdminOrderView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Admin Order Management");

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton viewOrdersButton = new JButton("View Orders");
        JButton updateOrderButton = new JButton("Update Order");
        JButton deleteOrderButton = new JButton("Delete Order");
        JButton addOrderButton = new JButton("Add Order");

        // Action Listeners
        viewOrdersButton.addActionListener(e -> viewOrders());
        updateOrderButton.addActionListener(e -> updateOrder());
        deleteOrderButton.addActionListener(e -> deleteOrder());
        addOrderButton.addActionListener(e -> addOrder());

        // Adding components to the main panel
        mainPanel.add(viewOrdersButton);
        mainPanel.add(updateOrderButton);
        mainPanel.add(deleteOrderButton);
        mainPanel.add(addOrderButton);

        // Adding main panel to the frame
        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void viewOrders() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM `Order`";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<String> orders = new ArrayList<>();
                    while (resultSet.next()) {
                        int orderId = resultSet.getInt("orderId");
                        String customerName = resultSet.getString("customerName");
                        int customerId = resultSet.getInt("customerId");
                        String sellerUsername = resultSet.getString("sellerUsername");

                        orders.add("Order ID: " + orderId +
                                ", Customer Name: " + customerName +
                                ", Customer ID: " + customerId +
                                ", Seller Username: " + sellerUsername);
                    }   

                    if (!orders.isEmpty()) {
                        String ordersList = String.join("\n", orders);
                        JOptionPane.showMessageDialog(this, "Order List:\n" + ordersList);
                    } else {
                        JOptionPane.showMessageDialog(this, "No orders found.");
                    }
                }
            }
        } catch (SQLException ex) {  
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updateOrder() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            // Prompt for new username
            String newSellerUsername = JOptionPane.showInputDialog(this, "Enter new seller username:");

            // Check if the new sellerUsername exists in the user table
            if (sellerUsernameExists(connection, newSellerUsername)) {
                // Proceed with the updateOrder logic
                int orderId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter order ID to update:"));
                String sql = "UPDATE `order` SET sellerUsername = ? WHERE orderId = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, newSellerUsername);
                    preparedStatement.setInt(2, orderId);   

                    int rowsUpdated = preparedStatement.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(this, "Order updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update order.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seller username does not exist in the user table.");
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // Check if sellerUsername exists in the user table
    private boolean sellerUsernameExists(Connection connection, String sellerUsername) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, sellerUsername);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }


    private void deleteOrder() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            int orderId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter order ID to delete:"));

            if (orderExists(connection, orderId)) {
                int confirmResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the order?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                if (confirmResult == JOptionPane.YES_OPTION) {
                    String sql = "DELETE FROM `Order` WHERE orderId = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        preparedStatement.setInt(1, orderId);

                        int rowsDeleted = preparedStatement.executeUpdate();

                        if (rowsDeleted > 0) {
                            JOptionPane.showMessageDialog(this, "Order deleted successfully!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Failed to delete order.");
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Order not found.");
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // ... Remaining code


    private void addOrder() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            int orderId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter order ID:"));
            String customerName = JOptionPane.showInputDialog(this, "Enter customer name:");
            int customerId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter customer ID:"));
            String sellerUsername = JOptionPane.showInputDialog(this, "Enter seller username:");

            if (!orderExists(connection, orderId)) {
                String sql = "INSERT INTO `Order` (orderId, customerName, customerId, sellerUsername) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, orderId);
                    preparedStatement.setString(2, customerName);
                    preparedStatement.setInt(3, customerId);
                    preparedStatement.setString(4, sellerUsername);

                    int rowsInserted = preparedStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(this, "Order added successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to add order.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Order already exists.");
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private boolean orderExists(Connection connection, int orderId) throws SQLException {
        String sql = "SELECT * FROM `Order` WHERE orderId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    
}
