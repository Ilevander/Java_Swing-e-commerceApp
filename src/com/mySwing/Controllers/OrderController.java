package com.mySwing.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.mySwin.Models.Customer;
import com.mySwin.Models.Order;
import com.mySwing.Services.OrderService;

public class OrderController implements OrderService {

    private List<Order> orders;

    public OrderController() {
        // Initialize orders or fetch them from a data source
        this.orders = new ArrayList<>();
    }

    @Override
    public void placeOrder(Order order) {
        // Placeholder logic to place an order
        orders.add(order);
        System.out.println("Order placed: " + order);
    }

    @Override
    public void cancelOrder(int orderId) {
        // Placeholder logic to cancel an order
        orders.removeIf(order -> order.getOrderId() == orderId);
        System.out.println("Order canceled with ID: " + orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        // Placeholder logic to get all orders
        System.out.println("Getting all orders");
        return new ArrayList<>(orders);
    }

    @Override
    public Order getOrderById(int orderId) {
        // Placeholder logic to get an order by ID
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                System.out.println("Getting order by ID: " + orderId + " - " + order);
                return order;
            }
        }
        System.out.println("Order not found for ID: " + orderId);
        return null;
    }

    @Override
    public List<Order> getOrdersByCustomer(Customer customer) {
        // Placeholder logic to get orders by customer
        List<Order> ordersByCustomer = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomer().equals(customer)) {
                ordersByCustomer.add(order);
            }
        }
        System.out.println("Getting orders by customer: " + customer + " - " + ordersByCustomer);
        return ordersByCustomer;
    }
}
