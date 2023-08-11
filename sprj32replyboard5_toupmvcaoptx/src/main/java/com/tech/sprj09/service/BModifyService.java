package com.tech.sprj09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.sprj09.dao.IDao;
@Service
public class BModifyService implements BServiceInter{

	private SqlSession sqlSession;	
	
	public BModifyService(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BModifyService");
		//모델에서 request를 풀기
//		맵으로변환
		Map<String, Object> map=model.asMap();
//		맵에서 request를 풀기
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		
		String bid=request.getParameter("bid");
		String bname=request.getParameter("bname");
		String btitle=request.getParameter("btitle");
		String bcontent=request.getParameter("bcontent");
		
//		BoardDao dao=new BoardDao();//db연결객체가 생성됨
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.modify(bid,bname,btitle,bcontent);
		
	}

}
