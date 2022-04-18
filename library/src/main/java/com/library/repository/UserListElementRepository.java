package com.library.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.enums.UserListType;
import com.library.model.UserListElement;

@Repository
public interface UserListElementRepository extends JpaRepository<UserListElement, UUID>{

	List<UserListElement> getByUserIdAndType(UUID userId, UserListType type);

}
