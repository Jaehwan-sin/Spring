package com.tech.food;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/food")
public class FoodController {
			
			@RequestMapping("/kfood")
			public String kfood() {
				return "/food/kfood";
			}
			
			@RequestMapping("/kfoodconfirm")
			public String kfoodconfirm(HttpServletRequest request, Model model) {
				
				String kfood = request.getParameter("kfood");
				String kfood2 = request.getParameter("kfood2");
				
				model.addAttribute("kfood",kfood);
				model.addAttribute("kfood2",kfood2); 
				
				return "/food/kfoodconfirm";
			}
			
			@RequestMapping("/wfood")
			public String wfood() {
				return "/food/wfood";
			}
			
			@RequestMapping("/wfoodconfirm")
			public ModelAndView wfoodconfirm() {
				
				ModelAndView mv = new ModelAndView();
				
				mv.addObject("wfood","피자1");
				mv.addObject("wfood2","피자2");
				mv.setViewName("/food/wfoodconfirm"); // 페이지 이동하는 경로 정확히 지정해줘야한다.
				
				return mv;
			}
}
