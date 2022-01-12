package com.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.BookCopyDto;
import com.library.dto.OpinionDto;
import com.library.dto.RecommendationDto;
import com.library.model.AppUser;
import com.library.model.Book;
import com.library.model.Opinion;
import com.library.model.Recommendation;
import com.library.service.BookConverterService;
import com.library.service.RecommendationConverterService;
import com.library.service.UserConverterService;

@Service
public class RecommendationConverterServiceImpl implements RecommendationConverterService {

	private final BookConverterService bookConverter;
	private final UserConverterService userConverter;
		
		@Autowired
		public RecommendationConverterServiceImpl(BookConverterService bookConverter, 
				UserConverterService userConverter) {
			this.bookConverter = bookConverter;
			this.userConverter = userConverter;
		}
	
	@Override
	public RecommendationDto toDto(Recommendation model) {
		RecommendationDto dto = RecommendationDto.builder()
				.id(model.getId())
				.book(bookConverter.toDto(model.getBook()))
				.bookId(model.getBook().getId())
				.user(userConverter.toDto(model.getUser()))
				.userId(model.getUser().getId())
				
				.rating(model.getRating())
				.shouldNotRecommend(model.getShouldNotRecommend())
				.shouldNotRecommendType(model.getShouldNotRecommendType())
				.build();
		return dto;
	}

	@Override
	public Recommendation toModel(RecommendationDto dto) {
		Recommendation model = Recommendation.builder()
				.id(dto.getId())
				.book(Book.builder().id(dto.getBookId()).build())
				.user(AppUser.builder().id(dto.getUserId()).build())
				.rating(dto.getRating())
				.shouldNotRecommend(dto.getShouldNotRecommend())
				.shouldNotRecommendType(dto.getShouldNotRecommendType())
				.build();
		return model;
	}

	@Override
	public List<RecommendationDto> toDtoList(List<Recommendation> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recommendation> toModelList(List<RecommendationDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RecommendationDto> toDtoPage(Page<Recommendation> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Recommendation> toModelPage(Page<RecommendationDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
