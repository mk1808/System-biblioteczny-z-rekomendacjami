package com.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, UUID>{

}
