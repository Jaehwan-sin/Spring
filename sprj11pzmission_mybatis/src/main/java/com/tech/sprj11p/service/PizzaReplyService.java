package com.tech.sprj11p.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj11p.dao.PizzaDao;
import com.tech.sprj11p.dto.PizzaDto;

public class PizzaReplyService implements PizzaServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println("PizzaReplyService 신호");
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String pzid = request.getParameter("pzid");
		String pzgroup = request.getParameter("pzgroup");
		String pzstep = request.getParameter("pzstep");
		String pzintent = request.getParameter("pzintent");
		String pzname = request.getParameter("pzname");
		String pzsubj = request.getParameter("pzsubj");
		String pzcontent = request.getParameter("pzcontent");
		
		PizzaDao dao = new PizzaDao();
		dao.PizzaReply(pzid,pzname,pzsubj,pzcontent,pzgroup,pzstep,pzintent);
		
	}

}
