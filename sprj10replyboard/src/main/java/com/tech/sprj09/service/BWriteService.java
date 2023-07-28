package com.tech.sprj09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;

/*28 내용을 받아 DB에 글 Insert 작업*/
public class BWriteService implements BServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println("BWriteService 신호");
		/* model에서 request를 풀어서 map으로 전환 */
		Map<String, Object> map = model.asMap();
		/* map에서 request를 뽑아내기 */
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		// DB접속
		BoardDao dao = new BoardDao();
		dao.write(bname,btitle,bcontent);
		
	}

}
