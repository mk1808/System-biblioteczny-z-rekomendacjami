package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
