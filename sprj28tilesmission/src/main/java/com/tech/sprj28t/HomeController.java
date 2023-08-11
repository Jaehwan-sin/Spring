package com.tech.sprj28t;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String main(Locale locale, Model model) {		
		return "main";	
	}
	
	@RequestMapping(value = "layout", method = RequestMethod.GET)
	public String layout(Locale locale, Model model) {		
		return "common/layout";	
	}
	
	@RequestMapping(value = "notice.list", method = RequestMethod.GET)
	public String list (Locale locale, Model model) {
		
		model.addAttribute("list","여기는 리스트");
		
		return "notice/list";
	}
	
	@RequestMapping(value = "notice.detail", method = RequestMethod.GET)
	public String detail (Locale locale, Model model) {
		
		model.addAttribute("detail","여기는 디테일");
		
		return "notice/detail";
	}
	
	@RequestMapping(value = "notice.edit", method = RequestMethod.GET)
	public String edit (Locale locale, Model model) {
		
		model.addAttribute("edit","여기는 에디트");
		
		return "notice/edit";
	}
	
}
