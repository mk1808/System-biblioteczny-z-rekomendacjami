package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

}
