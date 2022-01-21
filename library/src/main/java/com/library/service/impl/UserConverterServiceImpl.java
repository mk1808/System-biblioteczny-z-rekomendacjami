package com.library.service.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.AppUserDto;
import com.library.dto.AuthorDto;
import com.library.dto.BookCopyDto;
import com.library.model.Address;
import com.library.model.AppUser;
import com.library.model.Author;
import com.library.model.Book;
import com.library.model.BookCopy;
import com.library.model.Role;
import com.library.service.AddressConverterService;
import com.library.service.BookConverterService;
import com.library.service.UserConverterService;

@Service
public class UserConverterServiceImpl implements UserConverterService {

private final AddressConverterService addressConverter;
	
	@Autowired
	public UserConverterServiceImpl(AddressConverterService addressConverter) {
		super();
		this.addressConverter = addressConverter;
	}
	
	@Override
	public AppUserDto toDto(AppUser model) {
		AppUserDto dto = AppUserDto.builder()
				.id(model.getId())
				.name(model.getName())
				.surname(model.getSurname())
				.phoneNo(model.getPhoneNo())
				.address(addressConverter.toDto(model.getAddress()))
				.mail(model.getMail())
				.password(model.getPassword())
				.photo(model.getPhoto())
				.creationDate(model.getCreationDate())		
				.dezactivationDate(model.getDezactivationDate())
				.roleIds(model.getRoles().stream().map(role->role.getId()).collect(Collectors.toSet()))
				.build();
		return dto;
	}

	@Override
	public AppUser toModel(AppUserDto dto) {
		AppUser model = AppUser.builder()
				.id(dto.getId())
				.name(dto.getName())
				.surname(dto.getSurname())
				.phoneNo(dto.getPhoneNo())
				.address(Address.builder().id(dto.getAddress().getId()).build())
				.mail(dto.getMail())
				.password(dto.getPassword())
				.photo(dto.getPhoto())
				.creationDate(dto.getCreationDate())		
				.dezactivationDate(dto.getDezactivationDate())
				.roles(createDtos(dto.getRoleIds(), this::createRoleDto))
				.build();
		return model;
	}

	@Override
	public List<AppUserDto> toDtoList(List<AppUser> models) {
		return models.stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public List<AppUser> toModelList(List<AppUserDto> dtos) {
		return dtos.stream().map(this::toModel).collect(Collectors.toList());
	}

	@Override
	public Page<AppUserDto> toDtoPage(Page<AppUser> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<AppUser> toModelPage(Page<AppUserDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private <M,D> Set<M> createDtos(Set<UUID> dtos, Function<UUID, M>function) {
		return dtos.stream().map(function).collect(Collectors.toSet());
	}
	
	private Role createRoleDto(UUID id) {
		return Role.builder().id(id).build();
	}
	

}
