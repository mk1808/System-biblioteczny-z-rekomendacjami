package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.Borrowing;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long>{

}
