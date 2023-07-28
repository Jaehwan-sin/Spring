package com.tech.sprj11p.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.sprj11p.service.PizzaContentViewService;
import com.tech.sprj11p.service.PizzaListService;
import com.tech.sprj11p.service.PizzaServiceInter;
import com.tech.sprj11p.service.PizzaWriteService;
import com.tech.sprj11p.service.pizzaDeleteService;

@Controller
public class PizzaController {
			
			PizzaServiceInter pizzaServiceInter;
	
			@RequestMapping("/pzlist")
			public String list(Model model) {
				pizzaServiceInter = new PizzaListService();
				pizzaServiceInter.execute(model);
				
				return "pzlist";
			}
			
			@RequestMapping("/pzwrite_view")
			public String pzwrite_view() {
				return "pzwrite_view";
			}
			
			@RequestMapping("/pzwrite")
			public String pzwrite(HttpServletRequest request, Model model) {
				
				model.addAttribute("request",request);
				pizzaServiceInter = new PizzaWriteService();
				pizzaServiceInter.execute(model);
				
				return "redirect:pzlist";
			}
			
			@RequestMapping("/pzcontent_view")
			public String pzcontent_view(HttpServletRequest request,Model model) {
				
				model.addAttribute("request",request);
				pizzaServiceInter = new PizzaContentViewService();
				pizzaServiceInter.execute(model);
				
				return "pzcontent_view";
			}
			
			@RequestMapping("/PizzaDelete")
			public String PizzaDelete(HttpServletRequest request,Model model) {
				
				model.addAttribute("request",request);
				pizzaServiceInter = new pizzaDeleteService();
				pizzaServiceInter.execute(model);
				
				return "redirect:pzlist";
			}
}
