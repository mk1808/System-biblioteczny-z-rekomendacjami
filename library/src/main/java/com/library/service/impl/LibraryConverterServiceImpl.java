package com.library.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.BookCopyDto;
import com.library.dto.LibraryDto;
import com.library.model.Address;
import com.library.model.BookCopy;
import com.library.model.Library;
import com.library.model.Terms;
import com.library.service.AddressConverterService;
import com.library.service.LibraryConverterService;

@Service
public class LibraryConverterServiceImpl implements LibraryConverterService {

	private final AddressConverterService addressConverter;
	
	@Autowired
	public LibraryConverterServiceImpl(AddressConverterService addressConverter) {
		super();
		this.addressConverter = addressConverter;
	}

	@Override
	public LibraryDto toDto(Library model) {
		LibraryDto dto = LibraryDto.builder()
				.id(model.getId())
				.name(model.getName())
				.phoneNo(model.getPhoneNo())
				.mail(model.getMail())
				.description(model.getDescription())
				.addressId(model.getAddress().getId())
				.address(addressConverter.toDto(model.getAddress()))
				.termsId(model.getTerms().getId())
				.terms(model.getTerms().getContent())
				.build();
		return dto;
	}

	@Override
	public Library toModel(LibraryDto dto) {
		Library model = Library.builder()
				.id(dto.getId())
				.name(dto.getName())
				.phoneNo(dto.getPhoneNo())
				.mail(dto.getMail())
				.description(dto.getDescription())
				.address(Address.builder().id(dto.getAddressId()).build())
				.terms(Terms.builder().id(dto.getTermsId()).build())
				.build();
		return model;
	}

	@Override
	public List<LibraryDto> toDtoList(List<Library> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Library> toModelList(List<LibraryDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<LibraryDto> toDtoPage(Page<Library> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Library> toModelPage(Page<LibraryDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
