package com.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.model.Book;
import com.library.model.BookCopy;
import com.library.model.ChangeProposal;
import com.library.model.Opinion;
import com.library.model.UserListElement;
import com.library.repository.BookRepository;
import com.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {
private BookRepository repository;
	
	
	@Autowired
	public BookServiceImpl(BookRepository repository) {
		this.repository = repository;
	}


	@Override
	public Book get(Long id) {
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
	public Book getByBookCopy(Long bookCopyId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void createOpinion(Opinion opinion) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Opinion> getOpinionsByBookId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Opinion getOpinionByBookIdAndUserId(Long userId, Long bookId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Opinion> getOpinionsByUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updateOpinion(Opinion opinion) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<BookCopy> getBookCopiesByBookId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void createChangeProposals(List<ChangeProposal> changeProposals) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<ChangeProposal> getChangeProposalsByBookId(Long bookId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<ChangeProposal> getNewChangeProposals() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updateChangePorposal(ChangeProposal changeProposal) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void createUserListElement(UserListElement userListElement) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<UserListElement> getUserListElementByUserAndType(Long userId, String type) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteUserListElement(Long elementId) {
		// TODO Auto-generated method stub
		
	}


}
