package com.tech.sprj08.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tech.model.Studentinfo;

@Controller
public class BoardController {

			@RequestMapping("/board/loginform")
			public String boardform() {
				
				return "board/loginform";
			}
			
			@RequestMapping(method = RequestMethod.POST, value = "/board/confirm") // 메소드로 보내는 경우 method를 어느 방식인지 써야하고, value에 가야하는 페이지 경로를 적는다.
			public String confirm(HttpServletRequest request, Model model) {
				
				String id = request.getParameter("id"); // request로 id 값을 id로 저장
				String pwd = request.getParameter("pwd");
				
				model.addAttribute("id",id); // request로 받은걸 id 라는 이름으로 model에 저장 후 전달
				model.addAttribute("pwd",pwd);
				
				return "board/loginconfirm";
			}
			
			// 데이터를 객체 통째로 전달
			@RequestMapping("/board/studentform")
			public String student() {
										
				return "board/studentform";
			}
						
			// 데이터를 객체 통째로 전달
			/*
			 * @RequestMapping("/board/studentinfo") public String
			 * studentinfo(@ModelAttribute("studentinfo") Studentinfo studentinfo) {
			 * 
			 * return "board/studentinfo"; }
			 */
			
			// 데이터를 객체 통째로 전달
			@RequestMapping("/board/studentinfo")
		    public String studentinfo(@ModelAttribute("stu") Studentinfo stu) { // studentinfo 를 stu로 줄여서 사용해도 가능
							
				return "board/studentinfo";
			}
			
}
