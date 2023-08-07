package com.tech.aop3;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.tech.ex.Bottle;

public class MainClass {
		public static void main(String[] args) {
			AbstractApplicationContext ctx = 
					new GenericXmlApplicationContext("classpath:appCtx3.xml");
			
			// 설정파일에서 bean 가져오기
			Student student = ctx.getBean("student",Student.class);
			student.getStudentInfo();
			
			Worker worker = ctx.getBean("worker",Worker.class);
			worker.getWorkerInfo();
			
			Bottle bottle = ctx.getBean("bottle",Bottle.class);
			bottle.getBottle();
		}
}
