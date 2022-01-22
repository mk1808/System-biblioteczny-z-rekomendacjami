package com.library.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.library.api.resource.ReservationsResource;
import com.library.dto.ReservationDto;
import com.library.model.Reservation;
import com.library.response.Response;
import com.library.service.ReservationConverterService;
import com.library.service.ReservationService;

@Controller
public class ReservationsController extends BaseController implements ReservationsResource{

	private final ReservationConverterService reservationConverter;
	private final ReservationService reservationService;
	
	@Autowired
	public ReservationsController(ReservationConverterService reservationConverter,
			ReservationService reservationService) {
		super();
		this.reservationConverter = reservationConverter;
		this.reservationService = reservationService;
	}
	
	@Override
	public ResponseEntity<Response<String>> create(ReservationDto reservationDto) {
		Reservation reservation = reservationConverter.toModel(reservationDto);
		reservationService.create(reservation);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<List<ReservationDto>>> getByUserId(UUID userId) {
		Response<List<ReservationDto>> response = createSuccessResponse(reservationConverter.toDtoList(
				reservationService.getByUserId(userId)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<String>> cancel(UUID id) {
		reservationService.cancel(id);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}

}
