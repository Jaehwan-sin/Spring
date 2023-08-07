package com.tech.aop3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop {
	
//	Aspectj문법sample
//	@Pointcut("execution(public void get*(..))")//public void 인 모든 get메소드
//	@Pointcut("execution(com.tech.ex.*.*())")//com.tech.ex패키지  파라메터가 없는 모든 메소드   
//	@Pointcut("execution(com.tech.ex..*.*())")//com.tech.ex패키지 & 
//	com.tech.ex하위패키지에 파라메터가 없는 모든 메소드
//	@Pointcut("execution(com.tech.ex.Worker.*())")//com.tech.ex.Worker 안의 모든 메소드

//	@Pointcut("within(com.tech.ex.*)")//com.tech.ex패키지의 모든 메소드
//	@Pointcut("within(com.tech.ex..*)")//com.tech.ex패키지및 하위패키지 모든 메소드
//	@Pointcut("within(com.tech.ex.Worker)")//Worker의 모든메소드
//	@Pointcut("bean(student)")//bean student인
	@Pointcut("bean(*ker)")//bean이 ker로 끝나는 
	
	public void pointcutMethod() {
		
	}

	@Around("pointcutMethod()")
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
