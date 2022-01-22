package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.Borrowing;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long>{

}
