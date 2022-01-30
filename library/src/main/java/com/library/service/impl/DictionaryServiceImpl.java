package com.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.model.Author;
import com.library.model.Genre;
import com.library.model.Publisher;
import com.library.repository.AuthorRepository;
import com.library.repository.GenreRepository;
import com.library.repository.PublisherRepository;
import com.library.service.DictionaryService;

@Service
public class DictionaryServiceImpl implements DictionaryService{
	private final AuthorRepository authorRepository;
	private final PublisherRepository publisherRepository;
	private final GenreRepository genreRepository;
	
	@Autowired
	public DictionaryServiceImpl(PublisherRepository publisherRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
		this.authorRepository = authorRepository;
		this.publisherRepository = publisherRepository;
		this.genreRepository = genreRepository;
		
	}
	
	@Override
	public List<Author> getAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public List<Genre> getGenres() {
		return genreRepository.findAll();
	}

	@Override
	public List<Publisher> getPublishers() {
		return publisherRepository.findAll();
	}

}
