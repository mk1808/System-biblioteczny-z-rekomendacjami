package com.library.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.library.dto.BookAvailabilityDto;
import com.library.dto.BookFIlterDto;
import com.library.dto.UserAvailabilityDto;
import com.library.enums.BookCopyStatus;
import com.library.enums.ChangeProposalStatus;
import com.library.enums.UserListType;
import com.library.model.AppUser;
import com.library.model.Book;
import com.library.model.BookCopy;
import com.library.model.BookKeyWord;
import com.library.model.Borrowing;
import com.library.model.CanBorrowBook;
import com.library.model.ChangeProposal;
import com.library.model.KeyWord;
import com.library.model.Opinion;
import com.library.model.Reservation;
import com.library.model.UserListElement;
import com.library.repository.AuthorRepository;
import com.library.repository.BookCopyRepository;
import com.library.repository.BookKeyWordRepository;
import com.library.repository.BookRepository;
import com.library.repository.ChangeProposalRepository;
import com.library.repository.KeyWordRepository;
import com.library.repository.OpinionRepository;
import com.library.repository.UserListElementRepository;
import com.library.service.BookService;
import com.library.service.BorrowingService;
import com.library.service.ReservationService;
import com.library.service.UserService;

@Service
public class BookServiceImpl implements BookService {
private final BookRepository repository;
private final BookCopyRepository bookCopyRepository;
private final OpinionRepository opinionRepository;
private final UserListElementRepository userListElementRepository;
private final ChangeProposalRepository changeProposalRepository;
private final AuthorRepository authorRepository;
private final KeyWordRepository keyWordRepository;
private final BookKeyWordRepository bookKeyWordRepository;
private final BorrowingService borrowingService;
private final UserService userService;
private ReservationService reservationService;

	
	
	@Autowired
	public BookServiceImpl(BookRepository repository, BookCopyRepository bookCopyRepository, OpinionRepository opinionRepository,
			UserListElementRepository userListElementRepository, ChangeProposalRepository changeProposalRepository, AuthorRepository authorRepository,
			KeyWordRepository keyWordRepository, BorrowingService borrowingService, UserService userService, BookKeyWordRepository bookKeyWordRepository) {
		this.repository = repository;
		this.bookCopyRepository = bookCopyRepository;
		this.opinionRepository = opinionRepository;
		this.userListElementRepository = userListElementRepository;
		this.changeProposalRepository = changeProposalRepository;
		this.authorRepository = authorRepository;
		this.keyWordRepository = keyWordRepository;
		this.borrowingService = borrowingService;
		this.userService = userService;
		this.bookKeyWordRepository = bookKeyWordRepository;
		
	}
	
	@Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
	
	@Autowired TestService testService;


	@Override
	public Book get(UUID id) {
		testService.save();
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
		return bookCopyRepository.findById(bookCopyId).orElse(null);
	}
	
	@Override
	public List<BookCopy> getBookCopiesByIds(List<UUID> bookCopiesIds) {
		return bookCopiesIds.stream().map(this::getBookCopyById).collect(Collectors.toList());
	}

	@Override
	public void createOpinion(Opinion opinion) {
	
		UUID bookId = opinion.getBook().getId();
		UUID userId = opinion.getUser().getId();
		Opinion oldOpinion = getOpinionByBookIdAndUserId(userId,bookId);
		if(oldOpinion!=null) {
			opinion.setId(oldOpinion.getId());
		}
		opinion.setBook(repository.getById(bookId));
		opinion.setUser(userService.get(userId));
		opinion.setCreated(new Date());
		opinionRepository.save(opinion);	
		updateBookAfterRating(opinion);
	}
	
	private void updateBookAfterRating(Opinion opinion) {
		UUID id = opinion.getBook().getId();
		List<Opinion> prevOpinions = getOpinionsByBookId(id);
		Book book = get(id);
		Double avgRating = book.getAvgRating()!=null?book.getAvgRating():0;
		Long rating = 0L;
		Long sum  = prevOpinions.stream().mapToLong(Opinion::getRating).sum();
		List<Opinion> prevByUser = prevOpinions.stream().filter(bRating->bRating.getUser().getId().equals(opinion.getUser().getId())).collect(Collectors.toList());
		Double newRating = Double.valueOf(0);
		if(prevByUser.size()>0) {
			newRating = Double.valueOf(( sum - prevByUser.get(0).getRating() + opinion.getRating()) / (prevOpinions.size()));
		}
		else {
			newRating = Double.valueOf(( sum + opinion.getRating()) / (prevOpinions.size()+1));
		}
			
		book.setAvgRating(newRating);
		repository.save(book);
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
		ChangeProposal newChangeProposal = new ChangeProposal();
		
		if(!CollectionUtils.isEmpty(changeProposals)) {
			UUID bookId = changeProposals.get(0).getBook().getId();
			UUID userId = changeProposals.get(0).getUser().getId();
			newChangeProposal.setBook(repository.getById(bookId));
			newChangeProposal.setUser(userService.get(userId));
		}
		
		changeProposals.stream().forEach(proposal->createSingleChangeProposal(proposal, newChangeProposal.getBook(), newChangeProposal.getUser()));
		saveKeyWords(changeProposals);
	}
	
