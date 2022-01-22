package com.library.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.model.BookCopy;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, UUID>{
	//@Query("SELECT c FROM BookCopy c JOIN book.id b WHERE b= :id")
	List<BookCopy> getBookCopiesByBookId(UUID id);
}
//@Query("select u.userName from User u inner join u.area ar where ar.idArea = :idArea")