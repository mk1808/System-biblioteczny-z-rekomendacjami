package com.library.aspect;

import static com.library.enums.Consts.BOOK_CTRL;
import static com.library.enums.Consts.OPINION_DTO;
import static com.library.enums.Consts.RESPONSE;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;

import com.library.dto.BorrowingDto;
import com.library.dto.OpinionDto;
import com.library.nosql.model.SingleActionData;
import com.library.nosql.model.UserAllData;
import com.library.nosql.model.UserData;
import com.library.nosql.repository.BookDataRepository;
import com.library.nosql.repository.UserAllDataRepository;
import com.library.nosql.repository.UserDataRepository;
import com.library.repository.BookCopyRepository;
import com.library.response.Response;

import static com.library.enums.Consts.*;

@Aspect
@Component
public class BorrowingsControllerAspect {
	
	private final BookDataRepository bookItemRepository;
	private final UserDataRepository userDataRepository;
	private final AspectHelperService helperService;
	private final BookCopyRepository bookCopyRepository;
	private final UserAllDataRepository userAllDataRepository;
	
	
	@Autowired
	public BorrowingsControllerAspect(BookDataRepository bookItemRepository, UserDataRepository userDataRepository, AspectHelperService helperService,
			BookCopyRepository bookCopyRepository, UserAllDataRepository userAllDataRepository) {
		this.bookItemRepository = bookItemRepository;
		this.userDataRepository = userDataRepository;
		this.helperService = helperService;
		this.bookCopyRepository = bookCopyRepository;
		this.userAllDataRepository = userAllDataRepository;
	}
	
	@After(value = "target("+BORROWING_CTRL+") && execution("+RESPONSE+"String>> create("+LIST_BORROWING_DTO+")) && args(borrowingsDtos)")  
	public void afterBorrowingAdvice(JoinPoint joinPoint, List<BorrowingDto> borrowingsDtos) {   
		String userEmail = helperService.getUserEmailFromRequest(joinPoint);
		for (BorrowingDto borrowing:borrowingsDtos) {
			String bookId = bookCopyRepository.findById(borrowing.getBookCopyId()).get().getBook().getId().toString();
		
			UserData toSave = UserData.builder()
				.action("borrowing")
				.bookId(bookId)
				.userId(userEmail)
				.date((new Date()).getTime())
				.build();
		userDataRepository.save(toSave);
		saveAsUserAllData(userEmail, bookId, "borrowing", null);
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
