package com.library.dto;

import java.util.UUID;

import com.library.enums.ChangeProposalStatus;

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
