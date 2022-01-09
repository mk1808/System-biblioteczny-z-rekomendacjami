package com.library.api.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.BookDto;
import com.library.dto.ReservationDto;
import com.library.response.Response;

@RestController
@RequestMapping("/api/reservations")
public interface ReservationsResource {
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping
	ResponseEntity<Response<String>> create(@RequestBody ReservationDto reservation);
}
