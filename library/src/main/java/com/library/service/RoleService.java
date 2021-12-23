package com.library.service;

import org.springframework.stereotype.Service;

import com.library.model.Role;

@Service
public interface RoleService {
	Role findByName(String name);
}
