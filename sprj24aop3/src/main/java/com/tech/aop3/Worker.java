package com.tech.aop3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Worker {
		private String name;
		private int age;
		private String job;
		
		public void getWorkerInfo() {
			System.out.println("-------------------");
			System.out.println("이름 : "+getName());
			System.out.println("나이 : "+getAge());
			System.out.println("직업 : "+getJob());
			System.out.println("-------------------");
		}
		
		
}
