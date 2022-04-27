package com.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.KeyWord;

@Repository
public interface KeyWordRepository extends JpaRepository<KeyWord, UUID> {

}
