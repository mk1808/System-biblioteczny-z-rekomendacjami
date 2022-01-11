package com.library.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.library.model.AppUser;
import com.library.model.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDto {
	private UUID id;
	private BookDto book;
	private UUID bookId;

	private AppUserDto user;
	private UUID userId;
	
	private LocalDateTime reservationDate;
	private LocalDateTime availabilityStartDate;
	private Boolean wasBorrowed;
}
