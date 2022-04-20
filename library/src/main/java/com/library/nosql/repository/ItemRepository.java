package com.library.nosql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.library.nosql.model.Item;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

}
