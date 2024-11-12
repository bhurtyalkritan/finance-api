// src/main/java/com/example/portfolio_api/service/UserService.java

package com.example.portfolio_api.service;

import com.example.portfolio_api.exception.ResourceNotFoundException;
import com.example.portfolio_api.model.User;
import com.example.portfolio_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> getAllUsers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User getUserByAccountNumber(String accountNumber) {
        return userRepository.findByAccountNumber(accountNumber);
    }

    @Transactional
    public User createUser(User user) {
        // Additional business logic can be added here
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, User userDetails) {
        // Update user logic
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setAddress(userDetails.getAddress());
        // Update other fields as necessary

        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public int getUserCount() {
        return userRepository.getUserCount();
    }

    public String getUserEmailById(Long id) {
        return userRepository.getUserEmailById(id);
    }
}
