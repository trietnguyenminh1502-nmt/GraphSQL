package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CategoryService categoryService;
    
    // Queries
    @QueryMapping
    public List<User> allUsers() {
        return userService.getAllUsers();
    }
    
    @QueryMapping
    public User userById(@Argument Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.orElse(null);
    }
    
    // Mutations
    @MutationMapping
    public User createUser(
            @Argument String fullname,
            @Argument String email,
            @Argument String password,
            @Argument String phone) {
        User user = new User();
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        return userService.createUser(user);
    }
    
    @MutationMapping
    public User updateUser(
            @Argument Long id,
            @Argument String fullname,
            @Argument String email,
            @Argument String password,
            @Argument String phone) {
        User userDetails = new User();
        userDetails.setFullname(fullname);
        userDetails.setEmail(email);
        userDetails.setPassword(password);
        userDetails.setPhone(phone);
        return userService.updateUser(id, userDetails);
    }
    
    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        return userService.deleteUser(id);
    }
    
    @MutationMapping
    public User addCategoryToUser(@Argument Long userId, @Argument Long categoryId) {
        return userService.addCategoryToUser(userId, categoryId);
    }
    
    @MutationMapping
    public User removeCategoryFromUser(@Argument Long userId, @Argument Long categoryId) {
        return userService.removeCategoryFromUser(userId, categoryId);
    }
}
