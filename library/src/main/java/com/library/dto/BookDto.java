package com.library.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.library.enums.Role;

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
public class BookDto {

	private UUID id;
	private String ISBN;
	private String title;
	private String publicationYear;
	private String originalTitle;
	private String descrpition;
	private String photo;
	private UUID publisherId;
	private String publisherName;
	private Date created;
	private Double avgRating;
	
	private List<AuthorDto> authors;
	private List<GenreDto> genres;
	private List<SeriesDto> series;
	private List<KeyWordDto> keyWords;
	
	
}
