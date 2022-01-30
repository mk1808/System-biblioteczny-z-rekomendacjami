package com.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, UUID>{

}
