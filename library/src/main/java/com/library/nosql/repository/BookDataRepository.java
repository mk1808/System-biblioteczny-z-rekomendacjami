package com.library.nosql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.library.nosql.model.BookData;

@Repository
public interface BookDataRepository extends MongoRepository<BookData, String> {

	
}
