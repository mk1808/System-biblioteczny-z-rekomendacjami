package com.library.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.library.response.ErrorResponse;
import com.library.response.Response;

public class BaseController{
	
	protected <T> Response<T> createSuccessResponse(T content) {
		Response<T> response = Response.<T> builder()
				.content(content)
				.status(HttpStatus.OK)
				.build();
		return response;
	}


	protected <T> Response<T> createErrorResponse(T content, HttpStatus status, String message) {
		Response<T> response = Response.<T> builder()
				.errors(List.of(ErrorResponse.builder().message(message).build()))
				.status(status)
				.build();
		return response;
	}
}
