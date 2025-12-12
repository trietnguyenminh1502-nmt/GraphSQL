package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

@Controller
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private UserService userService;
    
    // --- QUERIES ---
    @QueryMapping
    public List<Product> allProducts() {
        return productService.getAllProducts();
    }
    
    @QueryMapping
    public Product productById(@Argument Long id) {
        return productService.getProductById(id).orElse(null);
    }
    
    @QueryMapping
    public List<Product> productsByCategory(@Argument Long categoryId) {
        return productService.getProductsByCategory(categoryId);
    }
    
    // --- MUTATIONS ---
    
    @MutationMapping
    public Product createProduct(@Argument CreateProductInput input) {
        Product product = new Product();
        product.setTitle(input.title());
        product.setQuantity(input.quantity());
        product.setPrice(input.price());
        
        // Đã sửa thành description
        product.setDescription(input.description()); 
        
        // Xử lý User
        if (input.userId() != null) {
            Optional<User> userOpt = userService.getUserById(input.userId());
            if (userOpt.isPresent()) {
                product.setUser(userOpt.get());
            }
        }
        
        return productService.createProduct(product);
    }
    
    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument UpdateProductInput input) {
        Product productDetails = new Product();
        
        productDetails.setTitle(input.title());
        productDetails.setQuantity(input.quantity());
        productDetails.setPrice(input.price());
        
        // Đã sửa thành description
        productDetails.setDescription(input.description());
        
        return productService.updateProduct(id, productDetails);
    }
    
    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        return productService.deleteProduct(id);
    }
}

// --- QUAN TRỌNG: KHAI BÁO RECORD DTO Ở CUỐI FILE ---
// (Hoặc bạn có thể tách ra file riêng trong package dto)

record CreateProductInput(
    String title, 
    Integer quantity, 
    String description, // Tên biến phải khớp với schema input
    Double price, 
    Long userId
) {}

record UpdateProductInput(
    String title, 
    Integer quantity, 
    String description, 
    Double price
) {}