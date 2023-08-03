package com.tech.sprj09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;

/*34*/
public class BContentViewService implements BServiceInter {

	@Override
	public void execute(Model model, IDao dao) {
		System.out.println("BContentViewService");
		// model에 담겨져 있는 정보를 Map으로 변환
		Map<String, Object> map = model.asMap();
		// map에서 request 추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bid = request.getParameter("bid");
		// DB접속
//		BoardDao dao = new BoardDao();
		dao.upHit(bid);
		BoardDto dto = dao.contentView(bid); // 35
		
		// 36 리턴받은 해당 글(dto)을 model에 적재
		model.addAttribute("content_view",dto); // content_view 라는 key값으로 dto를 저장
				
	}
}
