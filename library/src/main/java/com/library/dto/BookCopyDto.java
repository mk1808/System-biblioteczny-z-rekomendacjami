package com.library.dto;

import java.util.UUID;

import com.library.enums.BookCopyStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCopyDto {
	
	private UUID id;
	private UUID bookId;
	private BookDto book;
	private BookCopyStatus status;
}
