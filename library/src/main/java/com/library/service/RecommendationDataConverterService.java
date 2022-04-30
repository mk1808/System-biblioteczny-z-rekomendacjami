package com.library.service;

import java.util.List;

import com.library.model.Recommendation;
import com.library.nosql.model.RecommendationData;

public interface RecommendationDataConverterService {
	
	Recommendation toModel(RecommendationData noSQLModel);
	
	List<Recommendation> toModelList(List<RecommendationData> noSQLModels);
}
