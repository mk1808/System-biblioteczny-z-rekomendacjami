package com.library.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

	private Long id;
	private String ISBN;
	private String title;
	private String publicationYear;
	private String originalTitle;
	private String descrpition;
	private String photo;
	private Long publisherId;
	private String publisherName;
	private List<AuthorDto> authors;
	private List<GenreDto> genres;
	private List<SeriesDto> series;
	private List<String> keyWords;
	
	
}
