package com.mySwing.Services;

import java.util.List;

import com.mySwin.Models.Customer;
import com.mySwin.Models.Order;

public interface OrderService {
    void placeOrder(Order order);
    void cancelOrder(int orderId);
    List<Order> getAllOrders();
    Order getOrderById(int orderId);
    List<Order> getOrdersByCustomer(Customer customer);

}

