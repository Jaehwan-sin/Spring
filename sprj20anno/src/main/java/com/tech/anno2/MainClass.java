package com.tech.anno2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
		public static void main(String[] args) {
//			ApplicationContext context = new ClassPathXmlApplicationContext("com/tech/anno2/beans.xml"); bean 호출
			ApplicationContext context = new AnnotationConfigApplicationContext(CollegeConfig.class); // CollegeConfig.class 호출
			System.out.println("beans.xml file loaded");
			College college = context.getBean("college",College.class); // getBean의 ""는 bean의 id 또는 name
			System.out.println("This College obj by spring is : "+college);
			college.test(); // college의 test 객체 호출
		}
}
