package com.tech.ex;

import java.util.ArrayList;
import java.util.HashMap;

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
		
		ArrayList<String> hobbys = new ArrayList<String>();
		hobbys.add("수영1");
		hobbys.add("수영2");
		student.setHobbys(hobbys);
		
		HashMap<String, String> maps = new HashMap<String, String>();
		maps.put("1", "김밥1");
		maps.put("2", "김밥2");
		student.setMaps(maps);
		
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
