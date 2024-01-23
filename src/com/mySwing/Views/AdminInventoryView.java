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

public class AdminInventoryView extends JFrame {

    public AdminInventoryView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Inventory Management");

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton viewInventoryButton = new JButton("View Inventory");
        JButton updateInventoryButton = new JButton("Update Inventory");
        JButton deleteInventoryButton = new JButton("Delete Inventory");
        JButton addInventoryButton = new JButton("Add Inventory");

        // Action Listeners
        viewInventoryButton.addActionListener(e -> viewInventory());
        updateInventoryButton.addActionListener(e -> updateInventory());
        deleteInventoryButton.addActionListener(e -> deleteInventory());
        addInventoryButton.addActionListener(e -> addInventory());

        // Adding components to the main panel
        mainPanel.add(viewInventoryButton);
        mainPanel.add(updateInventoryButton);
        mainPanel.add(deleteInventoryButton);
        mainPanel.add(addInventoryButton);

        // Adding main panel to the frame
        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void viewInventory() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM Inventory";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<String> inventoryItems = new ArrayList<>();
                    while (resultSet.next()) {
                        int productId = resultSet.getInt("productId");
                        int quantity = resultSet.getInt("quantity");
                        inventoryItems.add("Product ID: " + productId + ", Quantity: " + quantity);
                    }

                    if (!inventoryItems.isEmpty()) {
                        String inventoryList = String.join("\n", inventoryItems);
                        JOptionPane.showMessageDialog(this, "Inventory List:\n" + inventoryList);
                    } else {
                        JOptionPane.showMessageDialog(this, "No items found in inventory.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updateInventory() {
        // Implement logic to update inventory
        // You can show a form to input updated quantity and execute update query
        JOptionPane.showMessageDialog(this, "Update Inventory clicked!");
    }

    private void deleteInventory() {
        // Implement logic to delete inventory item
        // You can show a confirmation dialog, execute delete query
        JOptionPane.showMessageDialog(this, "Delete Inventory clicked!");
    }

    private void addInventory() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            int productId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter product ID:"));
            int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter quantity:"));

            // Check if the product is already in the inventory
            if (!inventoryItemExists(connection, productId)) {
                String sql = "INSERT INTO Inventory (productId, quantity) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, productId);
                    preparedStatement.setInt(2, quantity);

                    int rowsInserted = preparedStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(this, "Inventory item added successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to add inventory item.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Product already exists in inventory. Use update to modify quantity.");
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private boolean inventoryItemExists(Connection connection, int productId) throws SQLException {
        String sql = "SELECT * FROM Inventory WHERE productId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    
}
