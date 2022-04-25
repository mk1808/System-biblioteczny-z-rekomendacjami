package com.library.nosql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.library.nosql.model.UserData;

@Repository
public interface UserDataRepository extends MongoRepository<UserData, String> {

}
