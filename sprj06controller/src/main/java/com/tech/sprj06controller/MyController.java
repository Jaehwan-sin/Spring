package com.tech.sprj06controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
			
			@RequestMapping("view")
			public String View() {
				return "view";
			}
			
			@RequestMapping("/content/contentView")
			public String contentview(Model model) {
				// 데이터 전달
				model.addAttribute("id","Blue");
				model.addAttribute("name","홍길동");
				return "/content/contentView";
			}
			
			@RequestMapping("/model/modelEx")
			public String modelEx(Model model) {
				// 데이터 전달
				model.addAttribute("id","Model");
				model.addAttribute("name","김모델");
				return "/model/modelEx";
			}
			
			@RequestMapping("/modelAndView/modelView")
			public ModelAndView modelAndView() {
				
			    ModelAndView mv = new ModelAndView();
			    mv.addObject("data","modelandviewdata"); // Data 처리
			    mv.setViewName("modelAndView/modelView"); // View 처리
				
				return mv;
			}
			
			
}
