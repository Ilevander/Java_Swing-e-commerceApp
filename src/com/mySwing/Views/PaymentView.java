package com.mySwing.Views;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mySwing.database.DatabaseUtil;

public class PaymentView extends JFrame {

    private int orderId; // Assuming you pass the orderId to the constructor

    public PaymentView(int orderId) {
        this.orderId = orderId;
        initComponents();
        displayPayments();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Payment View");

        JPanel mainPanel = new JPanel(new GridLayout(3, 2));

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();

        JLabel dateLabel = new JLabel("Payment Date (YYYY-MM-DD):");
        JTextField dateField = new JTextField();

        JButton makePaymentButton = new JButton("Make Payment");

        makePaymentButton.addActionListener(e -> makePayment(orderId, amountField.getText(), dateField.getText()));

        mainPanel.add(amountLabel);
        mainPanel.add(amountField);
        mainPanel.add(dateLabel);
        mainPanel.add(dateField);
        mainPanel.add(makePaymentButton);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void makePayment(int orderId, String amountStr, String dateStr) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            double amount = Double.parseDouble(amountStr);
            java.sql.Date paymentDate = java.sql.Date.valueOf(dateStr);

            String sql = "INSERT INTO payment (orderId, amount, paymentDate) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, orderId);
                preparedStatement.setDouble(2, amount);
                preparedStatement.setDate(3, paymentDate);

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Payment made successfully!");
                    displayPayments(); // Refresh payment details after making a new payment
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to make payment.");
                }
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void displayPayments() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM payment WHERE orderId = ? ORDER BY paymentDate DESC";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, orderId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Assuming you have a JTextArea to display the payment details
                    JTextArea paymentDetailsArea = new JTextArea();
                    paymentDetailsArea.setEditable(false);

                    while (resultSet.next()) {
                        int paymentId = resultSet.getInt("paymentId");
                        double amount = resultSet.getDouble("amount");
                        Date paymentDate = resultSet.getDate("paymentDate");

                        paymentDetailsArea.append("Payment ID: " + paymentId +
                                "\nAmount: " + amount +
                                "\nPayment Date: " + paymentDate + "\n\n");
                    }

                    JScrollPane scrollPane = new JScrollPane(paymentDetailsArea);
                    JOptionPane.showMessageDialog(this, scrollPane, "Payment Details", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentView(1).setVisible(true));
    }
}
