package com.library.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpinionDto {
	private UUID id;
	private BookDto book;
	private UUID bookId;

	private AppUserDto user;
	private UUID userId;
	
	private Long rating;
	private String comment;

}
