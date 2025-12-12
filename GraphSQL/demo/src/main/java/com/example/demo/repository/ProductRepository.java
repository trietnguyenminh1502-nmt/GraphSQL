package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // 1. Lấy tất cả sản phẩm, sắp xếp giá tăng dần (đúng yêu cầu đề bài)
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findAllSortedByPrice();
    
    // 2. Lấy sản phẩm theo Category
    // Logic: Product thuộc về User, User thuộc về Category (Many-to-Many)
    // Query này sẽ join 3 bảng: products -> users -> user_category
    @Query("SELECT p FROM Product p JOIN p.user u JOIN u.categories c WHERE c.id = :categoryId ORDER BY p.price ASC")
    List<Product> findProductsByCategory(@Param("categoryId") Long categoryId);
    
    // 3. Tìm sản phẩm theo User ID (JPA tự động sinh câu lệnh dựa trên tên hàm)
    List<Product> findByUserId(Long userId);
}