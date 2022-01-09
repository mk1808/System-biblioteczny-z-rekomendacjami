package com.library.api.controller;

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

}
