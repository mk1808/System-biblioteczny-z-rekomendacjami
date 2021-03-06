package com.library.dto;

import java.util.UUID;

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
public class CanBorrowBookDto {
	private UUID id;
	private BookDto book;
	private BookCopyDto bookCopy;
	private AppUserDto user;
	private BookAvailabilityDto bookAvailabilityDto;
	private UserAvailabilityDto userAvailabilityDto;
	private Boolean isReservedByUser;
	private Boolean canBorrow;
	private UUID reservationId;
	
}
