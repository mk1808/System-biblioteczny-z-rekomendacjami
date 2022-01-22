package com.library.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.model.BookCopy;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, UUID>{
	@Query("SELECT c FROM bookCopy c JOIN book b ON c.book=b.id WHERE b.id=?1")
	List<BookCopy> getBookCopiesByBookId(UUID id);
}
