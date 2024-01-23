package com.mySwing.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.mySwin.Models.Order;
import com.mySwin.Models.Payement;
import com.mySwing.Services.PaymentService;

public class PaymentController implements PaymentService {

    private List<Payement> payments;

    public PaymentController() {
        // Initialize payments or fetch them from a data source
        this.payments = new ArrayList<>();
    }

    @Override
    public void processPayment(Payement payment) {
        // Placeholder logic to process a payment   
        payments.add(payment);
        System.out.println("Payment processed: " + payment);
    }

    @Override
    public List<Payement> getPaymentsByOrder(Order order) {
        // Placeholder logic to get payments by order
        List<Payement> paymentsByOrder = new ArrayList<>();
        for (Payement payment : payments) {
            if (payment.getOrder().equals(order)) {
                paymentsByOrder.add(payment);
            }
        }
        System.out.println("Getting payments by order: " + order + " - " + paymentsByOrder);
        return paymentsByOrder;
    }
}
