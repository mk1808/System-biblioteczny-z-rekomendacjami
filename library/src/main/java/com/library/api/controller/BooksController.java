package com.library.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.library.api.resource.BooksResource;
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
import com.library.model.Book;
import com.library.model.ChangeProposal;
import com.library.model.Opinion;
import com.library.model.UserListElement;
import com.library.response.Response;
import com.library.service.BookConverterService;
import com.library.service.BookCopyConverterService;
import com.library.service.BookService;
import com.library.service.CanBorrowBookConverterService;
import com.library.service.ChangeProposalConverterService;
import com.library.service.OpinionConverterService;
import com.library.service.UserListElementConverterService;

@Controller
public class BooksController extends BaseController implements BooksResource {

	private final BookConverterService bookConverter;
	private final BookCopyConverterService bookCopyConverter;
	private final OpinionConverterService opinionConverter;
	private final ChangeProposalConverterService changeProposalConverter;
	private final UserListElementConverterService userListElementConverter;
	private final BookService bookService;
	private final CanBorrowBookConverterService canBorrowBookConverter;

	@Autowired
	public BooksController(BookConverterService bookConverter, BookCopyConverterService bookCopyConverter,
			OpinionConverterService opinionConverter, BookService bookService,
			ChangeProposalConverterService changeProposalConverter,
			UserListElementConverterService userListElementConverter,
			CanBorrowBookConverterService canBorrowBookConverter) {
		this.bookConverter = bookConverter;
		this.bookCopyConverter = bookCopyConverter;
		this.opinionConverter = opinionConverter;
		this.changeProposalConverter = changeProposalConverter;
		this.userListElementConverter = userListElementConverter;
		this.bookService = bookService;
		this.canBorrowBookConverter = canBorrowBookConverter;
	}

	@Override
	public ResponseEntity<Response<BookDto>> create(BookDto bookDto) {
		Book book = bookConverter.toModel(bookDto);
		Response<BookDto> response = createSuccessResponse(bookConverter.toDto(bookService.create(book)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<BookDto>> getById(UUID id) {
		Response<BookDto> response = createSuccessResponse(bookConverter.toDto(bookService.get(id)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<BookDto>> getBookByBookCopy(UUID bookCopyId) {
		Response<BookDto> response = createSuccessResponse(bookConverter.toDto(bookService.getByBookCopy(bookCopyId)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<String>> update(BookDto bookDto) {
		Book book = bookConverter.toModel(bookDto);
		bookService.update(book);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<List<BookDto>>> getNewest(Long number) {
		List<Book> books = bookService.getNewest(number);
		Response<List<BookDto>> response = createSuccessResponse(bookConverter.toDtoList(books));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<List<BookDto>>> getPopular(Long number) {
		List<Book> books = new ArrayList<>();
		Response<List<BookDto>> response = createSuccessResponse(bookConverter.toDtoList(books));
		return ResponseEntity.ok(response);
	}

	@Override
	// page
	public ResponseEntity<Response<List<BookDto>>> getFiltered(Long pageNo, Long size, BookFIlterDto filter) {

		Response<List<BookDto>> response = createSuccessResponse(
				bookConverter.toDtoList(bookService.getFiltered(filter)));
		return ResponseEntity.ok(response);

	}

	@Override
	public ResponseEntity<Response<String>> createOpinion(OpinionDto opinionDto) {
		Opinion opinion = opinionConverter.toModel(opinionDto);
		bookService.createOpinion(opinion);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<List<OpinionDto>>> getOpinionsByBookId(UUID id) {
		Response<List<OpinionDto>> response = createSuccessResponse(
				opinionConverter.toDtoList(bookService.getOpinionsByBookId(id)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<OpinionDto>> getOpinionsByBookIdAndUserId(UUID userId, UUID bookId) {
		Response<OpinionDto> response = createSuccessResponse(
				opinionConverter.toDto(bookService.getOpinionByBookIdAndUserId(userId, bookId)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<List<OpinionDto>>> getOpinionsByUser(UUID userId) {
		Response<List<OpinionDto>> response = createSuccessResponse(
				opinionConverter.toDtoList(bookService.getOpinionsByUser(userId)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<String>> updateOpinion(OpinionDto opinionDto) {
		Opinion opinion = opinionConverter.toModel(opinionDto);
		bookService.updateOpinion(opinion);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<List<BookCopyDto>>> createBookCopies(CreateBookCopiesDto createBookCopies) {
		return null;
	}

	@Override
	public ResponseEntity<Response<List<BookCopyDto>>> getBookCopiesByBookId(UUID id) {
		Response<List<BookCopyDto>> response = createSuccessResponse(
				bookCopyConverter.toDtoList(bookService.getBookCopiesByBookId(id)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<String>> createChangeProposal(List<ChangeProposalDto> changeProposalsDto) {
		List<ChangeProposal> changeProposals = changeProposalConverter.toModelList(changeProposalsDto);
		bookService.createChangeProposals(changeProposals);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<List<ChangeProposalDto>>> getChangeProposal(UUID bookId) {
		Response<List<ChangeProposalDto>> response = createSuccessResponse(
				changeProposalConverter.toDtoList(bookService.getChangeProposalsByBookId(bookId)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<List<ChangeProposalDto>>> getNewChangeProposals() {
		Response<List<ChangeProposalDto>> response = createSuccessResponse(
				changeProposalConverter.toDtoList(bookService.getNewChangeProposals()));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<String>> updateChangeProposal(ChangeProposalDto changeProposalDto) {
		ChangeProposal changeProposal = changeProposalConverter.toModel(changeProposalDto);
		bookService.updateChangePorposal(changeProposal);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<String>> createUserListElement(UserListElementDto userListElementDto) {
		UserListElement userListElement = userListElementConverter.toModel(userListElementDto);
		bookService.createUserListElement(userListElement);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<List<UserListElementDto>>> getUserListElementByUserAndType(UUID userId,
			String type) {
		Response<List<UserListElementDto>> response = createSuccessResponse(
				userListElementConverter.toDtoList(bookService.getUserListElementByUserAndType(userId, type)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<String>> deleteUserListElement(UUID elementId) {
		bookService.deleteUserListElement(elementId);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<BookAvailabilityDto>> getAvailabilityByBookId(UUID id) {
		Response<BookAvailabilityDto> response = createSuccessResponse(bookService.getAvailabilityByBookId(id));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<CanBorrowBookDto>> canBorrowBookCopy(UUID bookCopyId, UUID userId) {
		Response<CanBorrowBookDto> response = createSuccessResponse(
				canBorrowBookConverter.toDto(bookService.canBorrow(bookCopyId, userId)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<List<OpinionDto>>> createQRFile(List<UUID> bookCopiesIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<List<BookFileColumnDto>>> getColumnsFromImport(FileDto file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<List<BookFileColumnDto>>> createColumnsMapping(List<BookFileColumnDto> columns) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<ImportFileResultDto>> importBooks(List<BookFileColumnDto> columns) {
		// TODO Auto-generated method stub
		return null;
	}

}
