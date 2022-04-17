package com.library.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.BookCopyDto;
import com.library.dto.OpinionDto;
import com.library.model.AppUser;
import com.library.model.Book;
import com.library.model.BookCopy;
import com.library.model.Opinion;
import com.library.service.BookConverterService;
import com.library.service.OpinionConverterService;
import com.library.service.UserConverterService;

@Service
public class OpinionConverterServiceImpl implements OpinionConverterService {

	private final BookConverterService bookConverter;
	private final UserConverterService userConverter;
		
		@Autowired
		public OpinionConverterServiceImpl(BookConverterService bookConverter, 
				UserConverterService userConverter) {
			this.bookConverter = bookConverter;
			this.userConverter = userConverter;
		}
	
	@Override
	public OpinionDto toDto(Opinion model) {
		OpinionDto dto = OpinionDto.builder()
				.id(model.getId())
				.book(bookConverter.toDto(model.getBook()))
				.bookId(model.getBook().getId())
				.user(userConverter.toDto(model.getUser()))
				.userId(model.getUser().getId())
				.rating(model.getRating())
				.comment(model.getComment())
				.created(model.getCreated())
				.build();
		return dto;
	}

	@Override
	public Opinion toModel(OpinionDto dto) {
		Opinion model = Opinion.builder()
				.id(dto.getId())
				.book(Book.builder().id(dto.getBookId()).build())
				.user(AppUser.builder().id(dto.getUserId()).build())
				.rating(dto.getRating())
				.comment(dto.getComment())
				.created(dto.getCreated())
				.build();
		return model;
	}

	@Override
	public List<OpinionDto> toDtoList(List<Opinion> models) {
		return models.stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public List<Opinion> toModelList(List<OpinionDto> dtos) {
		return dtos.stream().map(this::toModel).collect(Collectors.toList());
	}

	@Override
	public Page<OpinionDto> toDtoPage(Page<Opinion> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Opinion> toModelPage(Page<OpinionDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
