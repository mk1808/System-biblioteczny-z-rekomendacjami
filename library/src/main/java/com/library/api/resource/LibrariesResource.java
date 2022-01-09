package com.library.api.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.LibraryDto;
import com.library.dto.TermsDto;
import com.library.response.Response;

@RestController
@RequestMapping("/api/libraries")
public interface LibrariesResource {
	
	@GetMapping("/contact")
	ResponseEntity<Response<List<LibraryDto>>> getContact();
	
	@GetMapping("/terms")
	ResponseEntity<Response<List<TermsDto>>> getTerms();
}
