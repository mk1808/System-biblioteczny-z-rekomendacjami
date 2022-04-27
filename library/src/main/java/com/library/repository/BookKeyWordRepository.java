package com.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.BookKeyWord;

@Repository
public interface BookKeyWordRepository extends JpaRepository<BookKeyWord, UUID>{

}
