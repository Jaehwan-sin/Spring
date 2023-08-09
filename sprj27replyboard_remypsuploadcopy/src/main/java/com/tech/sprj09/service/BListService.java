package com.tech.sprj09.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dto.BoardDto;

public class BListService implements BServiceInter{

	@Override
	public void execute(Model model) {
		System.out.println(">>>BListService");
//		db에 접속해서 데이터 가져오기
		BoardDao dao=new BoardDao();
		ArrayList<BoardDto> dtos=dao.list();
		//모델에 적재
		model.addAttribute("list",dtos);	
	}

}
