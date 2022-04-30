package com.library.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.library.model.Recommendation;
import com.library.nosql.model.RecommendationData;
import com.library.nosql.repository.RecommendationDataRepository;
import com.library.repository.RecommendationRepository;
import com.library.service.RecommendationDataConverterService;
import com.library.service.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService {
	private final RecommendationRepository repository;
	private final RecommendationDataRepository noSQLRepository;
	private final RecommendationDataConverterService noSQLConverter;
	
	@Autowired
	public RecommendationServiceImpl(RecommendationRepository repository, RecommendationDataRepository noSQLRepository, RecommendationDataConverterService noSQLConverter) {
		this.repository = repository;
		this.noSQLRepository = noSQLRepository;
		this.noSQLConverter = noSQLConverter;
	}
	
	
	@Override
	public Recommendation get(UUID id) {	
		return repository.getById(id);
	}

	@Override
	public Recommendation create(Recommendation entity) {
		return repository.save(entity);
	}

	@Override
	public Recommendation update(Recommendation entity) {
		return repository.save(entity);
	}
	
	@Override
	public List<Recommendation> getByUserId(UUID id) {
		List<RecommendationData> noSQLList =  noSQLRepository.findByUserId(id.toString());
		return noSQLConverter.toModelList(noSQLList);
	}

}
