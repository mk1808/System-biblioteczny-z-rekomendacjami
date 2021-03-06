package com.library.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.AuthorDto;
import com.library.dto.BookDto;
import com.library.dto.GenreDto;
import com.library.dto.KeyWordDto;
import com.library.dto.SeriesDto;
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
		if(model == null) return null;
		BookDto dto = BookDto.builder()
				.id(model.getId())
				.ISBN(model.getISBN())
				.title(model.getTitle())
				.publicationYear(model.getPublicationYear())
				.originalTitle(model.getOriginalTitle())
				.descrpition(model.getDescrpition())
				.photo(model.getPhoto())
				.created(model.getCreated())
				.publisherId(model.getPublisher().getId())
				.publisherName(model.getPublisher().getName())
				.avgRating(model.getAvgRating())
				.authors(authorConverter.toDtoList(model.getBookAuthors().stream().map(x->x.getAuthor()).collect(Collectors.toList())))
				.genres(genreConverter.toDtoList(model.getBookGenres().stream().map(x->x.getGenre()).collect(Collectors.toList())))
				.series(seriesConverter.toDtoList(model.getBookSeries().stream().map(x->x.getSeries()).collect(Collectors.toList())))
				.keyWords(keyWordConverter.toDtoList(model.getBookKeyWords().stream().map(x->x.getKeyWord()).collect(Collectors.toList())))
				.build();
				
		return dto;
	}
	

	@Override
	public Book toModel(BookDto dto) {
		Book model = Book.builder()
				.id(dto.getId())
				.ISBN(dto.getISBN())
				.title(dto.getTitle())
				.publicationYear(dto.getPublicationYear())
				.originalTitle(dto.getOriginalTitle())
				.descrpition(dto.getDescrpition())
				.photo(dto.getPhoto())
				.created(dto.getCreated())
				.publisher(Publisher.builder().id(dto.getId()).build())
				.avgRating(dto.getAvgRating())
				.authors(createDtos(dto.getAuthors(), this::createAuthorDto))
				.genres(createDtos(dto.getGenres(), this::createGenreDto))
				.keyWords(createDtos(dto.getKeyWords(), this::createKeyWordDto))
				//.series(createDtos(dto.getSeries(), this::createSeriesDto))
				.build();
				
		return model;
	}

	@Override
	public List<BookDto> toDtoList(List<Book> models) {
		return models.stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public List<Book> toModelList(List<BookDto> dtos) {
		return dtos.stream().map(this::toModel).collect(Collectors.toList());
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

	private <M,D> List<M> createDtos(List<D> dtos, Function<D, M>function) {
		return dtos.stream().map(function).collect(Collectors.toList());
	}
	
	private Author createAuthorDto(AuthorDto dto) {
		return Author.builder().id(UUID.fromString(dto.getId().toString())).build();
	}
	
	private Genre createGenreDto(GenreDto dto) {
		return Genre.builder().id(UUID.fromString(dto.getId().toString())).build();
	}
	
	private Series createSeriesDto(SeriesDto dto) {
		return Series.builder().id(UUID.fromString(dto.getId().toString())).build();
	}

	private KeyWord createKeyWordDto(KeyWordDto dto) {
		return KeyWord.builder().id(UUID.fromString(dto.getId().toString())).build();
	}



}
