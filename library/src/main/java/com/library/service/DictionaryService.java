package com.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.model.Author;
import com.library.model.Genre;
import com.library.model.KeyWord;
import com.library.model.Publisher;

@Service
public interface DictionaryService {

	List<Author> getAuthors();

	List<Genre> getGenres();

	List<Publisher> getPublishers();

	List<KeyWord> getKeyWords();

	
	

}
