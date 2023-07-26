package com.tech.sprj07;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
															
		@RequestMapping("login/loginform")
		public String login() {
			return "login/loginform";
		}
		
		@RequestMapping("login/loginconfirm")
		public String loginconfirm(HttpServletRequest request, Model model) { // HttpservletRequest로 값을 받아오고 Model에 받은 값을 저장해서 다른곳으로 전달
			// loginform에서 입력한 정보 얻어오기
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			
			model.addAttribute("id",id);
			model.addAttribute("pwd",pwd);
			
			return "login/loginconfirm";
		}
		
}
