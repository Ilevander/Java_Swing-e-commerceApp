package com.mySwing.Controllers;

import java.util.ArrayList;
import java.util.List;
import com.mySwin.Models.Category;
import com.mySwing.Services.CategoryService;

public class CategoryController implements CategoryService {

    private List<Category> categories;

    public CategoryController() {
        // Initialize categories or fetch them from a data source
        this.categories = new ArrayList<>();
    }

    @Override
    public void addCategory(Category category) {
        // Placeholder logic to add a category
        categories.add(category);
        System.out.println("Category added: " + category);
    }

    @Override
    public void updateCategory(Category category) {
        // Placeholder logic to update a category
        for (Category existingCategory : categories) {
            if (existingCategory.getCategoryId() == category.getCategoryId()) {
                existingCategory.setCategoryName(category.getCategoryName());
                System.out.println("Category updated: " + category);
                return;
            }
        }
        System.out.println("Category not found for update: " + category);
    }

    @Override
    public void deleteCategory(int categoryId) {
        // Placeholder logic to delete a category
        categories.removeIf(category -> category.getCategoryId() == categoryId);
        System.out.println("Category deleted with ID: " + categoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        // Placeholder logic to get all categories
        System.out.println("Getting all categories");
        return new ArrayList<>(categories);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        // Placeholder logic to get a category by ID
        for (Category category : categories) {
            if (category.getCategoryId() == categoryId) {
                System.out.println("Getting category by ID: " + categoryId + " - " + category);
                return category;
            }
        }
        System.out.println("Category not found for ID: " + categoryId);
        return null;
    }
}
