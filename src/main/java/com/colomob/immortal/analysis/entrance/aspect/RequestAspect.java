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

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.colomob.immortal.analysis.entrance.annotations.RequestUri;
import com.colomob.immortal.analysis.entrance.basic.EntranceConfig;
import com.colomob.immortal.analysis.entrance.httpclient.AnalysisHttpClient;

/**
 * @author baowp
 * 
 */
@Component
@Aspect
public class RequestAspect {
	@Autowired
	private EntranceConfig entranceConfig;
	@Autowired
	private AnalysisHttpClient analysisHttpClient;

	@Around("execution(* com.colomob.immortal.analysis.entrance.service..*.*(..))")
	public Object advice(ProceedingJoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint
				.getSignature();
		Method method = methodSignature.getMethod();
		RequestUri requestUri = method.getAnnotation(RequestUri.class);
		Object result = null;

		if (requestUri != null) {
			String url = entranceConfig.getRequestHttp() + requestUri.value();
			Object[] args = joinPoint.getArgs();
			analysisHttpClient.request(url, args[0]);
			try {
				result = joinPoint.proceed();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			System.out.println(entranceConfig.getRequestHttp());
		}
		return result;
	}

}
