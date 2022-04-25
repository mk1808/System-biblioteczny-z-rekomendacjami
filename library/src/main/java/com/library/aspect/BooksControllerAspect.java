package com.library.aspect;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.library.dto.BookDto;
import com.library.response.Response;

@Aspect
@Component
public class BooksControllerAspect {
//public ResponseEntity<Response<BookDto>> getById(UUID id)
	
//	@Pointcut("execution(* com.library.api.controller.BooksController.*.*(..))")
  //  public void serviceMethodExecution(){
    //}

	//@After(value = "getById() && args(id)")
	
	@After(value = "target(com.library.api.controller.BooksController) && execution(org.springframework.http.ResponseEntity<com.library.response.Response<com.library.dto.BookDto>> getById(java.util.UUID, javax.servlet.http.HttpServletRequest))")  
	public void afterAdvice(JoinPoint joinPoint) {  
		RequestContextHolder.getRequestAttributes().getAttribute("response", 3); 
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		request.getAttribute("testname");
	System.out.println("After method:" + joinPoint.getSignature()); 
	joinPoint.getArgs();
	System.out.println(request.getCookies()[0].getName().toString());
	System.out.println(request.getCookies()[0].getValue().toString());
			
	request.getCookies()[0].getName().toString();
	//System.out.println("getting - "+id);  
	} 

}
