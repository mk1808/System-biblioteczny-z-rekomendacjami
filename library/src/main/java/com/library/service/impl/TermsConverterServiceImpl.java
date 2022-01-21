package com.library.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.BookCopyDto;
import com.library.dto.SeriesDto;
import com.library.dto.TermsDto;
import com.library.model.Series;
import com.library.model.Terms;
import com.library.service.TermsConverterService;

@Service
public class TermsConverterServiceImpl implements TermsConverterService {

	@Override
	public TermsDto toDto(Terms model) {
		TermsDto dto = TermsDto.builder()
				.id(model.getId())
				.content(model.getContent())
				.build();
		return dto;
	}

	@Override
	public Terms toModel(TermsDto dto) {
		Terms model = Terms.builder()
				.id(dto.getId())
				.content(dto.getContent())
				.build();
		return model;
	}

	@Override
	public List<TermsDto> toDtoList(List<Terms> models) {
		return models.stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public List<Terms> toModelList(List<TermsDto> dtos) {
		return dtos.stream().map(this::toModel).collect(Collectors.toList());
	}

	@Override
	public Page<TermsDto> toDtoPage(Page<Terms> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Terms> toModelPage(Page<TermsDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
