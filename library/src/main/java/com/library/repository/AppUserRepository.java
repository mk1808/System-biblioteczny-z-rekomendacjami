package com.library.repository;

import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID>{

	@Cacheable
    AppUser findByMail(String mail);
}
