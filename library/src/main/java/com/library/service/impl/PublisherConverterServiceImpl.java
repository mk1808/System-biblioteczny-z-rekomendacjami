package com.library.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.PublisherDto;
import com.library.model.Publisher;
import com.library.service.PublisherConverterService;


@Service
public class PublisherConverterServiceImpl implements PublisherConverterService{

	@Override
	public PublisherDto toDto(Publisher model) {
		PublisherDto dto = PublisherDto.builder()
				.id(model.getId())
				.name(model.getName())
				.description(model.getDescription())
				.build();
		return dto;
	}

	@Override
	public Publisher toModel(PublisherDto dto) {
		Publisher model = Publisher.builder()
				.id(dto.getId())
				.name(dto.getName())
				.description(dto.getDescription())
				.build();
		return model;
	}

	@Override
	public List<PublisherDto> toDtoList(List<Publisher> models) {
		return models.stream().map(model->toDto(model)).collect(Collectors.toList());
	}

	@Override
	public List<Publisher> toModelList(List<PublisherDto> dtos) {
		return dtos.stream().map(this::toModel).collect(Collectors.toList());
	}

	@Override
	public Page<PublisherDto> toDtoPage(Page<Publisher> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Publisher> toModelPage(Page<PublisherDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
