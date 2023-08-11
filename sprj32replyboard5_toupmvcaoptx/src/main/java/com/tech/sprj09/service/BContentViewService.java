package com.tech.sprj09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;

@Service
public class BContentViewService implements BServiceInter{
	private SqlSession sqlSession;	
	
	public BContentViewService(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BContentViewService");
		
//		map으로변환
		Map<String, Object> map=model.asMap();
		//map에서 request 빼오기
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		String bid=request.getParameter("bid");
		
//		BoardDao dao=new BoardDao();
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.upHit(bid);
		BoardDto dto=dao.contentView(bid);
		//리턴받은 내용을 모델에 담기
		model.addAttribute("content_view",dto);
		
	}

}
