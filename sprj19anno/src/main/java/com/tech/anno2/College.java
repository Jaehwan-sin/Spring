package com.tech.anno2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // Bean 등록
public class College {
		
		private Principal principal;
		private Teacher teacher;
		
		/*
		 * public College(Principal principal) {// Principal 생성자로 주입
		 * this.principal=principal; }
		 */
		
		public void test() {
			principal.principalInfo();
			teacher.teach();
			System.out.println("testing is call methods");
		}
		
		@Autowired // 자동주입
		public void setPrincipal(Principal principal) {
			this.principal = principal;
			System.out.println("Using setPrincipal method");
		}
		
		@Autowired
		public void setTeacher(Teacher teacher) {
			this.teacher = teacher;
			System.out.println("Using setTeacher method");
		}
		
		
}
