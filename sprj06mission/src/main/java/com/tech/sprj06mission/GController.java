package com.tech.sprj06mission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GController {
			
			@RequestMapping("/login/login")
			public String login(Model model) {
				model.addAttribute("id","아이디123");
				model.addAttribute("email","email@email.com");
				model.addAttribute("pass","pass123");
				
				return "/login/login";
			}
			
			@RequestMapping("/login/loginform")
			public String loginform(Model model) {
				model.addAttribute("id","로그인폼123");
				model.addAttribute("email","email@email.com");
				
				return "/login/loginform";
			}
			
			@RequestMapping("/join/joinForm")
			public String joinform(Model model) {
				model.addAttribute("id","조인폼123");
				
				return "/join/joinForm";
			}
	
}
