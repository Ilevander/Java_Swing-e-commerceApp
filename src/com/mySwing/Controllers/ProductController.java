package com.mySwing.Controllers;

import com.mySwin.Models.Category;
import com.mySwin.Models.Product;
import com.mySwing.Services.ProductService;
import com.mySwing.database.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductController implements ProductService {

    @Override
    public void addProduct(Product product) {
        // Placeholder logic to add a product
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO product (productName, price) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, product.getProductName());
                preparedStatement.setDouble(2, product.getPrice());

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Adding product failed, no rows affected.");
                }

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        product.setProductId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Adding product failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product product) {
        // Placeholder logic to update a product
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "UPDATE product SET productName = ?, price = ? WHERE productId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, product.getProductName());
                preparedStatement.setDouble(2, product.getPrice());
                preparedStatement.setInt(3, product.getProductId());

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Product updated: " + product);
                } else {
                    System.out.println("Product not found for update: " + product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int productId) {
        // Placeholder logic to delete a product
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "DELETE FROM product WHERE productId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, productId);

                int rowsDeleted = preparedStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Product deleted with ID: " + productId);
                } else {
                    System.out.println("Product not found for deletion with ID: " + productId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        // Placeholder logic to get all products
        List<Product> allProducts = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM product";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        Product product = new Product();
                        product.setProductId(resultSet.getInt("productId"));
                        product.setProductName(resultSet.getString("productName"));
                        product.setPrice(resultSet.getDouble("price"));

                        allProducts.add(product);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allProducts;
    }

    @Override
    public Product getProductById(int productId) {
        // Placeholder logic to get a product by ID
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM product WHERE productId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, productId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Product product = new Product();
                        product.setProductId(resultSet.getInt("productId"));
                        product.setProductName(resultSet.getString("productName"));
                        product.setPrice(resultSet.getDouble("price"));

                        System.out.println("Getting product by ID: " + productId + " - " + product);
                        return product;
                    } else {
                        System.out.println("Product not found for ID: " + productId);
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
    public List<Product> getProductsByCategory(Category category) {
        // Placeholder logic to get products by category
        List<Product> productsByCategory = new ArrayList<>();
        // Add your specific logic based on your data model
        System.out.println("Getting products by category: " + category + " - " + productsByCategory);
        return productsByCategory;
    }
}
