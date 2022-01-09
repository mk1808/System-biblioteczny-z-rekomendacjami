package com.library.api.resource;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.BookDto;
import com.library.dto.RecommendationDto;
import com.library.response.Response;

@RestController
@RequestMapping("/api/recommendations")
public interface RecommendationsResource {
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user/{userId}")
	ResponseEntity<Response<Page<RecommendationDto>>> getByUserId(@PathVariable Long userId, @RequestParam Long pageNo, @RequestParam Long pageSize);

	@PreAuthorize("hasRole('USER')")
	@PatchMapping
	ResponseEntity<Response<String>> updateInfo(@RequestBody RecommendationDto recommendation);

}
