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

public class LoginView extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginView() {
        initComponents();
    }  

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login");

        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> register());

        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(registerButton);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

//    private void login() {
//        String username = usernameField.getText();
//        String password = new String(passwordField.getPassword());
//
//        try (Connection connection = DatabaseUtil.getConnection()) {
//            // Check if the user is the admin
//            if (isAdmin(username, password, connection)) {
//                JOptionPane.showMessageDialog(this, "Admin login successful!");
//                openAdminDashboard();
//            } else {
//                // Check for other users
//                String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
//                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                    preparedStatement.setString(1, username);
//                    preparedStatement.setString(2, password);
//
//                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                        if (resultSet.next()) {
//                            JOptionPane.showMessageDialog(this, "Login successful!");
//                            openCustomerDashboard();
//                        } else {
//                            JOptionPane.showMessageDialog(this, "Invalid username or password.");
//                        }
//                    }
//                }
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
//        }
//    }
//
//    private boolean isAdmin(String username, String password, Connection connection) throws SQLException {
//        // Check if the provided credentials match the admin's credentials
//        String adminSql = "SELECT * FROM User WHERE username = 'ilyass-admin@gmail.com' AND password = ?";
//        try (PreparedStatement adminStatement = connection.prepareStatement(adminSql)) {
//            adminStatement.setString(1, password);
//
//            try (ResultSet adminResultSet = adminStatement.executeQuery()) {
//                return adminResultSet.next();
//            }
//        }
//    }

//    private void login() {
//        String username = usernameField.getText();
//        String password = new String(passwordField.getPassword());
//
//        try (Connection connection = DatabaseUtil.getConnection()) {
//            String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setString(1, username);
//                preparedStatement.setString(2, password);
//
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    if (resultSet.next()) {
//                        JOptionPane.showMessageDialog(this, "Login successful!");
//                        openAdminDashboard();
//                    } else {
//                        // If not found in the first row, check other rows
//                        if (verifyFromOtherRows(connection, username, password)) {
//                            JOptionPane.showMessageDialog(this, "Login successful!");
//                            openCustomerDashboard();
//                        } else {
//                            JOptionPane.showMessageDialog(this, "Invalid username or password.");
//                        }
//                    }
//                }
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
//        }
//    }
//
//    private boolean verifyFromOtherRows(Connection connection, String username, String password) throws SQLException {
//        String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setString(1, username);
//            preparedStatement.setString(2, password);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                return resultSet.next();
//            }
//        }
//    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection connection = DatabaseUtil.getConnection()) {
            if ("admin".equals(username) && "admin".equals(password)) {
                JOptionPane.showMessageDialog(this, "Admin Login successful!");
                openAdminDashboard();
            } else {
                // Check if the user is a customer
                if (isValidCustomer(username, password, connection)) {
                    JOptionPane.showMessageDialog(this, "Customer Login successful!");
                    openCustomerDashboard();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private boolean isValidCustomer(String username, String password, Connection connection) throws SQLException {
        String customerSql = "SELECT * FROM User WHERE username = ? AND password = ?";
        try (PreparedStatement customerStatement = connection.prepareStatement(customerSql)) {
            customerStatement.setString(1, username);
            customerStatement.setString(2, password);

            try (ResultSet customerResultSet = customerStatement.executeQuery()) {
                return customerResultSet.next();
            }
        }
    }

    
   
    private void register() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password are required.");
            return;
        }

        try (Connection connection = DatabaseUtil.getConnection()) {
            // Check if the username already exists
            if (usernameExists(connection, username)) {
                JOptionPane.showMessageDialog(this, "Username already exists. Choose a different one.");
                return;
            }

            // Hash the password (you should use a stronger hash function in a real application)
            String hashedPassword = hashPassword(password);

            String sql = "INSERT INTO User (username, password) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, hashedPassword);

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Registration successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to register user.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private boolean usernameExists(Connection connection, String username) throws SQLException {
        String sql = "SELECT * FROM User WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    // In a real application, you should use a stronger hashing algorithm
    private String hashPassword(String password) {
        // This is a simple example; use a library like BCrypt in a production environment
        return password; // Replace with actual hashing logic
    }


    private void openAdminDashboard() {
        SwingUtilities.invokeLater(() -> new AdminDashboard().setVisible(true));
        this.dispose();
    }
    
    private void openCustomerDashboard() {
        SwingUtilities.invokeLater(() -> new CustomerDashboard().setVisible(true));
        this.dispose();
    }

   
}
