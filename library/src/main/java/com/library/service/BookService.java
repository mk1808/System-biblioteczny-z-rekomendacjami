package com.library.service;

import java.util.List;

import com.library.model.Book;
import com.library.model.BookCopy;
import com.library.model.ChangeProposal;
import com.library.model.Opinion;
import com.library.model.UserListElement;

public interface BookService extends RepositoryService<Book>{

	Book getByBookCopy(Long bookCopyId);

	void createOpinion(Opinion opinion);

	List<Opinion> getOpinionsByBookId(Long id);

	Opinion getOpinionByBookIdAndUserId(Long userId, Long bookId);

	List<Opinion> getOpinionsByUser(Long userId);

	void updateOpinion(Opinion opinion);

	List<BookCopy> getBookCopiesByBookId(Long id);

	void createChangeProposals(List<ChangeProposal> changeProposals);

	List<ChangeProposal> getChangeProposalsByBookId(Long bookId);

	List<ChangeProposal> getNewChangeProposals();

	void updateChangePorposal(ChangeProposal changeProposal);

	void createUserListElement(UserListElement userListElement);

	List<UserListElement> getUserListElementByUserAndType(Long userId, String type);

	public void deleteUserListElement(Long elementId);
	
	ChangeProposal createChangeProposal(ChangeProposal changeProposal);

}
