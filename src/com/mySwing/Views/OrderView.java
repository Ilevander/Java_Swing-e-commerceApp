package com.mySwing.Views;

import com.mySwin.Models.Customer;
import com.mySwin.Models.Order;
import com.mySwing.Controllers.OrderController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrderView extends JFrame {

    private static final long serialVersionUID = 1L;
    private OrderController orderController;

    public OrderView() {
        this.orderController = new OrderController();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Order Management");

        // Order Panel
        JPanel orderPanel = new JPanel();
        JButton placeOrderButton = new JButton("Place Order");
        JButton cancelOrderButton = new JButton("Cancel Order");
        JButton getAllOrdersButton = new JButton("Get All Orders");

        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Use a dialog box to get user input for order details
                // Example: Order order = showOrderInputDialog();
                // orderController.placeOrder(order);
                JOptionPane.showMessageDialog(null, "Place Order functionality not implemented.");
            }
        });

        cancelOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Use a dialog box to get user input for order cancellation
                // Example: int orderId = showOrderIdInputDialog();
                // orderController.cancelOrder(orderId);
                JOptionPane.showMessageDialog(null, "Cancel Order functionality not implemented.");
            }
        });

        getAllOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Call the controller to get all orders
                    List<Order> orders = orderController.getAllOrders();
                    // Update the UI with the orders
                    // Example: updateOrderUI(orders);
                    JOptionPane.showMessageDialog(null, "Orders: " + orders);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error getting orders: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        orderPanel.add(placeOrderButton);
        orderPanel.add(cancelOrderButton);
        orderPanel.add(getAllOrdersButton);

        // Add orderPanel to the frame
        add(orderPanel);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OrderView().setVisible(true);
            }
        });
    }
}
