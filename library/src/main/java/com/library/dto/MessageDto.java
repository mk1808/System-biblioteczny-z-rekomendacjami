package com.library.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.library.enums.MessageType;
import com.library.model.AppUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
	
	private Long id;
	private AppUser user;
	private LocalDateTime date;
	private MessageType type;
	private List<Long> ids;
}
