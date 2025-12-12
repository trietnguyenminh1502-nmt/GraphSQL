package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private Integer quantity;
    
    // --- SỬA Ở ĐÂY ---
    // Đổi tên biến từ 'desc' thành 'description' để tránh lỗi từ khóa SQL
    // columnDefinition = "LONGTEXT" giúp lưu được văn bản dài
    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;
    
    @Column(nullable = false)
    private Double price;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // Bỏ nullable=false nếu muốn cho phép product tạm thời chưa có user
    @ToString.Exclude
    private User user;
}