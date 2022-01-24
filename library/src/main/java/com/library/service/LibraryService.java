package com.library.service;

import com.library.model.Library;
import com.library.model.Terms;

public interface LibraryService extends RepositoryService<Library> {

	Library getContact();

	Terms getTerms();

}
