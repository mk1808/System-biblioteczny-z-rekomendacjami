package com.library.nosql.model;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

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
@Document("books")
public class BookData {
	
	@Id
	private String id;
	
	private List<String> genres;
	private List<String> keyWords;
	private List<String> authors;
	
	private List<String> genreIds;
	private List<String> keyWordIds;
	private List<String> authorIds;
	

}
