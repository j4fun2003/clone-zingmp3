

package com.m2m.zing.service;

import com.m2m.zing.model.Role;
import com.m2m.zing.model.User;
import com.m2m.zing.model.UserRole;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    User createUser(User user);

    User update(User user);

    Role saveRole(Role role);
    User findByEmail(String email);
    User findByUsername(String username);
    void addToUser(String username, String id);
    User getUserById(Long userId) throws Exception;
    User updateUser(Long userId, User userDetails) throws Exception;
    void deleteUser(Long userId) throws Exception;
//    User getByUserName(String username) throws Exception;
    List<User> getAllUser() throws Exception;

    List<UserRole> getUserRolesById( Long userId);
}