package com.library.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.Borrowing;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, UUID>{

	List<Borrowing> getByUserId(UUID userId);

	List<Borrowing> getByBookCopyId(UUID bookCopyId);

}
