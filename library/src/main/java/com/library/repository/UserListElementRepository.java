package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.UserListElement;

public interface UserListElementRepository extends JpaRepository<UserListElement, Long>{

}
