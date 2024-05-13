package com.example.gestionbibliotheque.service;

import com.example.gestionbibliotheque.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category saveCategory(Category category);

    Category createCategory(Category category);

    void deleteCategory(Long id);

    void deleteById(Long id);
}