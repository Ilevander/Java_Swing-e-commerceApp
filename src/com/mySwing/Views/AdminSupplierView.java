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

public class AdminSupplierView extends JFrame {

    public AdminSupplierView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Admin Supplier Management");

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton viewSuppliersButton = new JButton("View Suppliers");
        JButton addSupplierButton = new JButton("Add Supplier");
        JButton updateSupplierButton = new JButton("Update Supplier");
        JButton deleteSupplierButton = new JButton("Delete Supplier");

        // Action Listeners
        viewSuppliersButton.addActionListener(e -> viewSuppliers());
        addSupplierButton.addActionListener(e -> addSupplier());
        updateSupplierButton.addActionListener(e -> updateSupplier());
        deleteSupplierButton.addActionListener(e -> deleteSupplier());

        // Adding components to the main panel
        mainPanel.add(viewSuppliersButton);
        mainPanel.add(addSupplierButton);
        mainPanel.add(updateSupplierButton);
        mainPanel.add(deleteSupplierButton);

        // Adding main panel to the frame
        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void viewSuppliers() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM Supplier";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<String> suppliers = new ArrayList<>();
                    while (resultSet.next()) {
                        int supplierId = resultSet.getInt("supplierId");
                        String supplierName = resultSet.getString("supplierName");
                        String contactInformation = resultSet.getString("contactInformation");
                        String email = resultSet.getString("email");

                        suppliers.add("Supplier ID: " + supplierId +
                                ", Supplier Name: " + supplierName +
                                ", Contact Information: " + contactInformation +
                                ", Email: " + email);
                    }

                    if (!suppliers.isEmpty()) {
                        String suppliersList = String.join("\n", suppliers);
                        JOptionPane.showMessageDialog(this, "Supplier List:\n" + suppliersList);
                    } else {
                        JOptionPane.showMessageDialog(this, "No suppliers found.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void addSupplier() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String supplierName = JOptionPane.showInputDialog(this, "Enter Supplier Name:");
            String contactInformation = JOptionPane.showInputDialog(this, "Enter Contact Information:");
            String email = JOptionPane.showInputDialog(this, "Enter Email:");

            String sql = "INSERT INTO Supplier (supplierName, contactInformation, email) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, supplierName);
                preparedStatement.setString(2, contactInformation);
                preparedStatement.setString(3, email);

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Supplier added successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add supplier.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updateSupplier() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            int supplierId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Supplier ID to update:"));
            String newContactInformation = JOptionPane.showInputDialog(this, "Enter new contact information:");
            String newEmail = JOptionPane.showInputDialog(this, "Enter new email:");

            String sql = "UPDATE Supplier SET contactInformation = ?, email = ? WHERE supplierId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, newContactInformation);
                preparedStatement.setString(2, newEmail);
                preparedStatement.setInt(3, supplierId);

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Supplier updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update supplier.");
                }
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteSupplier() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            int supplierId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Supplier ID to delete:"));

            int confirmResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the supplier?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirmResult == JOptionPane.YES_OPTION) {
                String sql = "DELETE FROM Supplier WHERE supplierId = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, supplierId);

                    int rowsDeleted = preparedStatement.executeUpdate();

                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(this, "Supplier deleted successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete supplier.");
                    }
                }
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    
}
