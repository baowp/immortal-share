/**
 * Project: analysis-entrance
 * 
 * File Created at May 11, 2013
 * $Id$Corporation
 * 
 * Copyright 2013-2015 Colomob.com Corporation Limited.
 * All rights reserved.
 */
package com.colomob.immortal.analysis.entrance.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author baowp
 * 
 */
@Component
@Aspect
public class RequestAspect {

	@Around("execution(* com.colomob.immortal.analysis.entrance.service..*.*(..))")
	public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = joinPoint.proceed();
		// saveLog(joinPoint, annotation);
		return result;
	}

	// @Before("execution(* com.iteye.baowp.springrest.controller..*.*(..))")
	@Before("execution(* com.colomob.immortal.analysis.entrance.service..*.*(..))")
	public void sample() {
		System.out.println("sample aspect");
	}
}
