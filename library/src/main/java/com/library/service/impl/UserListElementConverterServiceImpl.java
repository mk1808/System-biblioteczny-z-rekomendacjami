package com.library.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.BookCopyDto;
import com.library.dto.UserListElementDto;
import com.library.model.BookCopy;
import com.library.model.UserListElement;
import com.library.service.UserListElementConverterService;

@Service
public class UserListElementConverterServiceImpl implements UserListElementConverterService {

	@Override
	public UserListElementDto toDto(UserListElement model) {
		BookCopyDto dto = BookCopyDto.builder()
				.id(Long.valueOf(model.getId().toString()))
				.name(model.getName())
				.surname(model.getSurname())
				.description(model.getDescription())
				.build();
		return dto;
	}

	@Override
	public UserListElement toModel(UserListElementDto dto) {
		BookCopy model = BookCopy.builder()
				.id(UUID.fromString(dto.getId().toString()))
				.build();
		return model;
	}

	@Override
	public List<UserListElementDto> toDtoList(List<UserListElement> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserListElement> toModelList(List<UserListElementDto> dtos) {
		// TODO Auto-generated method stub
		return null;
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
