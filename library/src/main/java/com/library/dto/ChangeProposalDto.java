package com.library.dto;

import java.util.UUID;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.library.enums.BookCopyStatus;
import com.library.enums.ChangeProposalStatus;
import com.library.model.AppUser;
import com.library.model.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeProposalDto {
	private UUID id;
	private String value;
	private String type;
	private ChangeProposalStatus status;
	private BookDto book;
	private UUID bookId;
	private AppUserDto user;
	private UUID userId;
}
