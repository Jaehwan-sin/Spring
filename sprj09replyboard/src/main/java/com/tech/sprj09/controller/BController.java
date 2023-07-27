package com.tech.sprj09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.sprj09.service.BListService;
import com.tech.sprj09.service.BServiceInter;

@Controller /* 2 */
public class BController {
			
			/* 6 (인터페이스 만들고 선언) */
			BServiceInter bServiceInter; 
	
			/* 3 */
			@RequestMapping("/list")
			public String list(Model model) {
				System.out.println("list");
				/*  8 데이터 가져오기 작업*/
				bServiceInter = new BListService();
				bServiceInter.execute(model);
				
				return "list";
			}
}
