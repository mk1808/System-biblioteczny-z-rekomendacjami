package com.library.service;

import com.library.dto.BookDto;
import com.library.model.Book;

public interface ConverterService<D, M> extends Service {
	D toDto(M model);
	M toModel(D dto);
}
