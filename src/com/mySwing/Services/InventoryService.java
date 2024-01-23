package com.mySwing.Services;

import java.util.List;

import com.mySwin.Models.Product;

public interface InventoryService {
    void updateStock(Product product, int quantity);
    int getStock(Product product);
}




