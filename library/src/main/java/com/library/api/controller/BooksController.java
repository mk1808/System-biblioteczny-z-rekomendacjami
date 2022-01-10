package com.library.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.library.api.resource.BooksResource;
import com.library.dto.BookAvailabilityDto;
import com.library.dto.BookCopyDto;
import com.library.dto.BookDto;
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
	
    @Autowired
    public BooksController(BookConverterService bookConverter, BookCopyConverterService bookCopyConverter,
    		OpinionConverterService opinionConverter, BookService bookService, 
    		ChangeProposalConverterService changeProposalConverter, UserListElementConverterService userListElementConverter){
        this.bookConverter = bookConverter;
        this.bookCopyConverter = bookCopyConverter;
		this.opinionConverter = opinionConverter;
		this.changeProposalConverter = changeProposalConverter;
		this.userListElementConverter = userListElementConverter;
		this.bookService = bookService;
    }
	
    
	@Override
	public ResponseEntity<Response<BookDto>> create(BookDto bookDto) {
		Book book = bookConverter.toModel(bookDto);
		Response<BookDto> response = createSuccessResponse(bookConverter.toDto(bookService.create(book)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<BookDto>> getById(Long id) {
		Response<BookDto> response = createSuccessResponse(bookConverter.toDto(bookService.get(id)));
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<Response<BookDto>> getBookByBookCopy(Long bookCopyId) {
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
		List<Book> books = new ArrayList<>();
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
	public ResponseEntity<Response<Page<BookDto>>> getFiltered(Long pageNo, Long size) {
		
		/*Response<Page<BookDto>> response = Response.<Page<BookDto>> builder()
				.content(bookConverter.toDtoPage(books))
				.status(HttpStatus.OK)
				.build();
		return ResponseEntity.ok(response);*/
		return null;
	}

	@Override
	public ResponseEntity<Response<String>> createOpinion(OpinionDto opinionDto) {
		Opinion opinion = opinionConverter.toModel(opinionDto);
		bookService.createOpinion(opinion);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<Response<List<OpinionDto>>> getOpinionsByBookId(Long id) {
		Response<List<OpinionDto>> response = createSuccessResponse(opinionConverter.toDtoList(bookService.getOpinionsByBookId(id)));
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<Response<OpinionDto>> getOpinionsByBookIdAndUserId(Long userId, Long bookId) {
		Response<OpinionDto> response = createSuccessResponse(opinionConverter.toDto(bookService.getOpinionByBookIdAndUserId(userId, bookId)));
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<Response<List<OpinionDto>>> getOpinionsByUser(Long userId) {
		Response<List<OpinionDto>> response = createSuccessResponse(opinionConverter.toDtoList(bookService.getOpinionsByUser(userId)));
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
	public ResponseEntity<Response<List<BookCopyDto>>> getBookCopiesByBookId(Long id) {
		Response<List<BookCopyDto>> response = createSuccessResponse(bookCopyConverter.toDtoList(bookService.getBookCopiesByBookId(id)));
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
	public ResponseEntity<Response<List<ChangeProposalDto>>> getChangeProposal(Long bookId) {
		Response<List<ChangeProposalDto>> response = createSuccessResponse(changeProposalConverter.toDtoList(bookService.getChangeProposalsByBookId(bookId)));
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<Response<List<ChangeProposalDto>>> getNewChangeProposals() {
		Response<List<ChangeProposalDto>> response = createSuccessResponse(changeProposalConverter.toDtoList(bookService.getNewChangeProposals()));
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
	public ResponseEntity<Response<List<UserListElementDto>>> getUserListElementByUserAndType(Long userId,
			String type) {
		Response<List<UserListElementDto>> response = createSuccessResponse(userListElementConverter.toDtoList(
				bookService.getUserListElementByUserAndType(userId, type)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<String>> deleteUserListElement(Long elementId) {
		bookService.deleteUserListElement(elementId);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<Response<BookAvailabilityDto>> getAvailabilityByBookId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<CanBorrowBookDto>> canBorrowBookCopy(Long bookCopyId, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<List<OpinionDto>>> createQRFile(List<Long> bookCopiesIds) {
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
