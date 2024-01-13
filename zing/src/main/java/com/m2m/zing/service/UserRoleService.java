package com.m2m.zing.service;

import com.m2m.zing.model.UserRole;
import org.springframework.stereotype.Service;

@Service
public interface UserRoleService {

    UserRole findByUserId(Long id);
}
