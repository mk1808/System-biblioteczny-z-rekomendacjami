package com.library.dto;

import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.library.enums.BookCopyStatus;
import com.library.model.Address;
import com.library.model.Terms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryDto {
	private Long id;
	private String name;
	private String phoneNo;
	private String mail;
	private String description;
	private Long addressId;
	private AddressDto address;
	private Long termsId;
	private String terms;
}
