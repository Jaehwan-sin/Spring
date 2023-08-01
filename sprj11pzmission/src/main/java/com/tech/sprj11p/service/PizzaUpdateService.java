package com.tech.sprj11p.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj11p.dao.PizzaDao;
import com.tech.sprj11p.dto.PizzaDto;

public class PizzaUpdateService implements PizzaServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println("PizzaUpdateService 신호");
		
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String pzid = request.getParameter("pzid");
		
		PizzaDao dao = new PizzaDao();
		dao.PizzaContentView(pzid);
		
		
	}

}
