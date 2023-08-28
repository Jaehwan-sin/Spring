package com.tech.springwebrboard01.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.springwebrboard01.dao.BoardDao;
import com.tech.springwebrboard01.dto.BoardDto;

public class BContentViewService implements BServiceInf{

	@Override
	public void execute(Model model) {
		System.out.println("BContentViewService-------");
//		map으로 변환
		Map<String, Object> map=model.asMap();
//		map->request
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String bid=request.getParameter("bid");
		
		
		//System.out.println("bName   :  "+bName);
		
		BoardDao dao=new BoardDao();
		BoardDto dto=dao.contentView(bid);
		
		model.addAttribute("content_view",dto);
	}
}
