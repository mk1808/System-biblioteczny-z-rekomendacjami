package com.library.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.AuthorDto;
import com.library.model.Author;
import com.library.service.AuthorConverterService;

@Service
public class AuthorConverterServiceImpl implements AuthorConverterService{

	@Override
	public AuthorDto toDto(Author model) {
		AuthorDto dto = AuthorDto.builder()
				.id(model.getId())
				.name(model.getName())
				.surname(model.getSurname())
				.description(model.getDescription())
				.build();
		return dto;
	}

	@Override
	public Author toModel(AuthorDto dto) {
		Author model = Author.builder()
				.id(dto.getId())
				.name(dto.getName())
				.surname(dto.getSurname())
				.description(dto.getDescription())
				.build();
		return model;
	}

	@Override
	public List<AuthorDto> toDtoList(List<Author> models) {
		return models.stream().map(model->toDto(model)).collect(Collectors.toList());
	}

	@Override
	public List<Author> toModelList(List<AuthorDto> dtos) {
		return dtos.stream().map(this::toModel).collect(Collectors.toList());
	}

	@Override
	public Page<AuthorDto> toDtoPage(Page<Author> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Author> toModelPage(Page<AuthorDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
