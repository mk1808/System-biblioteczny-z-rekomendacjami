package com.library.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.BookCopyDto;
import com.library.dto.UserListElementDto;
import com.library.model.AppUser;
import com.library.model.Book;
import com.library.model.BookCopy;
import com.library.model.UserListElement;
import com.library.service.BookConverterService;
import com.library.service.UserConverterService;
import com.library.service.UserListElementConverterService;

@Service
public class UserListElementConverterServiceImpl implements UserListElementConverterService {
	
	private final BookConverterService bookConverter;
	private final UserConverterService userConverter;
		
		@Autowired
		public UserListElementConverterServiceImpl(BookConverterService bookConverter, 
				UserConverterService userConverter) {
			this.bookConverter = bookConverter;
			this.userConverter = userConverter;
		}
		
	@Override
	public UserListElementDto toDto(UserListElement model) {
		UserListElementDto dto = UserListElementDto.builder()
				.id(model.getId())
				.book(bookConverter.toDto(model.getBook()))
				.bookId(model.getBook().getId())
				.user(userConverter.toDto(model.getUser()))
				.userId(model.getUser().getId())
				.type(model.getType())
				.deleteDate(model.getDeleteDate())
				.build();
		return dto;
	}

	@Override
	public UserListElement toModel(UserListElementDto dto) {
		UserListElement model = UserListElement.builder()
				.id(dto.getId())
				.book(Book.builder().id(dto.getBookId()).build())
				.user(AppUser.builder().id(dto.getUserId()).build())
				.type(dto.getType())
				.deleteDate(dto.getDeleteDate())
				.build();
		return model;
	}

	@Override
	public List<UserListElementDto> toDtoList(List<UserListElement> models) {
		return models.stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public List<UserListElement> toModelList(List<UserListElementDto> dtos) {
		return dtos.stream().map(this::toModel).collect(Collectors.toList());
	}

	@Override
	public Page<UserListElementDto> toDtoPage(Page<UserListElement> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UserListElement> toModelPage(Page<UserListElementDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
