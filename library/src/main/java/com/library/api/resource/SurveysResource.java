package com.library.api.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.BookDto;
import com.library.response.Response;

@RestController
@RequestMapping("/api/surveys")
public interface SurveysResource {
	
	/*@GetMapping("/newest")
	ResponseEntity<Response<List<BookDto>>> getNewest(@RequestParam Long number);*/
}
