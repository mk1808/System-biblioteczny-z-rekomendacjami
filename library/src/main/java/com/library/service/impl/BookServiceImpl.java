package com.library.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.enums.ChangeProposalStatus;
import com.library.model.Book;
import com.library.model.BookCopy;
import com.library.model.ChangeProposal;
import com.library.model.Opinion;
import com.library.model.UserListElement;
import com.library.repository.BookCopyRepository;
import com.library.repository.BookRepository;
import com.library.repository.ChangeProposalRepository;
import com.library.repository.OpinionRepository;
import com.library.repository.UserListElementRepository;
import com.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {
private final BookRepository repository;
private final BookCopyRepository bookCopyRepository;
private final OpinionRepository opinionRepository;
private final UserListElementRepository userListElementRepository;
private final ChangeProposalRepository changeProposalRepository;
	
	
	@Autowired
	public BookServiceImpl(BookRepository repository, BookCopyRepository bookCopyRepository, OpinionRepository opinionRepository,
			UserListElementRepository userListElementRepository, ChangeProposalRepository changeProposalRepository) {
		this.repository = repository;
		this.bookCopyRepository = bookCopyRepository;
		this.opinionRepository = opinionRepository;
		this.userListElementRepository = userListElementRepository;
		this.changeProposalRepository = changeProposalRepository;
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


}
