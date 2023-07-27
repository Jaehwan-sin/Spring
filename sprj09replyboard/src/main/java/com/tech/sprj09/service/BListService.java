package com.tech.sprj09.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dto.BoardDto;

/*7*/
public class BListService implements BServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println("BListService 신호");
		/* 9 DB에 접속해서 데이터 가져오기 */
		BoardDao dao = new BoardDao(); // 11 생성자 호출
		ArrayList<BoardDto> dtos = dao.list(); // 14는 dao.list() 처리 / 18은 ArrayList 리턴 작업
		
		model.addAttribute("list",dtos); // 22 model에 리턴받은 dtos를 담는다.
	}
			
}
