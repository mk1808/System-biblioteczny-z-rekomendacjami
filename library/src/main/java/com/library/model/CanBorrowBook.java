package com.library.model;

import java.util.UUID;

import com.library.dto.BookAvailabilityDto;
import com.library.dto.UserAvailabilityDto;

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
public class CanBorrowBook {
	private Book book;
	private BookCopy bookCopy;
	private AppUser user;
	private BookAvailabilityDto bookAvailabilityDto;
	private UserAvailabilityDto userAvailabilityDto;
	private Boolean canBorrow;
	private Boolean isReservedByUser;
	private UUID reservationId;

}
