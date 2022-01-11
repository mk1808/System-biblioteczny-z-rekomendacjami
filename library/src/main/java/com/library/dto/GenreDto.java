package com.library.dto;

import java.util.List;
import java.util.UUID;

import com.library.model.Author;
import com.library.model.Genre;
import com.library.model.KeyWord;
import com.library.model.Publisher;
import com.library.model.Series;

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
public class GenreDto {
	private UUID id;
	private String name;

}
