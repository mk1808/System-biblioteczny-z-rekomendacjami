package com.library.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

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
public class Library extends BaseEntity {
	
	private String name;
	private String phoneNo;
	private String mail;
	private String description;
	@OneToOne(fetch = FetchType.LAZY)
	private Address address;
	@OneToOne(fetch = FetchType.LAZY)
	private Terms terms;
	
}