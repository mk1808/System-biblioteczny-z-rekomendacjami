package com.library.service;

import java.util.List;
import java.util.UUID;

import com.library.model.Recommendation;
import com.library.nosql.model.RecommendationData;

public interface RecommendationService extends RepositoryService<Recommendation> {
	
	List<Recommendation> getByUserId(UUID id);

}
