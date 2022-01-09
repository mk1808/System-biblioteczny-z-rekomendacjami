package com.library.api.resource;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.BookAvailabilityDto;
import com.library.dto.BookCopyDto;
import com.library.dto.BookDto;
import com.library.response.Response;

@RestController
@RequestMapping("/api/books")
public interface BooksResource {

	@GetMapping("/newest")
	ResponseEntity<Response<List<BookDto>>> getNewest(@RequestParam Long number);
	
	@GetMapping("/filtered")
	ResponseEntity<Response<Page<BookDto>>> getFiltered(@RequestParam Long pageNo, @RequestParam Long size);

	@GetMapping("/popular")
	ResponseEntity<Response<List<BookDto>>> getPopular(@RequestParam Long number);
	
	@GetMapping("/{id}")
	ResponseEntity<Response<BookDto>> getById(@PathVariable Long id);
	
	@GetMapping("/{id}/bookCopies")
	ResponseEntity<Response<List<BookCopyDto>>> getBookCopiesByBookId(@PathVariable Long id);
	
	@GetMapping("/{id}/availability")
	ResponseEntity<Response<BookAvailabilityDto>> getAvailabilityByBookId(@PathVariable Long id);
	
	
	//@PreAuthorize("hasRole('USER')")
	//@PreAuthorize("hasRole('ADMIN')")
}
