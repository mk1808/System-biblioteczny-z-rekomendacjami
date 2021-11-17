package com.library.dto;

import com.library.enums.BookCopyStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCopyDto {
	
	private Long id;
	private Long bookId;
	private BookDto book;
	private BookCopyStatus status;
}
