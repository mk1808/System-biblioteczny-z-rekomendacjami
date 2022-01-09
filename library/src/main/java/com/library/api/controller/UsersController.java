package com.library.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.library.api.resource.UsersResource;
import com.library.dto.AppUserDto;
import com.library.dto.UserFilterDto;
import com.library.response.Response;

@Controller
public class UsersController implements UsersResource {

	@Override
	public ResponseEntity<Response<AppUserDto>> getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<AppUserDto>> update(AppUserDto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<Page<AppUserDto>>> getFiltered(UserFilterDto userFIlter) {
		// TODO Auto-generated method stub
		return null;
	}

}
