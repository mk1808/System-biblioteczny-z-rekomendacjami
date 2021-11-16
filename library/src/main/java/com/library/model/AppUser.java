package com.library.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NaturalId;

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
@Entity
public class AppUser extends BaseEntity {
	
	
	private String name;
	private String surname;
	private String phoneNo;
	@OneToOne(fetch = FetchType.LAZY)
	private Address address;
	private String mail;
	private String password;
	private Role role;
	private String photo;
	private LocalDateTime creationDate;
	private LocalDateTime dezactivationDate; 
	
	
	
}