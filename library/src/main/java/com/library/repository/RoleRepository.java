package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findRoleByName(String name);

}
