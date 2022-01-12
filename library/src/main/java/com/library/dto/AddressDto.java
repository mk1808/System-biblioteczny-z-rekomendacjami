package com.library.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.library.enums.Role;

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
public class AddressDto {
	private UUID id;
	private String street;
	private String houseNo;
	private String flatNo;
	private String postcode;
	private String city;
	private String district;
	private String country;
}
