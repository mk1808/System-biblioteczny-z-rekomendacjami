package com.library.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.model.Recommendation;
import com.library.nosql.model.RecommendationData;
import com.library.repository.AppUserRepository;
import com.library.repository.BookRepository;
import com.library.service.RecommendationDataConverterService;

@Service
public class RecommendationDataConverterServiceImpl implements RecommendationDataConverterService {

	private final BookRepository bookRepository;
	private final AppUserRepository userRepository;
	
	@Autowired
	public RecommendationDataConverterServiceImpl(BookRepository bookRepository, AppUserRepository userRepository) {
		super();
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public Recommendation toModel(RecommendationData noSQLModel) {
		Recommendation model = Recommendation.builder()
				//.id(UUID.fromString(noSQLModel.getId()))
				.book(bookRepository.getById(UUID.fromString(noSQLModel.getBookId())))
				.user(userRepository.findByMail(noSQLModel.getUserId()))
				.rating(noSQLModel.getRating())
				.shouldNotRecommend(noSQLModel.getShouldNotRecommend())
				.shouldNotRecommendType(noSQLModel.getShouldNotRecommendType())
				.created(noSQLModel.getCreated())
				.deleted(noSQLModel.getDeleted())
				.build();
		return model;
	}
	
	@Override
	public List<Recommendation> toModelList(List<RecommendationData> noSQLModels) {
		return noSQLModels.stream().map(this::toModel).collect(Collectors.toList());
	}

}
