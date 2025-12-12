package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    // Queries
    @QueryMapping
    public List<Category> allCategories() {
        return categoryService.getAllCategories();
    }
    
    @QueryMapping
    public Category categoryById(@Argument Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.orElse(null);
    }
    
    // Mutations
    @MutationMapping
    public Category createCategory(@Argument String name, @Argument String images) {
        Category category = new Category();
        category.setName(name);
        category.setImages(images);
        return categoryService.createCategory(category);
    }
    
    @MutationMapping
    public Category updateCategory(
            @Argument Long id,
            @Argument String name,
            @Argument String images) {
        Category categoryDetails = new Category();
        categoryDetails.setName(name);
        categoryDetails.setImages(images);
        return categoryService.updateCategory(id, categoryDetails);
    }
    
    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        return categoryService.deleteCategory(id);
    }
}
