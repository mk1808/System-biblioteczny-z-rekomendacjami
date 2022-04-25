package com.library.aspect;

import org.aspectj.lang.JoinPoint;

public interface AspectHelperService {
	public String getUserEmailFromRequest(JoinPoint joinPoint);
	

}
