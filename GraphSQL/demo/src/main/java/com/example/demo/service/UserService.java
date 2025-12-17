package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.User;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    public User updateUser(Long id, User userDetails) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User u = user.get();
            if (userDetails.getFullname() != null) {
                u.setFullname(userDetails.getFullname());
            }
            if (userDetails.getEmail() != null) {
                u.setEmail(userDetails.getEmail());
            }
            if (userDetails.getPassword() != null) {
                u.setPassword(userDetails.getPassword());
            }
            if (userDetails.getPhone() != null) {
                u.setPhone(userDetails.getPhone());
            }
            return userRepository.save(u);
        }
        return null;
    }
    
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public User addCategoryToUser(Long userId, Long categoryId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        
        if (userOpt.isPresent() && categoryOpt.isPresent()) {
            User user = userOpt.get();
            Category category = categoryOpt.get();
            user.getCategories().add(category);
            return userRepository.save(user);
        }
        return null;
    }
    
    public User removeCategoryFromUser(Long userId, Long categoryId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        
        if (userOpt.isPresent() && categoryOpt.isPresent()) {
            User user = userOpt.get();
            Category category = categoryOpt.get();
            user.getCategories().remove(category);
            return userRepository.save(user);
        }
        return null;
    }
}
