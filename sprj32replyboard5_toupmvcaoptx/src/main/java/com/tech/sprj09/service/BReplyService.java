package com.tech.sprj09.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;
@Service
public class BReplyService implements BServiceInter{
	private SqlSession sqlSession;	
	
	public BReplyService(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	@Transactional
	@Override
	public void execute(Model model) {
		System.out.println(">>>BReplyService");
		
//		map으로변환
		Map<String, Object> map=model.asMap();
		//map에서 request 빼오기
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		String bid=request.getParameter("bid");
		String bname=request.getParameter("bname");
		String btitle=request.getParameter("btitle");
		String bcontent=request.getParameter("bcontent");
		String bgroup=request.getParameter("bgroup");
		String bstep=request.getParameter("bstep");
		String bindent=request.getParameter("bindent");
		
//		System.out.println("bid >>>>>"+bid);
//		System.out.println("btitle >>>>>"+btitle);
		
//		BoardDao dao=new BoardDao();
		IDao dao=sqlSession.getMapper(IDao.class);
		int r1=dao.replyShape(bgroup, bstep);
		System.out.println("r11111:"+r1);
		int r2=dao.reply(bid,bname,btitle,bcontent,
				bgroup,bstep,bindent);
		System.out.println("r22222:"+r2);
//		
		
	}

}
