package com.tech.sprj08.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {
			
			@RequestMapping("/studentConfirm")
			public String studentRedirect(HttpServletRequest request) {
				System.out.println("studentconfirm");
				String id = request.getParameter("id");
				
				if(id.equals("abc")) {
					return "redirect:studentOk"; // redirect는 다시 주소줄로 보낸다.
				}
				return "redirect:studentNg?msg='1234'";
				
			}
			
			@RequestMapping("/studentOk")
			public String studentOk(HttpServletRequest request) {
				System.out.println("studentOk"+request.getParameter("msg"));	
				return "studentOk";
			}
			
			@RequestMapping("/studentNg")
			public String studentNg(HttpServletRequest request) {
				System.out.println("studentNg");	
				return "studentNg";
			}
}
