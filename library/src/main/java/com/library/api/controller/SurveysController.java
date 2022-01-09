package com.library.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.library.api.resource.SurveysResource;
import com.library.dto.SurveyDto;
import com.library.response.Response;

@Controller
public class SurveysController implements SurveysResource {

	@Override
	public ResponseEntity<Response<String>> create(SurveyDto survey) {
		// TODO Auto-generated method stub
		return null;
	}

}
