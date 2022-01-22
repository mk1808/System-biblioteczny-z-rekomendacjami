package com.library.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.library.api.resource.UsersResource;
import com.library.dto.AppUserDto;
import com.library.dto.BookDto;
import com.library.dto.UserFilterDto;
import com.library.model.AppUser;
import com.library.model.Opinion;
import com.library.response.Response;
import com.library.service.UserConverterService;
import com.library.service.UserService;

@Controller
public class UsersController extends BaseController implements UsersResource {
	
	private final UserService userService;
	private final UserConverterService userConverter;
	
	@Autowired
	public UsersController(UserService userService, UserConverterService userConverter) {
		super();
		this.userService = userService;
		this.userConverter = userConverter;
	}

	@Override
	public ResponseEntity<Response<AppUserDto>> getById(UUID id) {
		Response<AppUserDto> response = createSuccessResponse(userConverter.toDto(userService.get(id)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<AppUserDto>> update(AppUserDto userDto) {
		AppUser user = userConverter.toModel(userDto);
		Response<AppUserDto> response = createSuccessResponse(userConverter.toDto(userService.update(user)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<Page<AppUserDto>>> getFiltered(UserFilterDto userFIlter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<String>> create(AppUserDto userDto) {
		AppUser user = userConverter.toModel(userDto);
		userService.create(user);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<String>> updateByAdmin(AppUserDto userDto) {
		AppUser user = userConverter.toModel(userDto);
		userService.updateByAdmin(user);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<String>> deactivate(UUID id) {
		userService.deactivate(id);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}

}
