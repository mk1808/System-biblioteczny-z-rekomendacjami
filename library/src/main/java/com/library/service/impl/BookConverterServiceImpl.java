package com.library.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.BookDto;
import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Genre;
import com.library.model.KeyWord;
import com.library.model.Publisher;
import com.library.model.Series;
import com.library.service.AuthorConverterService;
import com.library.service.BookConverterService;
import com.library.service.GenreConverterService;
import com.library.service.KeyWordConverterService;
import com.library.service.PublisherService;
import com.library.service.SeriesConverterService;

import lombok.Getter;
import lombok.Setter;

@Service
public class BookConverterServiceImpl implements BookConverterService { 
	
	@Setter private List<Author> authors = new ArrayList<>();
	@Setter private List<Genre> genres = new ArrayList<>();
	@Setter private List<Series> series= new ArrayList<>();
	@Setter private List<KeyWord> keywords = new ArrayList<>();
	
	private final AuthorConverterService authorConverter;
	private final GenreConverterService genreConverter;
	private final SeriesConverterService seriesConverter;
	private final KeyWordConverterService keyWordConverter;
	
	public BookConverterServiceImpl(AuthorConverterService authorConverter,
			GenreConverterService genreConverter,
			SeriesConverterService seriesConverter,
			KeyWordConverterService keyWordConverter
			) {
		super();
		this.authorConverter = authorConverter;
		this.genreConverter = genreConverter;
		this.seriesConverter = seriesConverter;
		this.keyWordConverter = keyWordConverter;
	}
	
	
	@Override
	public BookDto toDto(Book model) {
		BookDto dto = BookDto.builder()
				.id(model.getId())
				.ISBN(model.getISBN())
				.title(model.getTitle())
				.publicationYear(model.getPublicationYear())
				.originalTitle(model.getOriginalTitle())
				.descrpition(model.getDescrpition())
				.photo(model.getPhoto())
				.publisherId(model.getPublisher().getId())
				.publisherName(model.getPublisher().getName())
				.authors(authorConverter.toDtoList(authors))
				.genres(genreConverter.toDtoList(genres))
				.series(seriesConverter.toDtoList(series))
				.keyWords(keyWordConverter.toDtoList(keywords))
				.build();
				
		return dto;
	}
	

	@Override
	public Book toModel(BookDto dto) {
		Book model = Book.builder()
				.ISBN(dto.getISBN())
				.title(dto.getTitle())
				.publicationYear(dto.getPublicationYear())
				.originalTitle(dto.getOriginalTitle())
				.descrpition(dto.getDescrpition())
				.photo(dto.getPhoto())
				.publisher(Publisher.builder().id(dto.getId()).build())
				.build();
		model.setId(dto.getId());
				
		return model;
	}

	@Override
	public List<BookDto> toDtoList(List<Book> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> toModelList(List<BookDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<BookDto> toDtoPage(Page<Book> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Book> toModelPage(Page<BookDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}






}
