package com.library.aspect;

import java.util.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class AspectHelperServiceImpl implements AspectHelperService {
	
	
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

	@Override
	public String getUserEmailFromRequest(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Cookie authCookie = request.getCookies()[0];
		if("Authorization".equals(authCookie.getName().toString())) {
			return getUserEmailFromCookie(authCookie);
		}
		return "";
		
	}
	
}
