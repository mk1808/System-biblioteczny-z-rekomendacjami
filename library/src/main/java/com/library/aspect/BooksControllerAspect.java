package com.library.aspect;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.library.dto.BookDto;
import com.library.dto.OpinionDto;
import com.library.dto.UserListElementDto;
import com.library.nosql.model.UserData;
import com.library.nosql.repository.BookDataRepository;
import com.library.nosql.repository.UserDataRepository;
import com.library.response.Response;
import static com.library.enums.Consts.*;

@Aspect
@Component
public class BooksControllerAspect {
	
	private final BookDataRepository bookItemRepository;
	private final UserDataRepository userDataRepository;
	
	@Autowired
	public BooksControllerAspect(BookDataRepository bookItemRepository, UserDataRepository userDataRepository) {
		this.bookItemRepository = bookItemRepository;
		this.userDataRepository = userDataRepository;
	}
	

	
	@After(value = "target("+BOOK_CTRL+") && execution("+RESPONSE+"com.library.dto.BookDto>> getById(java.util.UUID)) && args(id)")  
	public void afterAdvice(JoinPoint joinPoint, UUID id) {  
		RequestContextHolder.getRequestAttributes().getAttribute("response", 3); 
		String userEmail = getUserEmailFromRequest(joinPoint);
		UserData toSave = UserData.builder()
				.action("get")
				.bookId(id.toString())
				.userId(userEmail)
				.date((new Date()).getTime())
				.build();
		userDataRepository.save(toSave);
	} 
	
	@After(value = "target("+BOOK_CTRL+") && execution("+RESPONSE+"String>> createUserListElement("+LIST_ELEM_DTO+")) && args(userListElementDto)")  
	public void afterToFavAdvice(JoinPoint joinPoint, UserListElementDto userListElementDto) {  
		RequestContextHolder.getRequestAttributes().getAttribute("response", 3); 
		String userEmail = getUserEmailFromRequest(joinPoint);
		UserData toSave = UserData.builder()
				.action(userListElementDto.getType().toString())
				.bookId(userListElementDto.getBookId().toString())
				.userId(userEmail)
				.date((new Date()).getTime())
				.value(userListElementDto.getType().toString())
				.build();
		userDataRepository.save(toSave);
	} 

	@After(value = "target("+BOOK_CTRL+") && execution("+RESPONSE+"String>> createOpinion("+OPINION_DTO+")) && args(opinionDto)")  
	public void afterOpinionAdvice(JoinPoint joinPoint, OpinionDto opinionDto) {  
		RequestContextHolder.getRequestAttributes().getAttribute("response", 3); 
		String userEmail = getUserEmailFromRequest(joinPoint);
		UserData toSave = UserData.builder()
				.action(StringUtils.isEmpty(opinionDto.getComment())?"opinion":"opinionWithComment")
				.bookId(opinionDto.getBookId().toString())
				.userId(userEmail)
				.date((new Date()).getTime())
				.value(opinionDto.getRating().toString())
				.build();
		userDataRepository.save(toSave);
	} 
	
	private String encodeUser(String jwt){
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String[] chunks = jwt.split("\\.");
		String payload = new String(decoder.decode(chunks[1]));
		return payload;
	}
	
	private String getUserEmail(String user) {
		String[] splitted = user.split("\"sub\":\"");
		String[] splitted2 = splitted[1].split("\"");
		System.out.println(splitted2[0]);  
		return splitted2[0];
		
	}
	
	private String getUserEmailFromCookie(Cookie authCookie) {
		String userEncoded = authCookie.getValue().toString();
		String userDecoded = encodeUser(userEncoded);
		System.out.println("getting - "+userDecoded);  
		return getUserEmail(userDecoded);
		
	}

	private String getUserEmailFromRequest(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Cookie authCookie = request.getCookies()[0];
		if("Authorization".equals(authCookie.getName().toString())) {
			return getUserEmailFromCookie(authCookie);
		}
		return "";
		
	}
}
