package com.library.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.library.api.resource.ReservationsResource;
import com.library.dto.ReservationDto;
import com.library.response.Response;

@Controller
public class ReservationsController implements ReservationsResource{

	@Override
	public ResponseEntity<Response<String>> create(ReservationDto reservation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<List<ReservationDto>>> getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<String>> cancel(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
