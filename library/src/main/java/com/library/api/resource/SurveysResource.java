package com.library.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.SurveyDto;
import com.library.response.Response;

@RestController
@RequestMapping("/api/surveys")
public interface SurveysResource {
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping
	ResponseEntity<Response<SurveyDto>> create(@RequestBody SurveyDto survey);
}
