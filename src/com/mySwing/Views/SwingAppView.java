package com.mySwing.Views;

import com.mySwin.Models.Category;
import com.mySwin.Models.Product;
import com.mySwin.Models.Supplier;
import com.mySwing.Controllers.CategoryController;
import com.mySwing.Controllers.ProductController;
import com.mySwing.Controllers.SupplierController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SwingAppView extends JFrame {

    private static final long serialVersionUID = 1L;
    private CategoryController categoryController;
    private ProductController productController;
    private SupplierController supplierController;

    public SwingAppView() {
        this.categoryController = new CategoryController();
        this.productController = new ProductController();
        this.supplierController = new SupplierController();

        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Swing Desktop App");

        // Category Panel
        JPanel categoryPanel = new JPanel();
        JButton addCategoryButton = new JButton("Add Category");
        JButton getAllCategoriesButton = new JButton("Get All Categories");

        addCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Use a dialog box to get user input for category name
                String categoryName = JOptionPane.showInputDialog("Enter Category Name:");
                if (categoryName != null && !categoryName.isEmpty()) {
                    try {
                        // Call the controller to add the category
                        categoryController.addCategory(new Category());
                        JOptionPane.showMessageDialog(null, "Category added successfully!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error adding category: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // User canceled or entered an empty name
                }
            }
        });

        getAllCategoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Call the controller to get all categories
                    List<Category> categories = categoryController.getAllCategories();
                    // Update the UI with the categories
                    // Example: updateCategoryUI(categories);
                    JOptionPane.showMessageDialog(null, "Categories: " + categories);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error getting categories: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        categoryPanel.add(addCategoryButton);
        categoryPanel.add(getAllCategoriesButton);

        // Product Panel
        JPanel productPanel = new JPanel();
        JButton addProductButton = new JButton("Add Product");
        JButton getAllProductsButton = new JButton("Get All Products");

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Use a dialog box to get user input for product details
                // Example: Product product = showProductInputDialog();
                // productController.addProduct(product);
            }
        });

        getAllProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Call the controller to get all products
                    List<Product> products = productController.getAllProducts();
                    // Update the UI with the products
                    // Example: updateProductUI(products);
                    JOptionPane.showMessageDialog(null, "Products: " + products);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error getting products: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        productPanel.add(addProductButton);
        productPanel.add(getAllProductsButton);

        // Supplier Panel
        JPanel supplierPanel = new JPanel();
        JButton addSupplierButton = new JButton("Add Supplier");
        JButton getAllSuppliersButton = new JButton("Get All Suppliers");

        addSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Use a dialog box to get user input for supplier details
                // Example: Supplier supplier = showSupplierInputDialog();
                // supplierController.addSupplier(supplier);
            }
        });

        getAllSuppliersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Call the controller to get all suppliers
                    List<Supplier> suppliers = supplierController.getAllSuppliers();
                    // Update the UI with the suppliers
                    // Example: updateSupplierUI(suppliers);
                    JOptionPane.showMessageDialog(null, "Suppliers: " + suppliers);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error getting suppliers: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        supplierPanel.add(addSupplierButton);
        supplierPanel.add(getAllSuppliersButton);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(categoryPanel);
        mainPanel.add(productPanel);
        mainPanel.add(supplierPanel);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SwingAppView().setVisible(true);
            }
        });
    }
}
