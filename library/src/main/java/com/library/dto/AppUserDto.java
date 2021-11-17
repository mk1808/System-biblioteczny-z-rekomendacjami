package com.library.dto;

import java.time.LocalDateTime;

import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.library.enums.Role;
import com.library.model.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserDto {
	
	private Long id;
	private String name;
	private String surname;
	private String phoneNo;
	private Long addressId;
	private AddressDto address;
	private String mail;
	private String password;
	private Role role;
	private String photo;
	private LocalDateTime creationDate;
	private LocalDateTime dezactivationDate; 

}
