package com.tech.sprj09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dto.BoardDto;

/*56*/
public class BReplyViewService implements BServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println("BReplyViewService");
		// model에 담겨져 있는 정보를 Map으로 변환
		Map<String, Object> map = model.asMap();
		// map에서 request 추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bid = request.getParameter("bid");
		
		BoardDao dao = new BoardDao();
		BoardDto dto = dao.replyView(bid);
		
		/* 58 dao.replyView에서 받은 값을 reply_view로 저장 */
		model.addAttribute("reply_view",dto);
	}
}
