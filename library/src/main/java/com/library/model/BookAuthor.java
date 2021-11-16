package com.library.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

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
public class BookAuthor extends BaseEntity {

	    @ManyToOne(fetch = FetchType.LAZY)
	    Book book;

	    @ManyToOne(fetch = FetchType.LAZY)
	    Author author;

	    Long orderNumber;
}
