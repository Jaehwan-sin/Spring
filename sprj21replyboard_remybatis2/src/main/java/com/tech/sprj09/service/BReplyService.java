package com.tech.sprj09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;

/*62*/
public class BReplyService implements BServiceInter {

	@Override
	public void execute(Model model, IDao dao) {
		System.out.println("BReplyService");
		// model에 담겨져 있는 정보를 Map으로 변환
		Map<String, Object> map = model.asMap();
		// map에서 request 추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bid = request.getParameter("bid");
		String bgroup = request.getParameter("bgroup");
		String bstep = request.getParameter("bstep");
		String bindent = request.getParameter("bindent");
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
//		BoardDao dao = new BoardDao();
		dao.replyShape(bgroup,bstep);
		dao.reply(bid,bname,btitle,bcontent,bgroup,bstep,bindent);
		
	}
}
