package com.tech.springwebrboard01.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.tech.springwebrboard01.dao.BoardDao;
import com.tech.springwebrboard01.dto.BoardDto;

public class BListService implements BServiceInf{

	@Override
	public void execute(Model model) {
		System.out.println("BListService-------");
		BoardDao dao=new BoardDao();//훳호출  BoardDao()의 생성자호출
		ArrayList<BoardDto> dtos=dao.list();
		
		//리턴받은 내용을 모델에 담기
		model.addAttribute("list",dtos);
		
	}
}
