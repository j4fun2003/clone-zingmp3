

package com.m2m.zing.service;

import com.m2m.zing.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user) throws Exception;
    User getUserById(Long userId) throws Exception;
    User updateUser(Long userId, User userDetails) throws Exception;
    void deleteUser(Long userId) throws Exception;

    User getByUserName(String username) throws Exception;
    User getByEmail(String email) throws Exception;

    List<User> getAllUser() throws Exception;

}