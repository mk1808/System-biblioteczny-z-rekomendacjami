package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.Opinion;

public interface OpinionRepository extends JpaRepository<Opinion, Long>{

}
