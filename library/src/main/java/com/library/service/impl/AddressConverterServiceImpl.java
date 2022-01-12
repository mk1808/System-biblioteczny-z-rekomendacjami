package com.library.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.AddressDto;
import com.library.model.Address;
import com.library.service.AddressConverterService;

@Service
public class AddressConverterServiceImpl implements AddressConverterService{

	@Override
	public AddressDto toDto(Address model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address toModel(AddressDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AddressDto> toDtoList(List<Address> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> toModelList(List<AddressDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<AddressDto> toDtoPage(Page<Address> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Address> toModelPage(Page<AddressDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
