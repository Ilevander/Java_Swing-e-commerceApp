package com.mySwing.Views;

import javax.swing.*;

import com.mySwin.Models.Product;
import com.mySwing.database.DatabaseUtil;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductView extends JFrame {

    public ProductView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Product View");

        // Main Panel
        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // 2 columns
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Fetch and display products
        List<Product> productList = fetchProducts();
        for (Product product : productList) {
            // Display product information
            JPanel productPanel = createProductPanel(product);
            mainPanel.add(productPanel);
        }

        // Add mainPanel to the frame
        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private List<Product> fetchProducts() {
        List<Product> productList = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM product";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int productId = resultSet.getInt("productId");
                        String productName = resultSet.getString("productName");
                        double price = resultSet.getDouble("price");

                        // Create Product object and add to the list
                        Product product = new Product(productId, productName, price);
                        productList.add(product);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    private JPanel createProductPanel(Product product) {
        JPanel productPanel = new JPanel(new BorderLayout());
        productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel nameLabel = new JLabel("Product: " + product.getProductName());
        JLabel priceLabel = new JLabel("Price: $" + product.getPrice());

        productPanel.add(nameLabel, BorderLayout.NORTH);
        productPanel.add(priceLabel, BorderLayout.SOUTH);

        return productPanel;
    }

   
}
