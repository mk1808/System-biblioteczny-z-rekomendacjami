package com.library.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.library.api.resource.RecommendationsResource;
import com.library.dto.BookDto;
import com.library.dto.RecommendationDto;
import com.library.model.Opinion;
import com.library.model.Recommendation;
import com.library.response.Response;
import com.library.service.RecommendationConverterService;
import com.library.service.RecommendationService;

@Controller
public class RecommendationsController extends BaseController implements RecommendationsResource{

	private final RecommendationConverterService recommendationConverter;
	private final RecommendationService recommendationService;
	
	@Autowired
	public RecommendationsController(RecommendationConverterService recommendationConverter,
			RecommendationService recommendationService) {
		super();
		this.recommendationConverter = recommendationConverter;
		this.recommendationService = recommendationService;
	}

	@Override
	public ResponseEntity<Response<List<RecommendationDto>>> getByUserId(UUID userId, Long pageNo, Long pageSize) {
		Response<List<RecommendationDto>> response = createSuccessResponse(recommendationConverter
				.toDtoList(recommendationService.getByUserId(userId)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<String>> updateInfo(RecommendationDto recommendationDto) {
		Recommendation recommendation = recommendationConverter.toModel(recommendationDto);
		recommendationService.update(recommendation);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}

}
