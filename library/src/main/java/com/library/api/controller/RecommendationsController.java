package com.library.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.library.api.resource.RecommendationsResource;
import com.library.dto.RecommendationDto;
import com.library.response.Response;

@Controller
public class RecommendationsController implements RecommendationsResource{

	@Override
	public ResponseEntity<Response<Page<RecommendationDto>>> getByUserId(Long userId, Long pageNo, Long pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<String>> updateInfo(RecommendationDto recommendation) {
		// TODO Auto-generated method stub
		return null;
	}

}
