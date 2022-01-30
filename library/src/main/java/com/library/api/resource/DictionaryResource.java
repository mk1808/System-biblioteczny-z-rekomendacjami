package com.library.api.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.AuthorDto;
import com.library.dto.GenreDto;
import com.library.dto.PublisherDto;
import com.library.response.Response;

@RestController
@RequestMapping("/api/dictionaries")
public interface DictionaryResource {
	
	@GetMapping("/authors")
	ResponseEntity<Response<List<AuthorDto>>> getAuthors();
	
	@GetMapping("/genres")
	ResponseEntity<Response<List<GenreDto>>> getGenres();
	
	@GetMapping("/publishers")
	ResponseEntity<Response<List<PublisherDto>>> getPublishers();

}
