package com.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.model.Role;
import com.library.repository.RoleRepository;
import com.library.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
    private final RoleRepository roleRepository;
	
	public RoleServiceImpl(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	@Override
	public Role findByName(String name) {
		Role role = roleRepository.findRoleByName(name);
        return role;
	}

}
