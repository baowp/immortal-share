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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.colomob.immortal.analysis.entrance.basic.EntranceConfig;

/**
 * @author baowp
 * 
 */
@Component
@Aspect
public class RequestAspect {
	@Autowired
	private EntranceConfig entranceConfig;

	@Around("execution(* com.colomob.immortal.analysis.entrance.service..*.*(..))")
	public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = joinPoint.proceed();
		System.out.println(entranceConfig.getRequestHttp());
		return result;
	}

}
