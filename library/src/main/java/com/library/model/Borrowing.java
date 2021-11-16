package com.library.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

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
public class Borrowing extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	private BookCopy bookCopy;

	@ManyToOne(fetch = FetchType.LAZY)
	private AppUser user;
	
	private LocalDateTime borrowDate;
	private LocalDateTime returnDate;
	private LocalDateTime expectedReturnDate;
	private Long numberOfProlongings;

}