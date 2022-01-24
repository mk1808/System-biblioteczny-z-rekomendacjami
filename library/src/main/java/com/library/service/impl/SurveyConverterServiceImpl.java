package com.library.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.SurveyDto;
import com.library.model.Survey;
import com.library.service.SurveyConverterService;

@Service
public class SurveyConverterServiceImpl implements SurveyConverterService{

	@Override
	public SurveyDto toDto(Survey model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Survey toModel(SurveyDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SurveyDto> toDtoList(List<Survey> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Survey> toModelList(List<SurveyDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SurveyDto> toDtoPage(Page<Survey> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Survey> toModelPage(Page<SurveyDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
