package com.library.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.library.api.resource.SurveysResource;
import com.library.dto.SurveyDto;
import com.library.response.Response;
import com.library.service.SurveyService;

@Controller
public class SurveysController extends BaseController implements SurveysResource {
	
	private final SurveyService surveyService;
	
	@Autowired
	public SurveysController(SurveyService surveyService) {
		super();
		this.surveyService = surveyService;
	}

	@Override
	public ResponseEntity<Response<String>> create(SurveyDto surveyDto) {
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}

}
