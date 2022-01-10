package com.library.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.UserListElementDto;
import com.library.model.UserListElement;
import com.library.service.UserListElementConverterService;

@Service
public class UserListElementConverterServiceImpl implements UserListElementConverterService {

	@Override
	public UserListElementDto toDto(UserListElement model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserListElement toModel(UserListElementDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserListElementDto> toDtoList(List<UserListElement> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserListElement> toModelList(List<UserListElementDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UserListElementDto> toDtoPage(Page<UserListElement> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UserListElement> toModelPage(Page<UserListElementDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
