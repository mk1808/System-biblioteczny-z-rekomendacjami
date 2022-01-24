package com.library.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.model.Recommendation;
import com.library.repository.RecommendationRepository;
import com.library.service.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService {
	private final RecommendationRepository repository;
	
	@Autowired
	public RecommendationServiceImpl(RecommendationRepository repository) {
		this.repository = repository;
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

}
