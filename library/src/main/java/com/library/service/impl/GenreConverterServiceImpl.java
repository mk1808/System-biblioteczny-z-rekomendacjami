package com.library.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.BookCopyDto;
import com.library.dto.GenreDto;
import com.library.model.BookCopy;
import com.library.model.Genre;
import com.library.service.GenreConverterService;

@Service
public class GenreConverterServiceImpl implements GenreConverterService{

	@Override
	public GenreDto toDto(Genre model) {
		GenreDto dto = GenreDto.builder()
				.id(model.getId())
				.name(model.getName())
				.build();
		return dto;
	}
	
	@Override
	public Genre toModel(GenreDto dto) {
		Genre model = Genre.builder()
				.id(dto.getId())
				.name(dto.getName())
				.build();
		return model;
	}

	@Override
	public List<GenreDto> toDtoList(List<Genre> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Genre> toModelList(List<GenreDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<GenreDto> toDtoPage(Page<Genre> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Genre> toModelPage(Page<GenreDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
