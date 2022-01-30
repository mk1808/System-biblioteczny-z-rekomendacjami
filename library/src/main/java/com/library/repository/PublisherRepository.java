package com.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, UUID>{

}
