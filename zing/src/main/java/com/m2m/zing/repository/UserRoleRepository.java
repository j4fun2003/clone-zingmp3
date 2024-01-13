package com.m2m.zing.repository;

import com.m2m.zing.model.Role;
import com.m2m.zing.model.User;
import com.m2m.zing.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    @Query("SELECT new com.m2m.zing.model.UserRole(u1_0.user.userId, u1_0.id, u1_0.role.id, u1_0.role.name)  " +
            "FROM UserRole u1_0 " +
            "LEFT JOIN Role r1_0 ON r1_0.id = u1_0.role.id " +
            "WHERE u1_0.user.userId = :userId")
    List<UserRole> getUserRolesById(@Param("userId") Long userId);

//    @Query("SELECT ur FROM UserRole ur WHERE ur.user.userId = :user AND ur.role.id = :role")
//    UserRole findByUserAndRole(@Param("user") Long user, @Param("role") Long role);

    @Query("SELECT ur FROM UserRole ur WHERE ur.user.userId = :userId")
    UserRole findByUserId(@Param("userId") Long userId);

    Optional<UserRole> findByUserEmail(String email);
}
