package com.tech.sprj09.controller;

import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;
import com.tech.sprj09.dto.JobDto;
import com.tech.sprj09.dto.StuDto;
import com.tech.sprj09.service.BContentViewService;
import com.tech.sprj09.service.BDeleteService;
import com.tech.sprj09.service.BDownloadService;
import com.tech.sprj09.service.BEmpSumService;
import com.tech.sprj09.service.BListService;
import com.tech.sprj09.service.BModifyService;
import com.tech.sprj09.service.BReplyService;
import com.tech.sprj09.service.BReplyViewService;
import com.tech.sprj09.service.BServiceInter;
import com.tech.sprj09.service.BWriteService;
import com.tech.sprj09.vopage.SearchVO;

@Controller
public class BController {

	BServiceInter bServiceInter;
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/stuheight")
	public String stuheight(Model model) {
		System.out.println("stuheight 신호");
		
		IDao dao=sqlSession.getMapper(IDao.class);
		JSONArray arr = new JSONArray();
		ArrayList<StuDto> stuhsum = dao.sumstuheight();
		
		for (StuDto stu : stuhsum) {
			System.out.println(stu.getGrade()+":"+stu.getSumheight());
			JSONObject obj = new JSONObject();
			obj.put("grade", stu.getGrade());
			obj.put("sumheight", stu.getSumheight());
			if (obj!=null) arr.add(obj);
		}
		
		model.addAttribute("arr",arr);
		
		return "chart/stuheight";
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,
			SearchVO searchVO, Model model) {
		System.out.println("====list()====");
//		데이터 가져오기 작업
		
		model.addAttribute("request",request);
		model.addAttribute("searchVO",searchVO);
		
		bServiceInter=new BListService(sqlSession);
		bServiceInter.execute(model);
		
		return "list";
	}
	@RequestMapping("/write_view")
	public String write_view() {
		System.out.println("====write_view()====");
		return "write_view";
	}
	@RequestMapping("/write")
	public String write(HttpServletRequest request,Model model) throws Exception {
		System.out.println("====write()====");
		
		model.addAttribute("request",request);
		
		bServiceInter=new BWriteService(sqlSession);
		bServiceInter.execute(model);

		return "redirect:list";
	}
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request,Model model) {
		System.out.println("====content_view()====");
//		글조회
//		toss
		model.addAttribute("request",request);
		bServiceInter=new BContentViewService(sqlSession);
		bServiceInter.execute(model);
		
		return "content_view";
	}
	@RequestMapping("/download")
	public String download(HttpServletRequest request,HttpServletResponse response,
			Model model) throws Exception {
		System.out.println("====download()====");
		
		model.addAttribute("request",request);
		model.addAttribute("response",response);
		
		bServiceInter=new BDownloadService(sqlSession);
		bServiceInter.execute(model);

		//return "content_view?bid="+bid;
		return null; 
	}
	
	@RequestMapping("/content_update")
	public String content_update(HttpServletRequest request,Model model) {
		System.out.println("====content_update()====");
//		글수정form
//		toss
		model.addAttribute("request",request);
		bServiceInter=new BContentViewService(sqlSession);
		bServiceInter.execute(model);
		
//		String bid=request.getParameter("bid");
//		IDao dao=sqlSession.getMapper(IDao.class);
//		BoardDto dto=dao.contentView(bid);
//		model.addAttribute("content_view",dto);
		
		return "content_update";
	}
	@RequestMapping(method = RequestMethod.POST,value = "/modify")
	public String modify(HttpServletRequest request,Model model) {
		System.out.println("====modify()====");
//		글수정update
//		toss
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
//		db에 데이터 조회
		//toss
		model.addAttribute("request",request);
		bServiceInter=new BReplyViewService(sqlSession);
		bServiceInter.execute(model);
		
		return "reply_view";
	}
	@RequestMapping(method = RequestMethod.POST,value = "/reply")
	public String reply(HttpServletRequest request,Model model) {
		System.out.println("====reply()====");
//		댓글달기
//		toss
		model.addAttribute("request",request);
		bServiceInter=new BReplyService(sqlSession);
		bServiceInter.execute(model);
			
		return "redirect:list";
	}
	
	@RequestMapping("/empsum")
	public String empsum(Model model) {
		System.out.println("empsum 신호");
		
		bServiceInter=new BEmpSumService(sqlSession);
		bServiceInter.execute(model);
		
		return "chart/jobgraph";
	}
	
}
