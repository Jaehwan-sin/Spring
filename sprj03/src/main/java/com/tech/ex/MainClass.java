package com.tech.ex;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
	//	AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		Student student1 = ctx.getBean("student1",Student.class);
		
		System.out.println(student1.getName());
		System.out.println(student1.getAge());
		System.out.println(student1.getGradeNum());
		System.out.println(student1.getClassNum());
		System.out.println("----------------------");
		
		Student student2 = ctx.getBean("student2",Student.class);
		System.out.println(student2.getName());
		System.out.println(student2.getAge());
		System.out.println(student2.getGradeNum());
		System.out.println(student2.getClassNum());
		
		System.out.println("----------------------");
		Studentinfo studentinfo = ctx.getBean("studentinfo",Studentinfo.class);
		studentinfo.getStudentinfo();
		
		ctx.close();
	}
}
