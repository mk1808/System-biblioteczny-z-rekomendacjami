package com.library.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.library.model.Recommendation;
import com.library.model.AppUser;
import com.library.nosql.model.RecommendationData;
import com.library.nosql.repository.RecommendationDataRepository;
import com.library.repository.RecommendationRepository;
import com.library.service.RecommendationDataConverterService;
import com.library.service.RecommendationService;
import com.library.service.UserService;

@Service
public class RecommendationServiceImpl implements RecommendationService {
	private final RecommendationRepository repository;
	private final RecommendationDataRepository noSQLRepository;
	private final RecommendationDataConverterService noSQLConverter;
	private final UserService userService;
	
	@Autowired
	public RecommendationServiceImpl(RecommendationRepository repository, RecommendationDataRepository noSQLRepository, 
			RecommendationDataConverterService noSQLConverter, UserService userService) {
		this.repository = repository;
		this.noSQLRepository = noSQLRepository;
		this.noSQLConverter = noSQLConverter;
		this.userService = userService;
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
		AppUser user = userService.get(id);
		List<RecommendationData> noSQLList =  noSQLRepository.findByUserIdAndDeletedIsNull(user.getMail());
		return noSQLConverter.toModelList(noSQLList);
	}

}
