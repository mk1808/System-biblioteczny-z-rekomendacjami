package com.library.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Book extends BaseEntity{

	@NaturalId
	@Column(nullable = false, unique = true)
	private String ISBN;
	
	private String title;
	
	private String publicationYear;
	
	private String originalTitle;
	
	private String descrpition;
	
	@Column(length = 1000)
	private String photo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Publisher publisher;
	
	@OneToMany
	@JoinColumn(name = "book_id")
	private List<BookAuthor> bookAuthors;
	
	@Transient
	private List<Author> authors;
	
	@Transient
	private List<Genre> genres;
	
	@Transient
	private List<Series> series;
	
	@Transient
	private List<KeyWord> keyWords;
	
	
}
