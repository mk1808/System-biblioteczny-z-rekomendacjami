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
public class BookAvailabilityDto {
	private UUID bookId;
	private int allBooks;
	private int available;
	private int borrowedBooks;
	private int numberOfReservations;
	private int keptTooLong;
	private BookCopyStatus status;
	
}
