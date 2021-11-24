package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{

}
