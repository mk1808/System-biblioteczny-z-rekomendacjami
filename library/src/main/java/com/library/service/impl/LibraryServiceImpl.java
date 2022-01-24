package com.library.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.model.Library;
import com.library.model.Terms;
import com.library.repository.LibraryRepository;
import com.library.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {

private final LibraryRepository repository;
	
	
	@Autowired
	public LibraryServiceImpl(LibraryRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Library get(UUID id) {
		return repository.getById(id);
	}

	@Override
	public Library create(Library entity) {
		return repository.save(entity);
	}

	@Override
	public Library update(Library entity) {
		return repository.save(entity);
	}

	@Override
	public Library getContact() {
		return repository.findAll().get(0);
	}

	@Override
	public Terms getTerms() {
		return repository.findAll().get(0).getTerms();
	}

}
