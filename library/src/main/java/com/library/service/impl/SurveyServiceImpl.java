package com.library.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dto.SurveyDto;
import com.library.model.Survey;
import com.library.repository.SurveyRepository;
import com.library.service.SurveyService;

@Service
public class SurveyServiceImpl implements SurveyService{
	private final SurveyRepository repository;
	
	@Autowired
	public SurveyServiceImpl(SurveyRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Survey get(UUID id) {
		return repository.getById(id);
	}

	@Override
	public Survey create(Survey entity) {
		return repository.save(entity);
	}

	@Override
	public Survey update(Survey entity) {
		return repository.save(entity);
	}

}
