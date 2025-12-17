-- Insert Users (include role column)
INSERT INTO users (fullname, email, password, phone, role) VALUES 
('Admin System', 'admin@example.com', 'adminpass', '0900000000', 'ADMIN'),
('Nguyễn Văn A', 'nguyenvana@example.com', 'password123', '0901234567', 'USER'),
('Trần Thị B', 'tranthib@example.com', 'password456', '0987654321', 'USER'),
('Lê Văn C', 'levanc@example.com', 'password789', '0912345678', 'USER');

-- Insert Categories
INSERT INTO categories (name, images) VALUES 
('Điện thoại', 'https://via.placeholder.com/300x200?text=Phones'),
('Laptop', 'https://via.placeholder.com/300x200?text=Laptops'),
('Phụ kiện', 'https://via.placeholder.com/300x200?text=Accessories');

-- Insert Products
-- QUAN TRỌNG: Đã đổi `desc` thành `description` ở dòng dưới đây
INSERT INTO products (title, quantity, description, price, user_id) VALUES 
('iPhone 15 Pro Max', 5, 'Điện thoại cao cấp với camera 48MP', 1299.99, 1),
('Samsung Galaxy S24', 8, 'Điện thoại flagship với AI features', 999.99, 2),
('MacBook Pro 16"', 3, 'Laptop mạnh mẽ cho chuyên gia', 2499.99, 1),
('Dell XPS 13', 6, 'Laptop siêu nhẹ, hiệu năng cao', 1299.99, 2),
('Apple AirPods Pro', 15, 'Tai nghe không dây với chế độ khử tiếng ồn', 249.99, 3),
('Samsung Galaxy Buds', 12, 'Tai nghe TWS chất lượng cao', 149.99, 1),
('iPad Air', 7, 'Máy tính bảng mạnh mẽ', 599.99, 2),
('Google Pixel 8', 10, 'Điện thoại với AI camera tốt nhất', 799.99, 3);

-- Insert User-Category Relationships
INSERT INTO user_category (user_id, category_id) VALUES 
(1, 1), (1, 2), (1, 3),
(2, 1), (2, 2),
(3, 3);