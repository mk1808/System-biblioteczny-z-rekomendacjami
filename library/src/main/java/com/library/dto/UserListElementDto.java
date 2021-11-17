package com.library.dto;

import java.time.LocalDateTime;

import com.library.enums.UserListType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserListElementDto {
	private Long id;
	private BookDto book;
	private Long bookId;

	private AppUserDto user;
	private Long userId;
	
	private UserListType type;
	private LocalDateTime deleteDate;
}
