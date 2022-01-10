package com.library.service;

import org.springframework.stereotype.Service;

import com.library.dto.BookDto;
import com.library.model.Book;

public interface BookConverterService extends ConverterService <BookDto, Book>{

}
