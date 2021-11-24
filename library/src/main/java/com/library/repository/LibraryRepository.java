package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.Library;

public interface LibraryRepository extends JpaRepository<Library, Long>{

}
