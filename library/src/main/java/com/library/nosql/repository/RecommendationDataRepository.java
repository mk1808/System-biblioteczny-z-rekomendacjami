package com.library.nosql.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.library.nosql.model.RecommendationData;

@Repository
public interface RecommendationDataRepository extends MongoRepository<RecommendationData, String> {
	List<RecommendationData> findByUserIdAndDeletedIsNull(String userId);
}
