package com.tech.sprj09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.tech.sprj09.dao.IDao;

public class BReplyService implements BServiceInter{
	
	private SqlSession sqlSession;
	
	public BReplyService(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Transactional
	@Override
	public void execute(Model model) {
		System.out.println(">>>BReplyService");
		
//		map변환
		Map<String, Object> map=model.asMap();
//		map에서 request추출
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		
		String bid=request.getParameter("bid");
		String bgroup=request.getParameter("bgroup");
		String bstep=request.getParameter("bstep");
		String bindent=request.getParameter("bindent");
		String bname=request.getParameter("bname");
		String btitle=request.getParameter("btitle");
		String bcontent=request.getParameter("bcontent");
		
		IDao dao=sqlSession.getMapper(IDao.class);
		
		dao.replyShape(bgroup,bstep);
		dao.reply(bid,bname,btitle,bcontent,bgroup,bstep,bindent);
		
		
	}
}
