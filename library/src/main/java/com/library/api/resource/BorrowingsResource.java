package com.library.api.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	ResponseEntity<Response<List<BorrowingDto>>> getByUserId(@PathVariable Long userId);
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/user/{userId}/past")
	ResponseEntity<Response<List<BorrowingDto>>> getPastByUserId(@PathVariable Long userId);
	
	@PreAuthorize("hasRole('USER')")
	@PatchMapping("/{id}/prolong")
	ResponseEntity<Response<String>> prolong(@PathVariable Long id);
	
	
}
