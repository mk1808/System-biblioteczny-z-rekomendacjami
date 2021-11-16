package com.library.model;

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
public class BookGenre extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	Book book;

	@ManyToOne(fetch = FetchType.LAZY)
	Genre genre;

}