package com.library.aspect;

import static com.library.enums.Consts.BOOK_CTRL;
import static com.library.enums.Consts.OPINION_DTO;
import static com.library.enums.Consts.RESPONSE;

import java.util.Date;
import java.util.List;

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
import com.library.nosql.model.UserData;
import com.library.nosql.repository.BookDataRepository;
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
	
	@Autowired
	public BorrowingsControllerAspect(BookDataRepository bookItemRepository, UserDataRepository userDataRepository, AspectHelperService helperService,
			BookCopyRepository bookCopyRepository) {
		this.bookItemRepository = bookItemRepository;
		this.userDataRepository = userDataRepository;
		this.helperService = helperService;
		this.bookCopyRepository = bookCopyRepository;
	}
	
	@After(value = "target("+BORROWING_CTRL+") && execution("+RESPONSE+"String>> create("+LIST_BORROWING_DTO+")) && args(borrowingsDtos)")  
	public void afterBorrowingAdvice(JoinPoint joinPoint, List<BorrowingDto> borrowingsDtos) {   
		String userEmail = helperService.getUserEmailFromRequest(joinPoint);
		for (BorrowingDto borrowing:borrowingsDtos) {
			UserData toSave = UserData.builder()
				.action("borrowing")
				.bookId(bookCopyRepository.findById(borrowing.getBookCopyId()).get().getBook().getId().toString())
				.userId(userEmail)
				.date((new Date()).getTime())
				.build();
		userDataRepository.save(toSave);
		}
		
	} 

}
