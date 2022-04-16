package com.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.model.Author;
import com.library.model.Genre;
import com.library.model.KeyWord;
import com.library.model.Publisher;
import com.library.repository.AuthorRepository;
import com.library.repository.GenreRepository;
import com.library.repository.KeyWordRepository;
import com.library.repository.PublisherRepository;
import com.library.service.DictionaryService;

@Service
public class DictionaryServiceImpl implements DictionaryService{
	private final AuthorRepository authorRepository;
	private final PublisherRepository publisherRepository;
	private final GenreRepository genreRepository;
	private final KeyWordRepository keyWordRepository;
	
	@Autowired
	public DictionaryServiceImpl(PublisherRepository publisherRepository, AuthorRepository authorRepository, GenreRepository genreRepository, KeyWordRepository keyWordRepository) {
		this.authorRepository = authorRepository;
		this.publisherRepository = publisherRepository;
		this.genreRepository = genreRepository;
		this.keyWordRepository = keyWordRepository;
		
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

	@Override
	public List<KeyWord> getKeyWords() {
		return keyWordRepository.findAll();
	}

}
