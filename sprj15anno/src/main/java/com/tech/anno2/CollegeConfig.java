package com.tech.anno2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tech.anno3.College;

@Configuration
public class CollegeConfig {
			
			@Bean(name = "col") // 이름을 col로 등록
			// @Bean // bean으로 등록, bean으로 등록하면 소문자 college가 id가 된다.
			public College college() { // 보통 클래스 이름을 소문자로 사용한다.
				return new College();
			}
			
}
