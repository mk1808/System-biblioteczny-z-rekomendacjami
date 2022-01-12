package com.library.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.BookCopyDto;
import com.library.dto.OpinionDto;
import com.library.model.BookCopy;
import com.library.model.Opinion;
import com.library.service.OpinionConverterService;

@Service
public class OpinionConverterServiceImpl implements OpinionConverterService {

	@Override
	public OpinionDto toDto(Opinion model) {
		BookCopyDto dto = BookCopyDto.builder()
				.id(Long.valueOf(model.getId().toString()))
				.name(model.getName())
				.surname(model.getSurname())
				.description(model.getDescription())
				.build();
		return dto;
	}

	@Override
	public Opinion toModel(OpinionDto dto) {
		BookCopy model = BookCopy.builder()
				.id(UUID.fromString(dto.getId().toString()))
				.build();
		return model;
	}

	@Override
	public List<OpinionDto> toDtoList(List<Opinion> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Opinion> toModelList(List<OpinionDto> dtos) {
		// TODO Auto-generated method stub
		return null;
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
