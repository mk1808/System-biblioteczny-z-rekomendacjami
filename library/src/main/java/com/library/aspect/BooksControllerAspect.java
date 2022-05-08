package com.library.aspect;

import static com.library.enums.Consts.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;

import com.library.dto.ChangeProposalDto;
import com.library.dto.OpinionDto;
import com.library.dto.UserListElementDto;
import com.library.nosql.model.BookData;
import com.library.nosql.model.SingleActionData;
import com.library.nosql.model.UserAllData;
import com.library.nosql.model.UserData;
import com.library.nosql.repository.BookDataRepository;
import com.library.nosql.repository.UserAllDataRepository;
import com.library.nosql.repository.UserDataRepository;
import com.library.response.Response;

@Aspect
@Component
public class BooksControllerAspect {
	
	private final BookDataRepository bookDataRepository;
	private final UserDataRepository userDataRepository;
	private final UserAllDataRepository userAllDataRepository;
	private final AspectHelperService helperService;
	
	@Autowired
	public BooksControllerAspect(BookDataRepository bookItemRepository, UserDataRepository userDataRepository, AspectHelperService helperService,
			UserAllDataRepository userAllDataRepository) {
		this.bookDataRepository = bookItemRepository;
		this.userDataRepository = userDataRepository;
		this.userAllDataRepository = userAllDataRepository;
		this.helperService = helperService;
	}
	

	
	@After(value = "target("+BOOK_CTRL+") && execution("+RESPONSE+"com.library.dto.BookDto>> getById(java.util.UUID)) && args(id)")  
	public void afterAdvice(JoinPoint joinPoint, UUID id) {  
		RequestContextHolder.getRequestAttributes().getAttribute("response", 3); 
		String userEmail = helperService.getUserEmailFromRequest(joinPoint);
		UserData toSave = UserData.builder()
				.action("get")
				.bookId(id.toString())
				.userId(userEmail)
				.date((new Date()).getTime())
				.build();
		userDataRepository.save(toSave);
		saveAsUserAllData(userEmail, id.toString(), "get", null);
	} 
	
	@After(value = "target("+BOOK_CTRL+") && execution("+RESPONSE+"String>> createUserListElement("+LIST_ELEM_DTO+")) && args(userListElementDto)")  
	public void afterToFavAdvice(JoinPoint joinPoint, UserListElementDto userListElementDto) {  
		RequestContextHolder.getRequestAttributes().getAttribute("response", 3); 
		String userEmail = helperService.getUserEmailFromRequest(joinPoint);
		UserData toSave = UserData.builder()
				.action(userListElementDto.getType().toString())
				.bookId(userListElementDto.getBookId().toString())
				.userId(userEmail)
				.date((new Date()).getTime())
				.value(userListElementDto.getType().toString())
				.build();
		userDataRepository.save(toSave);
		saveAsUserAllData(userEmail, userListElementDto.getBookId().toString(), userListElementDto.getType().toString(), userListElementDto.getType().toString());
	} 

	@After(value = "target("+BOOK_CTRL+") && execution("+RESPONSE+"String>> createOpinion("+OPINION_DTO+")) && args(opinionDto)")  
	public void afterOpinionAdvice(JoinPoint joinPoint, OpinionDto opinionDto) {  
		RequestContextHolder.getRequestAttributes().getAttribute("response", 3); 
		String userEmail = helperService.getUserEmailFromRequest(joinPoint);
		UserData toSave = UserData.builder()
				.action(StringUtils.isEmpty(opinionDto.getComment())?"opinion":"opinionWithComment")
				.bookId(opinionDto.getBookId().toString())
				.userId(userEmail)
				.date((new Date()).getTime())
				.value(opinionDto.getRating().toString())
				.build();
		userDataRepository.save(toSave);
		saveAsUserAllData(userEmail, opinionDto.getBookId().toString(), StringUtils.isEmpty(opinionDto.getComment())?"opinion":"opinionWithComment", opinionDto.getRating().toString());
	} 
	
	//ResponseEntity<Response<String>> createChangeProposal(List<ChangeProposalDto> changeProposalsDto)
	
	@After(value = "target(" + BOOK_CTRL + ") && execution(" + RESPONSE + "String>> createChangeProposal("
			+ LIST_CHANGE_PROPOSAL_DTO + ")) && args(changeProposalsDto)")
	public void afterOpinionAdvice(JoinPoint joinPoint, List<ChangeProposalDto> changeProposalsDto) {
		String bookId = changeProposalsDto.get(0).getBookId().toString();
		List<String> keyWordsIds = new ArrayList();
		for (ChangeProposalDto proposal : changeProposalsDto) {
			if ("keyword".equals(proposal.getType())) {
				keyWordsIds.add(proposal.getValue());
			}
		}

		Optional<BookData> bookData = bookDataRepository.findById(bookId);
		if (bookData.isPresent()) {
			BookData book = bookData.get();
			keyWordsIds.addAll(book.getKeyWordIds());
			book.setKeyWordIds(keyWordsIds);
			bookDataRepository.save(book);
		} else {
			BookData toSave = BookData.builder().id(bookId).keyWordIds(keyWordsIds).build();
			bookDataRepository.save(toSave);
		}
	}
	
	private void saveAsUserAllData(String userId, String bookId, String actionDesc, String value) {
		Optional<UserAllData> userAllData = userAllDataRepository.findById(userId);
		SingleActionData action = SingleActionData
				.builder()
				.action(actionDesc)
				.bookId(bookId)
				.date((new Date()).getTime())
				.build();
		if(value!=null) {action.setValue(value);}
		if (userAllData.isPresent()) {
			UserAllData userData = userAllData.get();
			userData.withSingleAction(action);
			userAllDataRepository.save(userData);
		} else {
			UserAllData toSave = UserAllData.builder().userId(userId).action(action).build();
			userAllDataRepository.save(toSave);
		}
	}

}
