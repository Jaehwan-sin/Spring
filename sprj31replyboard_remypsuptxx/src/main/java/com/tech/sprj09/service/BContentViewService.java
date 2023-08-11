package com.tech.sprj09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;

public class BContentViewService implements BServiceInter{

	private SqlSession sqlSession;
	
	public BContentViewService(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void execute(Model model) {
		System.out.println(">>>BContentViewService");
		
//		map변환
		Map<String, Object> map=model.asMap();
//		map에서 request추출
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		
		String bid=request.getParameter("bid");
		IDao dao=sqlSession.getMapper(IDao.class);
		
		dao.upHit(bid);
		
		BoardDto dto=dao.contentView(bid);
		model.addAttribute("content_view",dto);
		
	}
}
