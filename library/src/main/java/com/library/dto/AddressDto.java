package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {
	private Long id;
	private String street;
	private String houseNo;
	private String flatNo;
	private String postcode;
	private String city;
	private String district;
	private String country;
}
