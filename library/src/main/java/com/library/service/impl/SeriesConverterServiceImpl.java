package com.library.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.BookCopyDto;
import com.library.dto.SeriesDto;
import com.library.model.Series;
import com.library.service.SeriesConverterService;

@Service
public class SeriesConverterServiceImpl implements SeriesConverterService{

	@Override
	public SeriesDto toDto(Series model) {
		BookCopyDto dto = BookCopyDto.builder()
				.id(Long.valueOf(model.getId().toString()))
				.name(model.getName())
				.surname(model.getSurname())
				.description(model.getDescription())
				.build();
		return dto;
	}

	@Override
	public Series toModel(SeriesDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SeriesDto> toDtoList(List<Series> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Series> toModelList(List<SeriesDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SeriesDto> toDtoPage(Page<Series> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Series> toModelPage(Page<SeriesDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
