package com.library.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.ReservationDto;
import com.library.model.AppUser;
import com.library.model.Book;
import com.library.model.Reservation;
import com.library.service.BookConverterService;
import com.library.service.ReservationConverterService;
import com.library.service.UserConverterService;

@Service
public class ReservationConverterServiceImpl implements ReservationConverterService {

	private final BookConverterService bookConverter;
	private final UserConverterService userConverter;
		
		@Autowired
		public ReservationConverterServiceImpl(BookConverterService bookConverter, 
				UserConverterService userConverter) {
			this.bookConverter = bookConverter;
			this.userConverter = userConverter;
		}
		
	@Override
	public ReservationDto toDto(Reservation model) {
		ReservationDto dto = ReservationDto.builder()
				.id(model.getId())
				.book(bookConverter.toDto(model.getBook()))
				.bookId(model.getBook().getId())
				.user(userConverter.toDto(model.getUser()))
				.userId(model.getUser().getId())
				
				.reservationDate(model.getReservationDate())
				.availabilityStartDate(model.getAvailabilityStartDate())
				.wasBorrowed(model.getWasBorrowed())
				.build();
		return dto;
	}

	@Override
	public Reservation toModel(ReservationDto dto) {
		Reservation model = Reservation.builder()
				.id(dto.getId())
				.book(Book.builder().id(dto.getBookId()).build())
				.user(AppUser.builder().id(dto.getUserId()).build())
				.reservationDate(dto.getReservationDate())
				.availabilityStartDate(dto.getAvailabilityStartDate())
				.wasBorrowed(dto.getWasBorrowed())
				.build();
		return model;
	}

	@Override
	public List<ReservationDto> toDtoList(List<Reservation> models) {
		return models.stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public List<Reservation> toModelList(List<ReservationDto> dtos) {
		return dtos.stream().map(this::toModel).collect(Collectors.toList());
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
