package com.library.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.KeyWordDto;
import com.library.model.KeyWord;
import com.library.service.KeyWordConverterService;

@Service
public class KeyWordConverterServiceImpl implements KeyWordConverterService{

	@Override
	public KeyWordDto toDto(KeyWord model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyWord toModel(KeyWordDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KeyWordDto> toDtoList(List<KeyWord> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KeyWord> toModelList(List<KeyWordDto> dtos) {
		// TODO Auto-generated method stub
		return null;
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
