package com.tech.sprj08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tech.model.Member;

@Controller
public class ParamController {
			
			@RequestMapping("/join/joinform")
			public String joinform() {
				
				return "join/joinform";
			}
			
//			@RequestMapping("/join/joinview")
//			public String joinview(@RequestParam("name") String name,
//					@RequestParam("id") String id,
//					@RequestParam("pw") String pw,
//					@RequestParam("email") String email,Model model) {
//				
//				model.addAttribute("name",name);
//				model.addAttribute("id",id);
//				model.addAttribute("pw",pw);
//				model.addAttribute("email",email);
//				
//				return "join/joinview";
//			}
			
			@RequestMapping("/join/joinview")
			public String joinview(@RequestParam("name") String name,
					@RequestParam("id") String id,
					@RequestParam("pw") String pw,
					@RequestParam("email") String email,Model model) {
				
				Member member = new Member();
				
				member.setName(name);
				member.setId(id);
				member.setPw(pw);
				member.setEmail(email);
				
				model.addAttribute("member",member);
				
				return "join/joinview";
			}
}
