package com.library.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.library.model.AppUser;
import com.library.model.BookCopy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowingDto {
	private BookCopyDto bookCopy;
	private Long bookCopyId;
	private AppUserDto user;
	private Long userId;
	private LocalDateTime borrowDate;
	private LocalDateTime returnDate;
	private LocalDateTime expectedReturnDate;
	private Long numberOfProlongings;
}
