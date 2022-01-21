package com.library.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.AddressDto;
import com.library.model.Address;
import com.library.service.AddressConverterService;

@Service
public class AddressConverterServiceImpl implements AddressConverterService{

	@Override
	public AddressDto toDto(Address model) {
		AddressDto dto = AddressDto.builder()
				.id(model.getId())
				.street(model.getStreet())
				.houseNo(model.getHouseNo())
				.flatNo(model.getFlatNo())
				.postcode(model.getPostcode())
				.city(model.getCity())
				.district(model.getDistrict())
				.country(model.getCountry())
				.build();
		return dto;
	}

	@Override
	public Address toModel(AddressDto dto) {
		Address model = Address.builder()
				.id(dto.getId())
				.street(dto.getStreet())
				.houseNo(dto.getHouseNo())
				.flatNo(dto.getFlatNo())
				.postcode(dto.getPostcode())
				.city(dto.getCity())
				.district(dto.getDistrict())
				.country(dto.getCountry())
				.build();
		return model;
	}

	@Override
	public List<AddressDto> toDtoList(List<Address> models) {
		return models.stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public List<Address> toModelList(List<AddressDto> dtos) {
		return dtos.stream().map(this::toModel).collect(Collectors.toList());
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
