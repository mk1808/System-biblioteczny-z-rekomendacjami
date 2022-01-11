package com.library.service;

import java.util.List;

import com.library.model.Library;
import com.library.model.Terms;

public interface LibraryService extends RepositoryService<Library> {

	List<Library> getContact();

	List<Terms> getTerms();

}
