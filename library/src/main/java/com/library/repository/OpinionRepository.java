package com.library.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.Opinion;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, UUID>{

	List<Opinion> getByBookId(Long id);

	Opinion getByBookIdAndUserId(Long bookId, Long userId);

	List<Opinion> getByUserId(Long userId);

}
