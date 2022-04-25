package com.library.nosql.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus.Series;

import com.library.model.Author;
import com.library.model.BookAuthor;
import com.library.model.BookGenre;
import com.library.model.BookInSeries;
import com.library.model.BookKeyWord;
import com.library.model.Genre;
import com.library.model.KeyWord;
import com.library.model.Publisher;

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
