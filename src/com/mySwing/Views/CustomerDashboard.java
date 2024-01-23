package com.mySwing.Views;

import javax.swing.*;

import com.mySwing.Controllers.LogoutController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerDashboard extends JFrame {

    private static final long serialVersionUID = 1L;

    private LogoutController logoutController;
    
    public CustomerDashboard() {
        initComponents();  
        logoutController = new LogoutController(this);
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Customer Dashboard");

        // Sidebar
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(51, 51, 51));

        // Profile Picture (Assuming you have a customer profile picture as an ImageIcon)
        ImageIcon profilePicture = new ImageIcon("path/to/profile_picture.jpg");
        JLabel profilePictureLabel = new JLabel(profilePicture);
        profilePictureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebarPanel.add(profilePictureLabel);

        // Options
        String[] options = {"View Profile", "Products", "Payments"};
        JButton[] optionButtons = new JButton[options.length];

        JButton logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(new Color(102, 102, 102));
        logoutButton.setFocusPainted(false);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        logoutButton.addActionListener(e -> logoutController.logout());

        sidebarPanel.add(logoutButton);
        
        for (int i = 0; i < options.length; i++) {
            optionButtons[i] = new JButton(options[i]);
            optionButtons[i].setForeground(Color.WHITE);
            optionButtons[i].setBackground(new Color(102, 102, 102));
            optionButtons[i].setFocusPainted(false);
            optionButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);

            int finalI = i;
            optionButtons[i].addActionListener(e -> handleOptionClick(finalI));

            sidebarPanel.add(optionButtons[i]);
        }

        // Main Panel
        JPanel mainPanel = new JPanel();
        JLabel contentLabel = new JLabel("Welcome to your dashboard!");
        mainPanel.add(contentLabel);

        // Add sidebar and mainPanel to the frame
        add(sidebarPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void handleOptionClick(int optionIndex) {
        switch (optionIndex) {
            case 0:
                // View Profile logic
                int customerId = 1; // Replace with logic to get the actual customer ID
                openCustomerProfileView(customerId);
                break;
            case 1:
                // Products logic
                openProductView();
                break;
            case 2:
                // Payments logic
                openPaymentsView();
                break;
            // Add more cases for additional options
        }
    }

    private void openCustomerProfileView(int customerId) {
        SwingUtilities.invokeLater(() -> new CustomerProfileView(customerId).setVisible(true));
    }

    private void openProductView() {
        ProductView productView = new ProductView();
        productView.setVisible(true);
        this.dispose();
    }

    private void openPaymentsView() {
        int orderId = 1; 
        SwingUtilities.invokeLater(() -> new PaymentView(orderId).setVisible(true));
    }

}
