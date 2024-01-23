package com.mySwing.Views;

import com.mySwin.Models.Category;
import com.mySwing.Controllers.CategoryController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CategoryView extends JFrame {

    private static final long serialVersionUID = 1L;
    private CategoryController categoryController;

    public CategoryView() {
        this.categoryController = new CategoryController();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Category Management");

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

        // Add categoryPanel to the frame
        add(categoryPanel);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CategoryView().setVisible(true);
            }
        });
    }
}
