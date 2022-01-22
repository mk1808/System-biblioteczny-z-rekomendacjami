package com.library.api.resource;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.BookDto;
import com.library.dto.BorrowingDto;
import com.library.response.Response;

@RestController
@RequestMapping("/api/borrowings")
public interface BorrowingsResource {
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/user/{userId}")
	ResponseEntity<Response<List<BorrowingDto>>> getByUserId(@PathVariable UUID userId);
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/user/{userId}/past")
	ResponseEntity<Response<List<BorrowingDto>>> getPastByUserId(@PathVariable UUID userId);
	
	@PreAuthorize("hasRole('USER')")
	@PatchMapping("/{id}/prolong")
	ResponseEntity<Response<String>> prolong(@PathVariable UUID id);
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	ResponseEntity<Response<String>> create(@RequestBody List<BorrowingDto> borrowings);
	
	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping("/return")
	ResponseEntity<Response<String>> returnBorrowing(@RequestParam List<UUID> bookCopiesIds);
	
	
	
}
