package com.library.api.resource;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.BookAvailabilityDto;
import com.library.dto.BookCopyDto;
import com.library.dto.BookDto;
import com.library.dto.BookFIlterDto;
import com.library.dto.BookFileColumnDto;
import com.library.dto.CanBorrowBookDto;
import com.library.dto.ChangeProposalDto;
import com.library.dto.CreateBookCopiesDto;
import com.library.dto.FileDto;
import com.library.dto.ImportFileResultDto;
import com.library.dto.OpinionDto;
import com.library.dto.UserListElementDto;
import com.library.response.Response;

@RestController
@RequestMapping("/api/books")
public interface BooksResource {

	@GetMapping("/newest")
	ResponseEntity<Response<List<BookDto>>> getNewest(@RequestParam Long number);
	
	@PostMapping("/filtered")
	ResponseEntity<Response<List<BookDto>>> getFiltered(@RequestParam Long pageNo, @RequestParam Long size,  @RequestBody BookFIlterDto filter);

	@GetMapping("/popular")
	ResponseEntity<Response<List<BookDto>>> getPopular(@RequestParam Long number);
	
	@GetMapping("/{id}")
	ResponseEntity<Response<BookDto>> getById(@PathVariable UUID id);
	
	@GetMapping("/{id}/bookCopies")
	ResponseEntity<Response<List<BookCopyDto>>> getBookCopiesByBookId(@PathVariable UUID id);
	
	@GetMapping("/{id}/availability")
	ResponseEntity<Response<BookAvailabilityDto>> getAvailabilityByBookId(@PathVariable UUID id);
	
	@GetMapping("/{id}/opinions")
	ResponseEntity<Response<List<OpinionDto>>> getOpinionsByBookId(@PathVariable UUID id);
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{bookId}/user/{userId}/opinions")
	ResponseEntity<Response<OpinionDto>> getOpinionsByBookIdAndUserId(@PathVariable UUID userId, @PathVariable UUID bookId);
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/opinions")
	ResponseEntity<Response<String>> createOpinion(@RequestBody OpinionDto opinion);
	
	@PreAuthorize("hasRole('USER')")
	@PutMapping("/opinions")
	ResponseEntity<Response<String>> updateOpinion(@RequestBody OpinionDto opinion);
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/changeProposal")
	ResponseEntity<Response<String>> createChangeProposal(@RequestBody List<ChangeProposalDto> changeProposals);
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/userList")
	ResponseEntity<Response<String>> createUserListElement(@RequestBody UserListElementDto userListElement);
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/userList/user/{userId}")
	ResponseEntity<Response<List<UserListElementDto>>> getUserListElementByUserAndType(@PathVariable UUID userId, @RequestParam String type);
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user/{userId}/opinions")
	ResponseEntity<Response<List<OpinionDto>>> getOpinionsByUser(@PathVariable UUID userId);
	
	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/userList/{elementId}")
	ResponseEntity<Response<String>> deleteUserListElement(@PathVariable UUID elementId);

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	ResponseEntity<Response<BookDto>> create(@RequestBody BookDto book);
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/bookCopies")
	ResponseEntity<Response<List<BookCopyDto>>> createBookCopies(@RequestBody CreateBookCopiesDto createBookCopies);
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/bookCopies/file")
	ResponseEntity<Response<List<OpinionDto>>> createQRFile(@RequestParam List<UUID> bookCopiesIds);
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}/changeProposal")
	ResponseEntity<Response<List<ChangeProposalDto>>> getChangeProposal(@PathVariable UUID bookId);
	
	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping("/changeProposal")
	ResponseEntity<Response<String>> updateChangeProposal(@RequestBody ChangeProposalDto changeProposal);
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	ResponseEntity<Response<String>> update(@RequestBody BookDto book);
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/changeProposal/new")
	ResponseEntity<Response<List<ChangeProposalDto>>> getNewChangeProposals();
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/bookCopies/{bookCopyId}/users/{userId}/canBorrow")
	ResponseEntity<Response<CanBorrowBookDto>> canBorrowBookCopy(@PathVariable UUID bookCopyId, @PathVariable UUID userId);
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/bookCopies/{bookCopyId}")
	ResponseEntity<Response<BookDto>> getBookByBookCopy(@PathVariable UUID bookCopyId);
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/import")
	ResponseEntity<Response<List<BookFileColumnDto>>> getColumnsFromImport(@RequestBody FileDto file);
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/import/mapping")
	ResponseEntity<Response<List<BookFileColumnDto>>> createColumnsMapping(@RequestBody List<BookFileColumnDto> columns);
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/import/result")
	ResponseEntity<Response<ImportFileResultDto>> importBooks(@RequestBody List<BookFileColumnDto> columns);
	
	
	//@PreAuthorize("hasRole('USER')")
	//@PreAuthorize("hasRole('ADMIN')")
}
