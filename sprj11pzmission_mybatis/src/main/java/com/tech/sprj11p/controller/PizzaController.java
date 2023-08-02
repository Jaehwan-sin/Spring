package com.tech.sprj11p.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tech.sprj11p.dao.IDao;
import com.tech.sprj11p.dao.PizzaDao;
import com.tech.sprj11p.dto.PizzaDto;
import com.tech.sprj11p.service.PizzaContentViewService;
import com.tech.sprj11p.service.PizzaModifyService;
import com.tech.sprj11p.service.PizzaReplyService;
import com.tech.sprj11p.service.PizzaReplyViewService;
import com.tech.sprj11p.service.PizzaServiceInter;
import com.tech.sprj11p.service.pizzaDeleteService;

@Controller
public class PizzaController {
			
			PizzaServiceInter pizzaServiceInter;
			
			@Autowired /* (servlet-context.xml 에 있는 sqlSession Bean 가져온다) */
			private SqlSession sqlSession;
			
			@RequestMapping("/pzlist")
			public String list(Model model) {
//				pizzaServiceInter = new PizzaListService();
//				pizzaServiceInter.execute(model);
				
				IDao dao = sqlSession.getMapper(IDao.class);
				ArrayList<PizzaDto> dtos = dao.pzlist();
				
				model.addAttribute("pzlist",dtos);
				
				return "pzlist";
			}
			
			@RequestMapping("/pzwrite_view")
			public String pzwrite_view() {
				return "pzwrite_view";
			}
			
			@RequestMapping("/pzwrite")
			public String pzwrite(HttpServletRequest request, Model model) {
				
//				pizzaServiceInter = new PizzaWriteService();
//				pizzaServiceInter.execute(model);
				
				String pzname = request.getParameter("pzname");
				String pzsubj = request.getParameter("pzsubj");
				String pzcontent = request.getParameter("pzcontent");
				
				IDao dao = sqlSession.getMapper(IDao.class);
				dao.write(pzname,pzsubj,pzcontent);
				
				model.addAttribute("request",request);
				
				return "redirect:pzlist";
			}
			
			@RequestMapping("/pzcontent_view")
			public String pzcontent_view(HttpServletRequest request,Model model) {
				
//				pizzaServiceInter = new PizzaContentViewService();
//				pizzaServiceInter.execute(model);
				
				
				String pzid = request.getParameter("pzid");
				IDao dao = sqlSession.getMapper(IDao.class);	
				
				dao.upHit(pzid);
				
				PizzaDto dto = dao.PizzaContentView(pzid);
				
				model.addAttribute("pzcontent_view",dto);

				return "pzcontent_view";
			}
			
			@RequestMapping("/PizzaDelete")
			public String PizzaDelete(HttpServletRequest request,Model model) {
				
//				model.addAttribute("request",request);
//				pizzaServiceInter = new pizzaDeleteService();
//				pizzaServiceInter.execute(model);
				
				IDao dao = sqlSession.getMapper(IDao.class);
				
				String pzid = request.getParameter("pzid");
				dao.delete(pzid);
				
				return "redirect:pzlist";
			}
			
			@RequestMapping("/PizzaUpdate")
			public String PizzaUpdate(HttpServletRequest request,Model model) {
				
//				model.addAttribute("request",request);
//				pizzaServiceInter = new PizzaContentViewService();
//				pizzaServiceInter.execute(model);
				
				IDao dao = sqlSession.getMapper(IDao.class);
				
				String pzid = request.getParameter("pzid");
				PizzaDto dto = dao.PizzaContentView(pzid);
				
				model.addAttribute("pzcontent_view",dto);
				
				return "pzcontent_update";
			}
			
			@RequestMapping(method = RequestMethod.POST, value = "/pzmodify")
			public String pzmodify(HttpServletRequest request,Model model) {
				
//				model.addAttribute("request",request);
//				pizzaServiceInter = new PizzaModifyService();
//				pizzaServiceInter.execute(model);
				
				IDao dao = sqlSession.getMapper(IDao.class);
				
				String pzid = request.getParameter("pzid");
				String pzname = request.getParameter("pzname");
				String pzsubj = request.getParameter("pzsubj");
				String pzcontent = request.getParameter("pzcontent");
				
				dao.PizzaModify(pzid,pzname,pzsubj,pzcontent);
				
				return "redirect:pzlist";
			}
			
			@RequestMapping("/pzreply_view")
			public String pzreply_view(HttpServletRequest request,Model model) {
				
//				model.addAttribute("request",request);
//				pizzaServiceInter = new PizzaReplyViewService();
//				pizzaServiceInter.execute(model);
				
				IDao dao = sqlSession.getMapper(IDao.class);
				
				String pzid = request.getParameter("pzid");
				PizzaDto dto = dao.PizzaReplyView(pzid);
				
				model.addAttribute("pzreply_view",dto);
				
				
				return "pzreply_view";
			}
			
			@RequestMapping(method = RequestMethod.POST, value = "/pzreply")
			public String pzreply(HttpServletRequest request,Model model) {
				
//				model.addAttribute("request",request);
//				pizzaServiceInter = new PizzaReplyService();
//				pizzaServiceInter.execute(model);
				
				IDao dao = sqlSession.getMapper(IDao.class);

				String pzid = request.getParameter("pzid");
				String pzgroup = request.getParameter("pzgroup");
				String pzstep = request.getParameter("pzstep");
				String pzintent = request.getParameter("pzintent");
				String pzname = request.getParameter("pzname");
				String pzsubj = request.getParameter("pzsubj");
				String pzcontent = request.getParameter("pzcontent");
				
				dao.replyShape(pzgroup,pzstep);
				dao.PizzaReply(pzid,pzname,pzsubj,pzcontent,pzgroup,pzstep,pzintent);
				
				return "redirect:pzlist";
			}
}
