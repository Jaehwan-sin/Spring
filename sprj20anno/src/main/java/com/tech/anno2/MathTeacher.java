package com.tech.anno2;

import org.springframework.stereotype.Component;

@Component
public class MathTeacher  implements Teacher {

	@Override
	public void teach() {
		System.out.println("Hi Math Teacher");
		System.out.println("My name is Math");
		
	}

}
