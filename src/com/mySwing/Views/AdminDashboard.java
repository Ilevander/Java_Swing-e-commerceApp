package com.mySwing.Views;

import javax.swing.*;

import com.mySwing.Controllers.LogoutController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {

	private LogoutController logoutController;

    public AdminDashboard() {
        initComponents();
        logoutController = new LogoutController(this);
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setTitle("Admin Dashboard");

        // Sidebar
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(51, 51, 51));

        JButton logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(new Color(102, 102, 102));
        logoutButton.setFocusPainted(false);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        logoutButton.addActionListener(e -> logoutController.logout());

        sidebarPanel.add(logoutButton);
        // Options
        String[] options = {
                "Manage Users",
                "Manage Products",
                "Category of Product",
                "Use Inventory",
                "Manage Orders",
                "Manage Payment",
                "My Suppliers"
        };
        JButton[] optionButtons = new JButton[options.length];

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
        JLabel contentLabel = new JLabel("Welcome to Admin Dashboard!");
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
                openUserView();
            case 1:
                openAdminProductsView();
            case 2:  
                openAdminProductsCategoriesView();
            case 3:  
            	openAdminInventoryView();
            case 4:
            	openAdminOrderView();
            case 5:
            	openAdminPaymentView();	
            case 6:
            	openAdminSupplierView();
                break;
        }
    }        

    private void openUserView() {
        SwingUtilities.invokeLater(() -> new UserView().setVisible(true));
    }
   
    private void openAdminProductsView() {  
        SwingUtilities.invokeLater(() -> new AdminProductsView().setVisible(true));
    }

    private void openAdminProductsCategoriesView() {
        SwingUtilities.invokeLater(() -> new AdminProductsCategoriesView().setVisible(true));
    }
    private void openAdminInventoryView() {
        SwingUtilities.invokeLater(() -> new AdminInventoryView().setVisible(true));
    }
    private void openAdminOrderView() {
        SwingUtilities.invokeLater(() -> new AdminOrderView().setVisible(true));
    }
    private void openAdminPaymentView() {
        SwingUtilities.invokeLater(() -> new AdminPaymentView().setVisible(true));
    }
    private void openAdminSupplierView() {
        SwingUtilities.invokeLater(() -> new AdminSupplierView().setVisible(true));
    }
}
