package com.library.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.library.api.resource.LibrariesResource;
import com.library.dto.LibraryDto;
import com.library.dto.TermsDto;
import com.library.response.Response;

@Controller
public class LibrariesController implements LibrariesResource {

	@Override
	public ResponseEntity<Response<List<LibraryDto>>> getContact() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<List<TermsDto>>> getTerms() {
		// TODO Auto-generated method stub
		return null;
	}

}
