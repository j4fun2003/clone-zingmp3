package com.m2m.zing.service.impl;

import com.m2m.zing.model.UserRole;
import com.m2m.zing.repository.UserRoleRepository;
import com.m2m.zing.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserRole findByUserId(Long id) {
        return userRoleRepository.findByUserId(id);
    }
}
