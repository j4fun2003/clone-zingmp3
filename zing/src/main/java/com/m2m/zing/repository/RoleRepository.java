package com.m2m.zing.repository;

import com.m2m.zing.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String role);
}
