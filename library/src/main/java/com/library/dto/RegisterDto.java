package com.library.dto;

import com.library.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDto {
	private String name;
	private String surname;
	private String phoneNo;
	private AddressDto address;
	private String mail;
	private String password;
	private String passwordRepeat;
	private Role role;
	private String photo;
	
}
