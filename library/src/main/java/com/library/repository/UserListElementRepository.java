package com.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.UserListElement;

@Repository
public interface UserListElementRepository extends JpaRepository<UserListElement, Long>{

	List<UserListElement> getByUserIdAndType(Long userId, String type);

}
