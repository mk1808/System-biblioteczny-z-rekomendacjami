package com.library.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

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
@Entity
public class UserListElement extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	private Book book;

	@ManyToOne(fetch = FetchType.LAZY)
	private AppUser user;
	
	private UserListType type;
	private LocalDateTime deleteDate;


}
