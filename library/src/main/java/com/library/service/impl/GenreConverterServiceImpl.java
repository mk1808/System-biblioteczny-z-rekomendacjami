package com.library.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.GenreDto;
import com.library.model.Genre;
import com.library.service.GenreConverterService;

@Service
public class GenreConverterServiceImpl implements GenreConverterService{

	@Override
	public GenreDto toDto(Genre model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genre toModel(GenreDto dto) {
		// TODO Auto-generated method stub
		return null;
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
