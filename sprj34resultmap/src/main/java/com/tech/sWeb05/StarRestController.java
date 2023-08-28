package com.tech.sWeb05;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class StarRestController {
	@RequestMapping("/starpoint")
	public String starpoint(HttpServletRequest request) {
	
		String point=request.getParameter("point");
		System.out.println("point : "+point);
		return point;
	}

}
