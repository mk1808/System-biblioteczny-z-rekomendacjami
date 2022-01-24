package com.library.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.library.api.resource.SurveysResource;
import com.library.dto.BookDto;
import com.library.dto.SurveyDto;
import com.library.model.Book;
import com.library.model.Survey;
import com.library.response.Response;
import com.library.service.SurveyConverterService;
import com.library.service.SurveyService;

@Controller
public class SurveysController extends BaseController implements SurveysResource {
	
	private final SurveyConverterService surveyConverter;
	private final SurveyService surveyService;
	
	@Autowired
	public SurveysController(SurveyConverterService surveyConverter, SurveyService surveyService) {
		super();
		this.surveyService = surveyService;
		this.surveyConverter = surveyConverter;
	}

	@Override
	public ResponseEntity<Response<SurveyDto>> create(SurveyDto surveyDto) {
		Survey survey = surveyConverter.toModel(surveyDto);
		Response<SurveyDto> response = createSuccessResponse(surveyConverter.toDto(surveyService.create(survey)));
		return ResponseEntity.ok(response);
	}

}
