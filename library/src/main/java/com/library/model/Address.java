package com.library.model;

import javax.persistence.Entity;

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
public class Address extends BaseEntity {
	
	private String street;
	private String houseNo;
	private String flatNo;
	private String postcode;
	private String city;
	private String district;
	private String country;
	
	
}