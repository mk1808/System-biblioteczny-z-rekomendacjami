package com.library.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
	
	
}
