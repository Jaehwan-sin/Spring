package com.tech.sprj09.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tech.sprj09.service.BContentViewService;
import com.tech.sprj09.service.BDeleteService;
import com.tech.sprj09.service.BDownLoadService;
import com.tech.sprj09.service.BListService;
import com.tech.sprj09.service.BModifyService;
import com.tech.sprj09.service.BReplyService;
import com.tech.sprj09.service.BReplyViewService;
import com.tech.sprj09.service.BServiceInter;
import com.tech.sprj09.service.BWriteService;
import com.tech.sprj09.vopage.SearchVO;

@Controller
public class BController {

	@Autowired
	private SqlSession sqlSession;
	
	private BServiceInter bServiceInter;
//	@Autowired
//	private BDeleteService bDeleteService;
//	@Autowired
//	private BReplyViewService bReplyViewService;
//	@Autowired
//	private BReplyService bReplyService;
//	@Autowired
//	private BModifyService bModifyService;
//	@Autowired
//	private BContentViewService bContentViewService;
//	@Autowired
//	private BWriteService bWriteService;
//	@Autowired
//	private BListService bListService;
//	@Autowired
//	private BDownLoadService bDownLoadService;
	
//	private static final Logger logger = (Logger) LoggerFactory.getLogger(BController.class);
	
	@RequestMapping("/errors/error500")
	public String error() {
		System.out.println("======error()======");
//		입력데이터폼화면으로 전환
		
		return "/errors/error500";
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,SearchVO searchVO,Model model) {
		System.out.println("======list()======");
//		db에서 데이터 가져오기
		model.addAttribute("request",request);
		model.addAttribute("searchVO",searchVO);
		
//		logger.info("loggggggg:"+request.getContextPath()+"  :  "+request.getContextPath());
		bServiceInter=new BListService(sqlSession);
		bServiceInter.execute(model);
//		((org.slf4j.Logger) logger).info("Welcome home! The client locale is .");
		
	
		return "list";
	}
	@RequestMapping("/write_view")
	public String write_view() {
		System.out.println("======write_view()======");
//		입력데이터폼화면으로 전환
		
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request,
			Model model) throws Exception {
		System.out.println("======write()======");
//		db에 데이터 저장
		//toss
		model.addAttribute("request",request);
		bServiceInter=new BWriteService(sqlSession);
		bServiceInter.execute(model);

		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request,
			Model model) {
		System.out.println("======content_view()======");
//		db에 데이터 저장
		//toss
		model.addAttribute("request",request);
		bServiceInter=new BContentViewService(sqlSession);
		bServiceInter.execute(model);	
		return "content_view";
	}
	@RequestMapping("/download")
	public void download(HttpServletRequest request,HttpServletResponse response,
			Model model) throws Exception {
		System.out.println("======download()======");
		
		
//		String path=request.getParameter("p");
//		String fname=request.getParameter("f");
		String bid=request.getParameter("bid");

		model.addAttribute("request",request);
		model.addAttribute("response",response);
		bServiceInter=new BDownLoadService(sqlSession);
		bServiceInter.execute(model);
		
//		return "content_view?bid="+bid;
//		return null;
		
	}
	
	@RequestMapping("/content_update")
	public String content_update(HttpServletRequest request,
			Model model) {
		System.out.println("======content_update()======");
//		db에 데이터 저장
		//toss
		model.addAttribute("request",request);
		bServiceInter=new BContentViewService(sqlSession);
		bServiceInter.execute(model);
		
		return "content_update";
	}
	@RequestMapping(method = RequestMethod.POST,value = "/modify")
	public String modify(HttpServletRequest request,
			Model model) {
		System.out.println("======modify()======");
//		db에 데이터 저장
		//toss
		model.addAttribute("request",request);
		bServiceInter=new BModifyService(sqlSession);
		bServiceInter.execute(model);
		
		return "redirect:list";
	}
	

	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			Model model) {
		System.out.println("======delete()======");
//		db에 데이터 삭제
		//toss
		model.addAttribute("request",request);
		bServiceInter=new BDeleteService(sqlSession);
		bServiceInter.execute(model);
		return "redirect:list";
	}
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request,
			Model model) {
		System.out.println("======reply_view()======");
//		db에 데이터 삭제
		//toss
		model.addAttribute("request",request);
		bServiceInter=new BReplyViewService(sqlSession);
		bServiceInter.execute(model);

		//답글쓰기 폼
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request,
			Model model) {
		System.out.println("======reply()======");
//		db에 데이터 삭제
		//toss
		model.addAttribute("request",request);
		bServiceInter=new BReplyService(sqlSession);
		bServiceInter.execute(model);

		//답글쓰기 폼
		return "redirect:list";
	}
}
