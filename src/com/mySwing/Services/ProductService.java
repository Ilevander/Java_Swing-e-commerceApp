package com.mySwing.Services;

import java.util.List;

import com.mySwin.Models.Category;
import com.mySwin.Models.Product;

public interface ProductService {
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int productId);
    List<Product> getAllProducts();
    Product getProductById(int productId);
    List<Product> getProductsByCategory(Category category);
}
