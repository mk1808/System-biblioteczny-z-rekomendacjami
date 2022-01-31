package com.library.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dto.BookAvailabilityDto;
import com.library.dto.BookFIlterDto;
import com.library.enums.BookCopyStatus;
import com.library.enums.ChangeProposalStatus;
import com.library.model.Book;
import com.library.model.BookCopy;
import com.library.model.Borrowing;
import com.library.model.ChangeProposal;
import com.library.model.Opinion;
import com.library.model.UserListElement;
import com.library.model.Reservation;
import com.library.repository.AuthorRepository;
import com.library.repository.BookCopyRepository;
import com.library.repository.BookRepository;
import com.library.repository.ChangeProposalRepository;
import com.library.repository.OpinionRepository;
import com.library.repository.UserListElementRepository;
import com.library.service.BookService;
import com.library.service.BorrowingService;
import com.library.service.ReservationService;

@Service
public class BookServiceImpl implements BookService {
private final BookRepository repository;
private final BookCopyRepository bookCopyRepository;
private final OpinionRepository opinionRepository;
private final UserListElementRepository userListElementRepository;
private final ChangeProposalRepository changeProposalRepository;
private final AuthorRepository authorRepository;
private final BorrowingService borrowingService;
private ReservationService reservationService;
	
	
	@Autowired
	public BookServiceImpl(BookRepository repository, BookCopyRepository bookCopyRepository, OpinionRepository opinionRepository,
			UserListElementRepository userListElementRepository, ChangeProposalRepository changeProposalRepository, AuthorRepository authorRepository,
			BorrowingService borrowingService) {
		this.repository = repository;
		this.bookCopyRepository = bookCopyRepository;
		this.opinionRepository = opinionRepository;
		this.userListElementRepository = userListElementRepository;
		this.changeProposalRepository = changeProposalRepository;
		this.authorRepository = authorRepository;
		this.borrowingService = borrowingService;
		
	}
	
	@Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


	@Override
	public Book get(UUID id) {
		return repository.getById(id);
	}

	@Override
	public Book create(Book entity) {
		return repository.save(entity);
	}

	@Override
	public Book update(Book entity) {
		return repository.save(entity);
	}


	@Override
	public Book getByBookCopy(UUID bookCopyId) {
		BookCopy bookCopy = bookCopyRepository.getById(bookCopyId);
		return bookCopy.getBook();
	}

	@Override
	public List<BookCopy> getBookCopiesByBookId(UUID id) {
		return bookCopyRepository.getBookCopiesByBookId(id);
	}
	
	@Override
	public BookCopy getBookCopyById(UUID bookCopyId) {
		return bookCopyRepository.getById(bookCopyId);
	}
	
	@Override
	public List<BookCopy> getBookCopiesByIds(List<UUID> bookCopiesIds) {
		return bookCopiesIds.stream().map(this::getBookCopyById).collect(Collectors.toList());
	}

	@Override
	public void createOpinion(Opinion opinion) {
		opinionRepository.save(opinion);
		
	}


	@Override
	public List<Opinion> getOpinionsByBookId(UUID id) {
		return opinionRepository.getByBookId(id);
	}


	@Override
	public Opinion getOpinionByBookIdAndUserId(UUID userId, UUID bookId) {
		return opinionRepository.getByBookIdAndUserId(bookId, userId);
	}


	@Override
	public List<Opinion> getOpinionsByUser(UUID userId) {
		return opinionRepository.getByUserId(userId);
	}


	@Override
	public void updateOpinion(Opinion opinion) {
		if(opinionRepository.getById(opinion.getId())!=null) {
			opinionRepository.save(opinion);
		}
	}


	


	@Override
	public void createChangeProposals(List<ChangeProposal> changeProposals) {
		changeProposals.stream().forEach(this::createChangeProposal);
		
	}
	
	@Override
	public ChangeProposal createChangeProposal(ChangeProposal changeProposal) {
		return changeProposalRepository.save(changeProposal);
	}


	@Override
	public List<ChangeProposal> getChangeProposalsByBookId(UUID bookId) {
		return changeProposalRepository.getChangeProposalsByBookId(bookId);
	}


	@Override
	public List<ChangeProposal> getNewChangeProposals() {
		return changeProposalRepository.getByStatus(ChangeProposalStatus.WAITING);
	}


	@Override
	public void updateChangePorposal(ChangeProposal changeProposal) {
		if(changeProposalRepository.getById(changeProposal.getId())!=null) {
			changeProposalRepository.save(changeProposal);
		}
		
	}


	@Override
	public void createUserListElement(UserListElement userListElement) {
		userListElementRepository.save(userListElement);
		
	}


	@Override
	public List<UserListElement> getUserListElementByUserAndType(UUID userId, String type) {
		return userListElementRepository.getByUserIdAndType(userId, type);
	}


	@Override
	public void deleteUserListElement(UUID elementId) {
		userListElementRepository.deleteById(elementId);
		
	}


	@Override
	public List<Book> getFiltered(BookFIlterDto bookFilterDto) {
		return repository.findByFilter(
				bookFilterDto.getISBN(), 
				bookFilterDto.getTitle(), 
				bookFilterDto.getAuthorId(), 
				bookFilterDto.getPublisherId(), 
				bookFilterDto.getGenreId());
	}


	@Override
	public BookAvailabilityDto getAvailabilityByBookId(UUID id) {
		List<BookCopy> copies = this.getBookCopiesByBookId(id);
		List<Borrowing> borrowings= this.borrowingService.getCurrentByBookCopies(copies);
		List<Reservation> reservations = this.reservationService.getByBookId(id);
		
		return BookAvailabilityDto.builder()
				.bookId(id)
				.allBooks(copies.size())
				.borrowedBooks(borrowings.size())
				.available(copies.size()-borrowings.size())
				.keptTooLong(this.borrowingService.getKeptTooLong(borrowings).size())
				.numberOfReservations(reservations.size())
				.status(borrowings.size()<copies.size()?BookCopyStatus.CANBORROW:BookCopyStatus.BORROWED)
				.build();
	}
		



}
