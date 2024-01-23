package com.mySwing.Services;

import java.util.List;

import com.mySwin.Models.Category;

public interface CategoryService {
    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(int categoryId);
    List<Category> getAllCategories();
    Category getCategoryById(int categoryId);
}


