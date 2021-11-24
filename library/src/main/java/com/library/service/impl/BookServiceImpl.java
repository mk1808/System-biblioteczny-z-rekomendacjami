package com.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.service.BookService;

public class BookServiceImpl implements BookService {
private BookRepository repository;
	
	
	@Autowired
	public BookServiceImpl(BookRepository repository) {
		this.repository = repository;
	}


	@Override
	public Book get(Long id) {
		return repository.getById(id);
	}

	@Override
	public Book create(Book entity) {
		return repository.save(entity);
	}

	@Override
	public Book update(Book entity) {
		return null;
	}


}
