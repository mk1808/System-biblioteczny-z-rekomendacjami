package com.library.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.BookCopyDto;
import com.library.dto.KeyWordDto;
import com.library.model.BookCopy;
import com.library.model.KeyWord;
import com.library.service.KeyWordConverterService;

@Service
public class KeyWordConverterServiceImpl implements KeyWordConverterService{

	@Override
	public KeyWordDto toDto(KeyWord model) {
		KeyWordDto dto = KeyWordDto.builder()
				.id(model.getId())
				.name(model.getName())
				.isVerified(model.getIsVerified())
				.build();
		return dto;
	}

	@Override
	public KeyWord toModel(KeyWordDto dto) {
		KeyWord model = KeyWord.builder()
				.id(dto.getId())
				.name(dto.getName())
				.isVerified(dto.getIsVerified())
				.build();
		return model;
	}

	@Override
	public List<KeyWordDto> toDtoList(List<KeyWord> models) {
		return models.stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public List<KeyWord> toModelList(List<KeyWordDto> dtos) {
		return dtos.stream().map(this::toModel).collect(Collectors.toList());
	}

	@Override
	public Page<KeyWordDto> toDtoPage(Page<KeyWord> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<KeyWord> toModelPage(Page<KeyWordDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
