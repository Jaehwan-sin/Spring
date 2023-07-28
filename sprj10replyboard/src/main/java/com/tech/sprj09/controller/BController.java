package com.tech.sprj09.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.sprj09.service.BContentViewService;
import com.tech.sprj09.service.BListService;
import com.tech.sprj09.service.BServiceInter;
import com.tech.sprj09.service.BWriteService;

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
			
			/* 25 글쓰기 페이지 호출 */
			@RequestMapping("/write_view")
			public String write_view() {
				return "write_view";
			}
			
			/* 27 글쓰기 버튼 눌렀을 때 작업 */
			@RequestMapping("/write")
			public String write(HttpServletRequest request, Model model) {
				/* 글쓰기 작업 진행, 내용을 받아와야하니 request,model 매개변수 지정*/
				
				model.addAttribute("request",request); // request에는 글쓰기의 내용이 담겨져있다 그걸 model에 담는다.
				/* model의 타입은 String,Object타입이다. */
				bServiceInter = new BWriteService(); // 내용을 글쓰기 작업 처리하는 곳에 보낸다.
				bServiceInter.execute(model); // 이 신호로 BWriteService의 execute가 실행된다.
				
				return "redirect:list"; // 글쓰고 나서 list로 리턴하면 글 목록이 안나와서 redirect로 list로 다시 보낸다.
			}
			
			/* 31 제목을 누르면 내용 보이는 작업 */
			@RequestMapping("/content_view")
			public String content_view(HttpServletRequest request, Model model) {
				/* 33 글 내용 조회 */
				model.addAttribute("request",request); // 2번째 request에 bid가 담겨있다 그걸 model에 담는다.
				bServiceInter = new BContentViewService();
				bServiceInter.execute(model);
				
				return "content_view";
			}
}
