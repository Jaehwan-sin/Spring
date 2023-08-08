package com.tech.sprj09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;

/*48 BController에서 받은 값을 map으로 나눠 받아 Dao에 보내기*/
public class BDeleteService implements BServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println("BDeleteService 신호");
		
		// model에 담겨져 있는 정보를 Map으로 변환
		Map<String, Object> map = model.asMap();
		// map에서 request 추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bid = request.getParameter("bid");
		
		BoardDao dao = new BoardDao();
		dao.delete(bid);
		
		
	}

}
