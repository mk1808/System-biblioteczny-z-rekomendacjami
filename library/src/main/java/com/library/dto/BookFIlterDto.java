package com.library.dto;

import java.util.UUID;

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
public class BookFIlterDto {
	private String ISBN;
	private String title;
	private UUID authorId;
	private UUID publisherId;
	private UUID genreId;
	
	
}
