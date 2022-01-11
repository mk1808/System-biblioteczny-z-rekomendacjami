package com.library.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.library.api.resource.LibrariesResource;
import com.library.dto.LibraryDto;
import com.library.dto.TermsDto;
import com.library.response.Response;
import com.library.service.LibraryConverterService;
import com.library.service.LibraryService;
import com.library.service.TermsConverterService;

@Controller
public class LibrariesController extends BaseController implements LibrariesResource {
	
	private final LibraryConverterService libraryConverter;
	private final TermsConverterService termsConverter;
	private final LibraryService libraryService;
	
	@Autowired
	public LibrariesController(LibraryConverterService libraryConverter, TermsConverterService termsConverter, 
			LibraryService libraryService) {
		this.libraryConverter = libraryConverter;
		this.libraryService = libraryService;
		this.termsConverter = termsConverter;
	}

	@Override
	public ResponseEntity<Response<List<LibraryDto>>> getContact() {
		Response<List<LibraryDto>> response = createSuccessResponse(libraryConverter.toDtoList(libraryService.getContact()));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<List<TermsDto>>> getTerms() {
		Response<List<TermsDto>> response = createSuccessResponse(termsConverter.toDtoList(libraryService.getTerms()));
		return ResponseEntity.ok(response);
	}

}
