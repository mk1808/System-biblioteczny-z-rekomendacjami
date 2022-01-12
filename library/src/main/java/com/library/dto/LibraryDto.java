package com.library.dto;

import java.util.UUID;

import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.library.enums.BookCopyStatus;
import com.library.model.Address;
import com.library.model.Terms;

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
public class LibraryDto {
	private UUID id;
	private String name;
	private String phoneNo;
	private String mail;
	private String description;
	private UUID addressId;
	private AddressDto address;
	private UUID termsId;
	private String terms;
}
