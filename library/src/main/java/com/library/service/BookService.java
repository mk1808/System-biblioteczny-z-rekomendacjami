package com.library.service;

import java.util.List;
import java.util.UUID;

import com.library.dto.BookFIlterDto;
import com.library.dto.CanBorrowBookDto;
import com.library.enums.UserListType;
import com.library.model.Book;
import com.library.model.BookCopy;
import com.library.model.CanBorrowBook;
import com.library.model.ChangeProposal;
import com.library.model.Opinion;
import com.library.model.UserListElement;
import com.library.dto.BookAvailabilityDto;

public interface BookService extends RepositoryService<Book>{

	Book getByBookCopy(UUID bookCopyId);

	void createOpinion(Opinion opinion);

	List<Opinion> getOpinionsByBookId(UUID id);

	Opinion getOpinionByBookIdAndUserId(UUID userId, UUID bookId);

	List<Opinion> getOpinionsByUser(UUID userId);

	void updateOpinion(Opinion opinion);

	List<BookCopy> getBookCopiesByBookId(UUID id);

	void createChangeProposals(List<ChangeProposal> changeProposals);

	List<ChangeProposal> getChangeProposalsByBookId(UUID bookId);

	List<ChangeProposal> getNewChangeProposals();

	void updateChangePorposal(ChangeProposal changeProposal);

	void createUserListElement(UserListElement userListElement);

	List<UserListElement> getUserListElementByUserAndType(UUID userId, UserListType type);

	public void deleteUserListElement(UUID elementId);
	
	ChangeProposal createChangeProposal(ChangeProposal changeProposal);

	BookCopy getBookCopyById(UUID bookCopyId);

	List<BookCopy> getBookCopiesByIds(List<UUID> bookCopiesIds);

	List<Book> getFiltered(BookFIlterDto bookFilterDto);
	
	List<Book> getNewest(long number);

	BookAvailabilityDto getAvailabilityByBookId(UUID id);
	
	List<BookAvailabilityDto> getAvailabilityByBookIds(UUID[] ids);

	CanBorrowBook canBorrow(UUID bookCopyId, UUID userId);
	
	

}
