package com.tech.anno2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com.tech.anno2")
@Configuration
public class CollegeConfig {
			
//			@Bean
//			public Teacher mathTeacherBean() {
//					return new MathTeacher();
//			}
//	
//	
//			@Bean
//			public Principal principalBean() {
//				return new Principal();
//			}
//	
//	
//			@Bean(name = "col") // 이름을 col로 등록
//			// @Bean // bean으로 등록, bean으로 등록하면 소문자 college가 id가 된다.
//			public College college() { // 보통 클래스 이름을 소문자로 사용한다.
////				return new College(principalBean());
//				College college = new College();
//				college.setPrincipal(principalBean());
//				college.setTeacher(mathTeacherBean());
//				return college;
//			}
			
}
