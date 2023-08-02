package com.tech.sprj11p.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.tech.sprj11p.dao.PizzaDao;
import com.tech.sprj11p.dto.PizzaDto;

public class PizzaListService implements PizzaServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println("PizzaListService 신호");
		
		PizzaDao dao = new PizzaDao();
		ArrayList<PizzaDto> dtos = dao.list();
		
		model.addAttribute("pzlist",dtos);
	}

}
