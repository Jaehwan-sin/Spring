package com.tech.sprj09.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;

//@Component
@Service
public class BDeleteService implements BServiceInter{

	private SqlSession sqlSession;	

	public BDeleteService(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BDeleteService");
		//모델에서 request를 풀기
//		맵으로변환		
		IDao dao=sqlSession.getMapper(IDao.class);	
		Map<String, Object> map=model.asMap();
//		맵에서 request를 풀기
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");	
		String bid=request.getParameter("bid");
			
		//BoardDao dao=new BoardDao();//db연결객체가 생성됨
		boolean delflag=delCondition(bid);
		
		if (delflag) {
			dao.delete(bid);
			System.out.println("삭제성공");
			System.out.println("삭제성공후 >>"+delflag);		
		}else {		
			System.out.println("삭제실패");
			System.out.println("삭제실패후 >>"+delflag);
		}	
	}
	public boolean delCondition(String bid) {
		System.out.println("===========delCondition()=============");
		
		IDao dao=sqlSession.getMapper(IDao.class);	
		boolean delflag=true;
//		선택글의 그룹과 스텝구하기 기존 메소드 활용
		BoardDto dto1=dao.contentView(bid);	
		ArrayList<BoardDto> sonlist=dao.sonReply(dto1.getBgroup());
		
		for (BoardDto dto2 : sonlist) {
			if(dto1.getBgroup()==dto2.getBgroup() &&
					dto1.getBstep()<dto2.getBstep() &&
					dto1.getBindent()<dto2.getBindent()) {
				delflag=false;
			}
		}
		return delflag;
	}	
}