	private ChangeProposal createSingleChangeProposal(ChangeProposal changeProposal, Book book, AppUser user) {
		changeProposal.setBook(book);
		changeProposal.setUser(user);
		return changeProposalRepository.save(changeProposal);
	}
	
	private void saveKeyWords(List<ChangeProposal> proposals) {
		List<KeyWord> newKeywords = new ArrayList<>();
		List<ChangeProposal> withKeyWords = proposals.stream().filter(proposal->"keyword".equals(proposal.getType()) ).collect(Collectors.toList());
		List<KeyWord> existingKeywords = new ArrayList<>();
		for (ChangeProposal proposal: withKeyWords) {
			try{
			    UUID uuid = UUID.fromString(proposal.getValue());
			    existingKeywords.add(keyWordRepository.findById(uuid).get());
			    
			} catch (IllegalArgumentException exception){
				KeyWord newKeyWord = keyWordRepository.save(KeyWord.builder().name(proposal.getValue()).build());
				newKeywords.add(newKeyWord);
			}
		}
		if(!CollectionUtils.isEmpty(withKeyWords)) {
			Book book = repository.findById(proposals.get(0).getBook().getId()).get();
			
			existingKeywords.addAll(newKeywords);
			for (KeyWord word: existingKeywords) {
				BookKeyWord bk = new BookKeyWord(book, word);
				bookKeyWordRepository.save(bk);
			}
		}
	}
	
	
	@Override
	public ChangeProposal createChangeProposal(ChangeProposal changeProposal) {
		UUID bookId = changeProposal.getBook().getId();
		UUID userId = changeProposal.getUser().getId();
		changeProposal.setBook(repository.getById(bookId));
		changeProposal.setUser(userService.get(userId));
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
	public List<UserListElement> getUserListElementByUserAndType(UUID userId, UserListType type) {
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
	
	public UserAvailabilityDto getAvailabilityByUserId(UUID id) {
		//List<BookCopy> copies = this.getBookCopiesByBookId(id);
		List<Borrowing> borrowings= this.borrowingService.getByUserId(id);
		List<Borrowing> keptTooLong=this.borrowingService.getKeptTooLong(borrowings);
	//	List<Reservation> reservations = this.reservationService.getCurrentByBookAndUser(id);
		
		return UserAvailabilityDto.builder()
				.currentlyBorrowed(borrowings.size())
				.keptTooLong(keptTooLong.size())
				.build();
	}


	@Override
	public CanBorrowBook canBorrow(UUID bookCopyId, UUID userId) {
	/*	UUID oldUserId = userId;
		if (bookCopyId!=null) {
			BookCopy bookCopyCheck = this.getBookCopyById(bookCopyId);
			if(bookCopyCheck==null) {
				userId=bookCopyId;
			}else {
				
			}
		}*/
		
		
		CanBorrowBook canBorrowBook = new CanBorrowBook();
		BookCopy bookCopy;
		Book book = null;
		BookAvailabilityDto bookAvailability;
		if (bookCopyId!=null&&this.getBookCopyById(bookCopyId)!=null) {
			bookCopy = this.getBookCopyById(bookCopyId);
			book = this.getByBookCopy(bookCopyId);
			bookAvailability = this.getAvailabilityByBookId(book.getId());
			canBorrowBook.setBook(book);
			canBorrowBook.setBookCopy(bookCopy);
			canBorrowBook.setBookAvailabilityDto(bookAvailability);}
		
		AppUser user;
		UserAvailabilityDto userAvailability;
		if (userId!=null&&userService.get(userId)!=null) {
			user = userService.get(userId);
			canBorrowBook.setUser(user);
			canBorrowBook.setUserAvailabilityDto(this.getAvailabilityByUserId(userId));
			
		}
		/*
		Boolean reservedByUser;
		if (userId!=null&&bookCopyId!=null) {
			List<Reservation> reservations = this.reservationService.getCurrentByBookAndUser(book.getId(), userId);
			reservedByUser=reservations.size()>0;
			canBorrowBook.setIsReservedByUser(reservedByUser);
			canBorrowBook.setReservationId(reservedByUser?reservations.get(0).getId():null);
		}*/
		
		return canBorrowBook;
	}
	
	void checkBook(UUID bookCopyId) {
		CanBorrowBook canBorrowBook = new CanBorrowBook();
		BookCopy bookCopy;
		Book book;
		BookAvailabilityDto bookAvailability;
		if (bookCopyId!=null) {
			bookCopy = this.getBookCopyById(bookCopyId);
			book = this.getByBookCopy(bookCopyId);
			bookAvailability = this.getAvailabilityByBookId(book.getId());
			canBorrowBook.setBook(book);
			canBorrowBook.setBookCopy(bookCopy);
			canBorrowBook.setBookAvailabilityDto(bookAvailability);
			
		}
	}
	void checkUser(UUID userId) {
		CanBorrowBook canBorrowBook = new CanBorrowBook();
		AppUser user;
		UserAvailabilityDto userAvailability;
		if (userId!=null) {
			user = userService.get(userId);
			canBorrowBook.setUser(user);
			canBorrowBook.setUserAvailabilityDto(this.getAvailabilityByUserId(userId));
			
			
			
			
		}
	}
	@Override
	public List<Book> getNewest(long number) {
		List<Book> books = new ArrayList<>();
		return this.repository.findAllByOrderByCreatedDesc().stream().limit(number).collect(Collectors.toList());
	}

	
		



}
