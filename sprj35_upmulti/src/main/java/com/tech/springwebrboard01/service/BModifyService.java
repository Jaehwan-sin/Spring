package com.tech.springwebrboard01.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.springwebrboard01.dao.BoardDao;
import com.tech.springwebrboard01.dto.BoardDto;

public class BModifyService implements BServiceInf{

	@Override
	public void execute(Model model) {
		System.out.println("BModifyService-------");
//		map으로 변환
		Map<String, Object> map=model.asMap();
//		map->request
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String bid=request.getParameter("bid");
		String bName=request.getParameter("bName");
		String bTitle=request.getParameter("bTitle");
		String bContent=request.getParameter("bContent");
		
		
		BoardDao dao=new BoardDao();
		dao.modify(bid,bName,bTitle,bContent);
	}
}
