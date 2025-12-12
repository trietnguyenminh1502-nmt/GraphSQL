package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public List<Product> getAllProducts() {
        // Sắp xếp tăng dần theo giá
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
    }
    
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    
    public List<Product> getProductsByCategory(Long categoryId) {
        // --- SỬA TÊN HÀM Ở ĐÂY CHO KHỚP VỚI REPOSITORY ---
        // Lúc nãy trong Repo mình đặt là findProductsByCategory
        return productRepository.findProductsByCategory(categoryId); 
    }
    
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product updateProduct(Long id, Product productDetails) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product p = product.get();
            
            if (productDetails.getTitle() != null) p.setTitle(productDetails.getTitle());
            if (productDetails.getQuantity() != null) p.setQuantity(productDetails.getQuantity());
            if (productDetails.getPrice() != null) p.setPrice(productDetails.getPrice());
            
            // Đã dùng đúng setDescription
            if (productDetails.getDescription() != null) {
                p.setDescription(productDetails.getDescription());
            }
            
            return productRepository.save(p);
        }
        return null;
    }
    
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}