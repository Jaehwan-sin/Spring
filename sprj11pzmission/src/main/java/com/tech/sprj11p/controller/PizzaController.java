package com.tech.sprj11p.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tech.sprj11p.service.PizzaContentViewService;
import com.tech.sprj11p.service.PizzaListService;
import com.tech.sprj11p.service.PizzaModifyService;
import com.tech.sprj11p.service.PizzaReplyService;
import com.tech.sprj11p.service.PizzaReplyViewService;
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
			
			@RequestMapping("/PizzaUpdate")
			public String PizzaUpdate(HttpServletRequest request,Model model) {
				
				model.addAttribute("request",request);
				pizzaServiceInter = new PizzaContentViewService();
				pizzaServiceInter.execute(model);
				
				return "pzcontent_update";
			}
			
			@RequestMapping(method = RequestMethod.POST, value = "/pzmodify")
			public String pzmodify(HttpServletRequest request,Model model) {
				
				model.addAttribute("request",request);
				pizzaServiceInter = new PizzaModifyService();
				pizzaServiceInter.execute(model);
				
				return "redirect:pzlist";
			}
			
			@RequestMapping("/pzreply_view")
			public String pzreply_view(HttpServletRequest request,Model model) {
				
				model.addAttribute("request",request);
				pizzaServiceInter = new PizzaReplyViewService();
				pizzaServiceInter.execute(model);
				
				return "pzreply_view";
			}
			
			@RequestMapping(method = RequestMethod.POST, value = "/pzreply")
			public String pzreply(HttpServletRequest request,Model model) {
				
				model.addAttribute("request",request);
				pizzaServiceInter = new PizzaReplyService();
				pizzaServiceInter.execute(model);
				
				return "redirect:pzlist";
			}
}
