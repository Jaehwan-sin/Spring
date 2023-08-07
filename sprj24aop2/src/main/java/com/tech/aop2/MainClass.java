package com.tech.aop2;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
		public static void main(String[] args) {
			AbstractApplicationContext ctx = 
					new GenericXmlApplicationContext("classpath:appCtx2.xml");
			
			// 설정파일에서 bean 가져오기
			Cats myCat = ctx.getBean("myCat",Cats.class);
			myCat.getCatsInfo();
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			myCat.getColorChar();
		}
}
