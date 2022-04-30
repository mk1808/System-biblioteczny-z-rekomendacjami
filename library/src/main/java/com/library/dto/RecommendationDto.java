package com.library.dto;

import java.util.Date;
import java.util.UUID;

import com.library.model.AppUser;
import com.library.model.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationDto {
	private UUID id;
	private BookDto book;
	private UUID bookId;

	private AppUserDto user;
	private UUID userId;
	
	private Long rating;
	private Boolean shouldNotRecommend;
	private Boolean shouldNotRecommendType;
	private Date created;
	private Date deleted;
}
