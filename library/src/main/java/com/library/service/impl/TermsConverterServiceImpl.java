package com.library.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.BookCopyDto;
import com.library.dto.TermsDto;
import com.library.model.Terms;
import com.library.service.TermsConverterService;

@Service
public class TermsConverterServiceImpl implements TermsConverterService {

	@Override
	public TermsDto toDto(Terms model) {
		BookCopyDto dto = BookCopyDto.builder()
				.id(Long.valueOf(model.getId().toString()))
				.name(model.getName())
				.surname(model.getSurname())
				.description(model.getDescription())
				.build();
		return dto;
	}

	@Override
	public Terms toModel(TermsDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TermsDto> toDtoList(List<Terms> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Terms> toModelList(List<TermsDto> dtos) {
		// TODO Auto-generated method stub
		return null;
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
