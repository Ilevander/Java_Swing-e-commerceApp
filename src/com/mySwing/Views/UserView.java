package com.mySwing.Views;

import javax.swing.*;

import com.mySwing.database.DatabaseUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserView extends JFrame {

    private static final long serialVersionUID = 1L;

    public UserView() {
        initComponents();
    }

private void initComponents() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("User Management");

    JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

    JLabel usernameLabel = new JLabel("Username:");
    JTextField usernameField = new JTextField(15);

    JLabel passwordLabel = new JLabel("Password:");
    JTextField passwordField = new JTextField(15);

    JLabel fullNameLabel = new JLabel("Full Name:");
    JTextField fullNameField = new JTextField(15);

    JLabel emailLabel = new JLabel("Email:");
    JTextField emailField = new JTextField(15);

    JButton addUserButton = new JButton("Add User");
    JButton updateUserButton = new JButton("Update User");
    JButton deleteUserButton = new JButton("Delete User");
    JButton viewUsersButton = new JButton("View Users");

    // Action Listeners
    addUserButton.addActionListener(e -> addUser(
            usernameField.getText(),
            passwordField.getText(),
            fullNameField.getText(),
            emailField.getText()
    ));

    updateUserButton.addActionListener(e -> updateUser(
            usernameField.getText(),
            passwordField.getText(),
            fullNameField.getText(),
            emailField.getText()
    ));

    deleteUserButton.addActionListener(e -> deleteUser(usernameField.getText()));

    viewUsersButton.addActionListener(e -> viewUsers());

    // Adding components to the main panel
    mainPanel.add(usernameLabel);
    mainPanel.add(usernameField);
    mainPanel.add(passwordLabel);
    mainPanel.add(passwordField);
    mainPanel.add(fullNameLabel);
    mainPanel.add(fullNameField);
    mainPanel.add(emailLabel);
    mainPanel.add(emailField);
    
    mainPanel.add(addUserButton);
    mainPanel.add(updateUserButton);
    mainPanel.add(deleteUserButton);
    mainPanel.add(viewUsersButton);

    // Adding main panel to the frame
    add(mainPanel);

    pack();
    setLocationRelativeTo(null);
}

    private void addUser(String username, String password, String fullName, String email) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO user (username, password, fullName, email) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, fullName);
                preparedStatement.setString(4, email);

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "User added successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add user.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void viewUsers() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM user";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<String> users = new ArrayList<>();
                    while (resultSet.next()) {
                        String username = resultSet.getString("username");
                        String fullName = resultSet.getString("fullName");
                        String email = resultSet.getString("email");
                        users.add("Username: " + username + ", Full Name: " + fullName + ", Email: " + email);
                    }

                    if (!users.isEmpty()) {
                        String userList = String.join("\n", users);
                        JOptionPane.showMessageDialog(this, "User List:\n" + userList);
                    } else {
                        JOptionPane.showMessageDialog(this, "No users found.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updateUser(String username, String password, String fullName, String email) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "UPDATE user SET password = ?, fullName = ?, email = ? WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, password);
                preparedStatement.setString(2, fullName);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, username);

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "User updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update user. User not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteUser(String username) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "DELETE FROM user WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);

                int rowsDeleted = preparedStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "User deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete user. User not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    
}
