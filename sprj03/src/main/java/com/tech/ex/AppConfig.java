package com.tech.ex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	/* 빈 등록 */
	@Bean
	public Student student1() {
		Student student = new Student("홍길동", "20");
		student.setGradeNum("5학년");
		student.setClassNum("Computer");
		
		return student;
	}
	
	@Bean
	public Student student2() {
		Student student = new Student("홍길동2", "22");
		student.setGradeNum("2학년");
		student.setClassNum("Computer");
		
		return student;
		
	}
	
	@Bean
	public Studentinfo studentInfo() {
		Studentinfo studentInfo = new Studentinfo(this.student1());
		
		return null;
	}
}
