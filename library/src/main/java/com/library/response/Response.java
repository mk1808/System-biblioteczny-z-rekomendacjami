package com.library.response;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response<T> {
	private HttpStatus status;
	private T content;
	private List<ErrorResponse> errors;
	
}
