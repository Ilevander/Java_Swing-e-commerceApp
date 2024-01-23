package com.mySwing.Views;

import com.mySwing.database.DatabaseUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminProductsView extends JFrame {

    public AdminProductsView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Admin Products Management");

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton viewProductsButton = new JButton("View Products");
        JButton updateProductButton = new JButton("Update Product");
        JButton deleteProductButton = new JButton("Delete Product");
        JButton addProductButton = new JButton("Add Product");

        // Action Listeners
        viewProductsButton.addActionListener(e -> viewProducts());
        updateProductButton.addActionListener(e -> updateProduct());
        deleteProductButton.addActionListener(e -> deleteProduct());
        addProductButton.addActionListener(e -> addProduct());

        // Adding components to the main panel
        mainPanel.add(viewProductsButton);
        mainPanel.add(updateProductButton);
        mainPanel.add(deleteProductButton);
        mainPanel.add(addProductButton);

        // Adding main panel to the frame
        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void viewProducts() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM product";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<String> products = new ArrayList<>();
                    while (resultSet.next()) {
                        String productName = resultSet.getString("productName");
                        double price = resultSet.getDouble("price");
                        int supplierId = resultSet.getInt("supplierId");
                        products.add("Product Name: " + productName + ", Price: " + price + ", Supplier ID: " + supplierId);
                    }

                    if (!products.isEmpty()) {
                        String productList = String.join("\n", products);
                        JOptionPane.showMessageDialog(this, "Product List:\n" + productList);
                    } else {
                        JOptionPane.showMessageDialog(this, "No products found.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }    

    private void updateProduct() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String productName = JOptionPane.showInputDialog(this, "Enter product name to update:");

            // Check if the product exists
            if (productExists(connection, productName)) {
                double newPrice = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter new price:"));
                int newSupplierId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new supplier ID:"));

                String sql = "UPDATE product SET price = ?, supplierId = ? WHERE productName = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setDouble(1, newPrice);
                    preparedStatement.setInt(2, newSupplierId);
                    preparedStatement.setString(3, productName);

                    int rowsUpdated = preparedStatement.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(this, "Product updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update product.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Product not found.");
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }   

    private void deleteProduct() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String productName = JOptionPane.showInputDialog(this, "Enter product name to delete:");

            // Check if the product exists
            if (productExists(connection, productName)) {
                int confirmResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the product?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                if (confirmResult == JOptionPane.YES_OPTION) {
                    String sql = "DELETE FROM product WHERE productName = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        preparedStatement.setString(1, productName);

                        int rowsDeleted = preparedStatement.executeUpdate();

                        if (rowsDeleted > 0) {
                            JOptionPane.showMessageDialog(this, "Product deleted successfully!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Failed to delete product.");
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Product not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void addProduct() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String productName = JOptionPane.showInputDialog(this, "Enter product name:");
            double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter price:"));
            int supplierId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter supplier ID:"));

            // Check if the product already exists
            if (!productExists(connection, productName)) {
                String sql = "INSERT INTO product (productName, price, supplierId) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, productName);
                    preparedStatement.setDouble(2, price);
                    preparedStatement.setInt(3, supplierId);

                    int rowsInserted = preparedStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(this, "Product added successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to add product.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Product already exists.");
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // Helper method to check if a product exists in the database
    private boolean productExists(Connection connection, String productName) throws SQLException {
        String sql = "SELECT * FROM product WHERE productName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, productName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if the product exists
            }
        }
    }
}
