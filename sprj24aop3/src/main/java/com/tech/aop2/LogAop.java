package com.tech.aop2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogAop {

	@Around("within(com.tech.aop2.*)")
	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable {

		String signatureStr = joinPoint.getSignature().toLongString();
		System.out.println(signatureStr + "시작");

		// 공통기능
		System.out.println("핵심 기능 전에 실행되는 공통 기능 : " + System.currentTimeMillis());

		try {
			// 핵심기능
			Object obj = joinPoint.proceed();
			return obj;
		} finally {
			// 공통기능
			System.out.println("핵심 기능 후 실행되는 공통 기능 : " + System.currentTimeMillis());
			System.out.println(signatureStr + "끝");
		}

	}

}
