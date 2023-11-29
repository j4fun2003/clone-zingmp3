package com.m2m.zing.service;

import com.m2m.zing.model.User;

public interface UserService {
    User createUser(User user);
    User getUserById(Long userId);
    User updateUser(Long userId, User userDetails);
    void deleteUser(Long userId);
    // Other methods as needed
}