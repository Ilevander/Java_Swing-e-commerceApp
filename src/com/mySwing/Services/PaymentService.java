package com.mySwing.Services;

import java.util.List;

import com.mySwin.Models.Order;
import com.mySwin.Models.Payement;

public interface PaymentService {
    void processPayment(Payement payment);
    List<Payement> getPaymentsByOrder(Order order);
}

