package com.library.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.library.enums.Role;
import com.library.model.AppUser;
import com.library.model.BookCopy;

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
public class BorrowingDto {
	private UUID id;
	private BookCopyDto bookCopy;
	private UUID bookCopyId;
	private AppUserDto user;
	private UUID userId;
	private LocalDateTime borrowDate;
	private LocalDateTime returnDate;
	private LocalDateTime expectedReturnDate;
	private Long numberOfProlongings;
}
