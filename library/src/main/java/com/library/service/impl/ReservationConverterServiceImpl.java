package com.library.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.BookCopyDto;
import com.library.dto.ReservationDto;
import com.library.model.Reservation;
import com.library.service.ReservationConverterService;

@Service
public class ReservationConverterServiceImpl implements ReservationConverterService {

	@Override
	public ReservationDto toDto(Reservation model) {
		BookCopyDto dto = BookCopyDto.builder()
				.id(Long.valueOf(model.getId().toString()))
				.name(model.getName())
				.surname(model.getSurname())
				.description(model.getDescription())
				.build();
		return dto;
	}

	@Override
	public Reservation toModel(ReservationDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReservationDto> toDtoList(List<Reservation> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> toModelList(List<ReservationDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ReservationDto> toDtoPage(Page<Reservation> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Reservation> toModelPage(Page<ReservationDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
