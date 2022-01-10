package com.library.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.OpinionDto;
import com.library.model.Opinion;
import com.library.service.OpinionConverterService;

@Service
public class OpinionConverterServiceImpl implements OpinionConverterService {

	@Override
	public OpinionDto toDto(Opinion model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Opinion toModel(OpinionDto dto) {
		// TODO Auto-generated method stub
		return null;
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
