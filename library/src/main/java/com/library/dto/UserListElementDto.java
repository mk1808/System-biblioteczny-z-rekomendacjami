package com.library.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.library.enums.UserListType;

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
public class UserListElementDto {
	private UUID id;
	private BookDto book;
	private UUID bookId;

	private AppUserDto user;
	private UUID userId;
	
	private UserListType type;
	private LocalDateTime deleteDate;
}
