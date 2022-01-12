package com.library.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

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
public class AppUserDto {
	
	private UUID id;
	private String name;
	private String surname;
	private String phoneNo;
	private Long addressId;
	private AddressDto address;
	private String mail;
	private String password;
	private Set<UUID> roleIds;
	private String photo;
	private LocalDateTime creationDate;
	private LocalDateTime dezactivationDate; 

}
