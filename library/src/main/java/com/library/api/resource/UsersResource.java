package com.library.api.resource;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.AppUserDto;
import com.library.dto.BookDto;
import com.library.dto.UserFilterDto;
import com.library.response.Response;

@RestController
@RequestMapping("/api/users")
public interface UsersResource {
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/{id}")
	ResponseEntity<Response<AppUserDto>> getById(@PathVariable UUID id);
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PutMapping
	ResponseEntity<Response<AppUserDto>> update(@RequestBody AppUserDto user);
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/filtered")
	ResponseEntity<Response<Page<AppUserDto>>> getFiltered(@RequestBody UserFilterDto userFIlter);
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	ResponseEntity<Response<String>> create(@RequestBody AppUserDto user);
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/admin")
	ResponseEntity<Response<String>> updateByAdmin(@RequestBody AppUserDto user);
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}/deactivate")
	ResponseEntity<Response<String>> deactivate(@PathVariable UUID id);
}
