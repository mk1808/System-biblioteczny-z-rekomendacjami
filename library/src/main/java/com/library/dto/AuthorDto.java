package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto {

	private Long id;
	private String name;
	private String surname;
	private String description;
}
