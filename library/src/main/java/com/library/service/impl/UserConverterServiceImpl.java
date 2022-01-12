package com.library.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.AppUserDto;
import com.library.dto.BookCopyDto;
import com.library.model.AppUser;
import com.library.service.UserConverterService;

@Service
public class UserConverterServiceImpl implements UserConverterService {

	@Override
	public AppUserDto toDto(AppUser model) {
		BookCopyDto dto = BookCopyDto.builder()
				.id(Long.valueOf(model.getId().toString()))
				.name(model.getName())
				.surname(model.getSurname())
				.description(model.getDescription())
				.build();
		return dto;
	}

	@Override
	public AppUser toModel(AppUserDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppUserDto> toDtoList(List<AppUser> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppUser> toModelList(List<AppUserDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<AppUserDto> toDtoPage(Page<AppUser> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<AppUser> toModelPage(Page<AppUserDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
