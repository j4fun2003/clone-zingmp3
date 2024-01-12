package com.m2m.zing.repository;

import com.m2m.zing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User findByUsername(String username);




//    @Query("SELECT u.user.userId, u.id, r.id, r.name FROM UserRole u LEFT JOIN u.role r WHERE u.user.userId = :userId")
//    User findUserRolesByUserId(@Param("userId") Long userId);

}
