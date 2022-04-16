package com.library.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.library.api.resource.DictionaryResource;
import com.library.dto.AuthorDto;
import com.library.dto.BookDto;
import com.library.dto.GenreDto;
import com.library.dto.PublisherDto;
import com.library.dto.KeyWordDto;
import com.library.model.Book;
import com.library.response.Response;
import com.library.service.BorrowingConverterService;
import com.library.service.BorrowingService;
import com.library.service.DictionaryService;
import com.library.service.PublisherConverterService;


import com.library.service.AuthorConverterService;
import com.library.service.GenreConverterService;
import com.library.service.KeyWordConverterService;

@RestController
public class DictionaryController extends BaseController implements DictionaryResource{

	private final AuthorConverterService authorConverter;
	private final PublisherConverterService publisherConverter;
	private final GenreConverterService genreConverter;
	private final KeyWordConverterService keyWordConverter;
	private final DictionaryService dictionaryService;
	
	@Autowired
	public DictionaryController(AuthorConverterService authorConverter, PublisherConverterService publisherConverter, 
			GenreConverterService genreConverter, KeyWordConverterService keyWordConverter, DictionaryService dictionaryService) {
		super();
		this.authorConverter = authorConverter;
		this.publisherConverter = publisherConverter;
		this.genreConverter = genreConverter;
		this.keyWordConverter = keyWordConverter;
		this.dictionaryService = dictionaryService;
	}
	
	
	@Override
	public ResponseEntity<Response<List<AuthorDto>>> getAuthors() {
		Response<List<AuthorDto>> response = createSuccessResponse(authorConverter.toDtoList(dictionaryService.getAuthors()));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<List<GenreDto>>> getGenres() {
		Response<List<GenreDto>> response = createSuccessResponse(genreConverter.toDtoList(dictionaryService.getGenres()));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<List<PublisherDto>>> getPublishers() {
		Response<List<PublisherDto>> response = createSuccessResponse(publisherConverter.toDtoList(dictionaryService.getPublishers()));
		return ResponseEntity.ok(response);
	}


	@Override
	public ResponseEntity<Response<List<KeyWordDto>>> getKeywords() {
		Response<List<KeyWordDto>> response = createSuccessResponse(keyWordConverter.toDtoList(dictionaryService.getKeyWords()));
		return ResponseEntity.ok(response);
	}
	
	

}
