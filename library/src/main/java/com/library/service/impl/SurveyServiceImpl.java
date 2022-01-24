package com.library.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dto.SurveyDto;
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
	public SurveyDto get(UUID id) {
		return repository.getById(id);
	}

	@Override
	public SurveyDto create(SurveyDto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SurveyDto update(SurveyDto entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
