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
  
public class AdminProductsCategoriesView extends JFrame {

	private static final long serialVersionUID = 1L;

	public AdminProductsCategoriesView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Admin Products Categories Management");

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton viewCategoriesButton = new JButton("View Categories");
        JButton updateCategoryButton = new JButton("Update Category");
        JButton deleteCategoryButton = new JButton("Delete Category");
        JButton addCategoryButton = new JButton("Add Category");

        // Action Listeners
        viewCategoriesButton.addActionListener(e -> viewCategories());
        updateCategoryButton.addActionListener(e -> updateCategory());
        deleteCategoryButton.addActionListener(e -> deleteCategory());
        addCategoryButton.addActionListener(e -> addCategory());

        // Adding components to the main panel
        mainPanel.add(viewCategoriesButton);
        mainPanel.add(updateCategoryButton);
        mainPanel.add(deleteCategoryButton);
        mainPanel.add(addCategoryButton);

        // Adding main panel to the frame
        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void viewCategories() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM category";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<String> categories = new ArrayList<>();
                    while (resultSet.next()) {
                        int categoryId = resultSet.getInt("categoryId");
                        String categoryName = resultSet.getString("categoryName");
                        categories.add("Category ID: " + categoryId + ", Category Name: " + categoryName);
                    }

                    if (!categories.isEmpty()) {
                        String categoriesList = String.join("\n", categories);
                        JOptionPane.showMessageDialog(this, "Category List:\n" + categoriesList);
                    } else {
                        JOptionPane.showMessageDialog(this, "No categories found.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }



    private void updateCategory() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            int productId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter product ID to update category:"));
            int newCategoryId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new category ID:"));

            // Check if the product exists
            if (productExists(connection, productId)) {
                String sql = "UPDATE product_category SET categoryId = ? WHERE productId = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, newCategoryId);
                    preparedStatement.setInt(2, productId);

                    int rowsUpdated = preparedStatement.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(this, "Category updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update category.");
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

    private void deleteCategory() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            int productId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter product ID to delete category:"));

            // Check if the product exists
            if (productExists(connection, productId)) {
                int confirmResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the category?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                if (confirmResult == JOptionPane.YES_OPTION) {
                    String sql = "DELETE FROM product_category WHERE productId = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        preparedStatement.setInt(1, productId);

                        int rowsDeleted = preparedStatement.executeUpdate();

                        if (rowsDeleted > 0) {
                            JOptionPane.showMessageDialog(this, "Category deleted successfully!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Failed to delete category.");
                        }
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

    private void addCategory() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            int productId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter product ID:"));
            int categoryId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter category ID:"));

            // Check if the product already has a category
            if (!productExists(connection, productId)) {
                String sql = "INSERT INTO product_category (productId, categoryId) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, productId);
                    preparedStatement.setInt(2, categoryId);

                    int rowsInserted = preparedStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(this, "Category added successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to add category.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Product already has a category.");
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private boolean productExists(Connection connection, int productId) throws SQLException {
        String sql = "SELECT * FROM product WHERE productId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    
}
