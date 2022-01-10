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
import com.library.model.Opinion;
import com.library.response.Response;
import com.library.service.BookConverterService;
import com.library.service.BookService;
import com.library.service.OpinionConverterService;

@Controller
public class BooksController extends BaseController implements BooksResource {

	private final BookConverterService bookConverter;
	private final OpinionConverterService opinionConverter;
	private final BookService bookService;
	
    @Autowired
    public BooksController(BookConverterService bookConverter, OpinionConverterService opinionConverter, BookService bookService){
        this.bookConverter = bookConverter;
		this.opinionConverter = opinionConverter;
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
	public ResponseEntity<Response<List<BookCopyDto>>> getBookCopiesByBookId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<BookAvailabilityDto>> getAvailabilityByBookId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ResponseEntity<Response<String>> createChangeProposal(List<ChangeProposalDto> changeProposals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<String>> createUserListElement(UserListElementDto userListElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<List<UserListElementDto>>> getUserListElementByUserAndType(Long userId,
			String type) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ResponseEntity<Response<String>> deleteUserListElement(Long elementId) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ResponseEntity<Response<List<BookCopyDto>>> createBookCopies(CreateBookCopiesDto createBookCopies) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<List<OpinionDto>>> createQRFile(List<Long> bookCopiesIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<List<ChangeProposalDto>>> createChangeProposal(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<String>> updateChangeProposal(ChangeProposalDto changeProposal) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ResponseEntity<Response<List<ChangeProposalDto>>> getNewChangeProposal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<CanBorrowBookDto>> canBorrowBookCopy(Long bookCopyId, Long userId) {
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
