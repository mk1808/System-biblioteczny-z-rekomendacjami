package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationDto {
	private Long id;
	private BookDto book;
	private Long bookId;

	private AppUserDto user;
	private Long userId;
	
	private Long rating;
	private Boolean shouldNotRecommend;
	private Boolean shouldNotRecommendType;
}