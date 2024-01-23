package com.mySwing.Controllers;

import com.mySwin.Models.Product;
import com.mySwing.Services.InventoryService;

import java.util.HashMap;
import java.util.Map;

public class InventoryController implements InventoryService {

    private Map<Product, Integer> stockMap; // Map to store the stock quantity for each product

    public InventoryController() {
        // Initialize the stock map or fetch it from a data source
        this.stockMap = new HashMap<>();
    }

    @Override
    public void updateStock(Product product, int quantity) {
        // Placeholder logic to update the stock for a product
        int currentStock = stockMap.getOrDefault(product, 0);
        int newStock = currentStock + quantity;
        stockMap.put(product, newStock);

        System.out.println("Stock updated for product " + product.getProductName() +
                           ". New stock quantity: " + newStock);
    }

    @Override
    public int getStock(Product product) {
        // Placeholder logic to get the stock quantity for a product
        return stockMap.getOrDefault(product, 0);
    }
}
