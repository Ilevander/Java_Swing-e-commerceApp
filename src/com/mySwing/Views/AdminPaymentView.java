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

public class AdminPaymentView extends JFrame {

    public AdminPaymentView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Admin Payment Management");

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton viewPaymentsButton = new JButton("View Payments");
        JButton addPaymentButton = new JButton("Add Payment");
        JButton updatePaymentButton = new JButton("Update Payment");
        JButton deletePaymentButton = new JButton("Delete Payment");

        // Action Listeners
        viewPaymentsButton.addActionListener(e -> viewPayments());
        addPaymentButton.addActionListener(e -> addPayment());
        updatePaymentButton.addActionListener(e -> updatePayment());
        deletePaymentButton.addActionListener(e -> deletePayment());

        // Adding components to the main panel
        mainPanel.add(viewPaymentsButton);
        mainPanel.add(addPaymentButton);
        mainPanel.add(updatePaymentButton);
        mainPanel.add(deletePaymentButton);

        // Adding main panel to the frame
        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void viewPayments() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM payment";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<String> payments = new ArrayList<>();
                    while (resultSet.next()) {
                        int paymentId = resultSet.getInt("paymentId");
                        int orderId = resultSet.getInt("orderId");
                        double amount = resultSet.getDouble("amount");
                        String paymentDate = resultSet.getString("paymentDate");

                        payments.add("Payment ID: " + paymentId +
                                ", Order ID: " + orderId +
                                ", Amount: " + amount +
                                ", Payment Date: " + paymentDate);
                    }

                    if (!payments.isEmpty()) {
                        String paymentsList = String.join("\n", payments);
                        JOptionPane.showMessageDialog(this, "Payment List:\n" + paymentsList);
                    } else {
                        JOptionPane.showMessageDialog(this, "No payments found.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void addPayment() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            int orderId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Order ID:"));
            double amount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Amount:"));
            String paymentDate = JOptionPane.showInputDialog(this, "Enter Payment Date (YYYY-MM-DD):");

            String sql = "INSERT INTO payment (orderId, amount, paymentDate) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, orderId);
                preparedStatement.setDouble(2, amount);
                preparedStatement.setString(3, paymentDate);

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Payment added successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add payment.");
                }
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updatePayment() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            int paymentId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Payment ID to update:"));
            double newAmount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter new amount:"));
            String newPaymentDate = JOptionPane.showInputDialog(this, "Enter new Payment Date (YYYY-MM-DD):");

            String sql = "UPDATE payment SET amount = ?, paymentDate = ? WHERE paymentId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDouble(1, newAmount);
                preparedStatement.setString(2, newPaymentDate);
                preparedStatement.setInt(3, paymentId);

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Payment updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update payment.");
                }
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deletePayment() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            int paymentId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Payment ID to delete:"));

            int confirmResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the payment?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirmResult == JOptionPane.YES_OPTION) {
                String sql = "DELETE FROM payment WHERE paymentId = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, paymentId);

                    int rowsDeleted = preparedStatement.executeUpdate();

                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(this, "Payment deleted successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete payment.");
                    }
                }
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    
}
