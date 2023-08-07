package com.tech.aop3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cats {
		private String name;
		private int age;
		private String color;
		
		public void getCatsInfo() {
			System.out.println("--------------------");
			System.out.println("이름 : "+getName());
			System.out.println("나이 : "+getAge());
			System.out.println("색상 : "+getColor());
			System.out.println("--------------------");
		}
		
		public void getColorChar() {
			if (name.equals("호랑이")) {
				System.out.println("--------------------");
				System.out.println("이 녀석 성격이 용맹하다.");
				System.out.println("--------------------");
			} else {
				System.out.println("--------------------");
				System.out.println("이 녀석 성격이 온순하다.");
				System.out.println("--------------------");
			}
		}
}
