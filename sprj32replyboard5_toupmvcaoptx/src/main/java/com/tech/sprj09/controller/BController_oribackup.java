package com.tech.sprj09.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;
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

//@Controller
public class BController_oribackup {

	@Autowired
	private BDeleteService bDeleteService;
	@Autowired
	private BReplyViewService bReplyViewService;
	@Autowired
	private BReplyService bReplyService;
	@Autowired
	private BModifyService bModifyService;
	@Autowired
	private BContentViewService bContentViewService;
	@Autowired
	private BWriteService bWriteService;
	@Autowired
	private BListService bListService;
	@Autowired
	private BDownLoadService bDownLoadService;
	
	
	
//	@Autowired
//	private SqlSession sqlSession;
	
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,SearchVO searchVO,Model model) {
		System.out.println("======list()======");
//		db에서 데이터 가져오기
//		bServiceInter=new BListService();
		model.addAttribute("request",request);
		model.addAttribute("searchVO",searchVO);
		bListService.execute(model);
		
//		IDao dao=sqlSession.getMapper(IDao.class);
//		/* searching */
//		
//		String btitle="";
//		String bcontent="";
//		
//		String[] brdtitle=request.getParameterValues("searchType");
//		if(brdtitle!=null) {
//			for(int i=0;i<brdtitle.length;i++) {
//				System.out.println("brdtitle : "+brdtitle[i]);
//			}
//		}
////		변수에 저장
//		if (brdtitle!=null) {
//			for (String val : brdtitle) {
//				if (val.equals("btitle")) {
//					model.addAttribute("btitle","true");//검색체크유지
//					btitle="btitle";
//				}else if (val.equals("bcontent")) {
//					model.addAttribute("bcontent","true");//검색체크유지
//					bcontent="bcontent";
//				}
//			}
//		}	
//		
//		//검색결과 유지하기	
//		String bt=request.getParameter("btitle");
//		String bc=request.getParameter("bcontent");
//		
//		if(bt!=null) {
//			if (bt.equals("btitle")) {
//				btitle=bt;
//				model.addAttribute("btitle","true");//검색체크유지
//			}
//		}
//		if(bc!=null) {
//			if (bc.equals("bcontent")) {
//				bcontent=bc;
//				model.addAttribute("bcontent","true");//검색체크유지
//			}
//		}
//		
//		
//		String searchKeyword=request.getParameter("sk");
//		if(searchKeyword==null)
//			searchKeyword="";
//		model.addAttribute("resk",searchKeyword);//***
//		System.out.println("searchkeyword : "+searchKeyword);
//				
//		/* paging */
//		String strPage=request.getParameter("page");	
////		null검사
//		if(strPage==null)
//			strPage="1";
//
//		int page=Integer.parseInt(strPage);
//		searchVO.setPage(page);
//		
////		total글의 개수구하기
////		int total=dao.selectBoardTotCount();		
////		조건에 따른 갯수구하기
//		int total=0;
//		if (btitle.equals("btitle") && bcontent.equals("")) {
//			total=dao.selectBoardTotCount1(searchKeyword);
//		}else if (btitle.equals("") && bcontent.equals("bcontent")) {
//			total=dao.selectBoardTotCount2(searchKeyword);
//		}else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
//			total=dao.selectBoardTotCount3(searchKeyword);
//		}else if (btitle.equals("") && bcontent.equals("")) {
//			total=dao.selectBoardTotCount4(searchKeyword);
//		}
//		
//
//		searchVO.pageCalculate(total);
//		
//		//계산된 내용 출력
//		System.out.println("totRow : "+total);
//		System.out.println("clickpage : "+strPage);
//		System.out.println("pageStart : "+searchVO.getPageStart());
//		System.out.println("pageEnd : "+searchVO.getPageEnd());
//		System.out.println("pageTot : "+searchVO.getTotPage());
//		System.out.println("rowStart : "+searchVO.getRowStart());
//		System.out.println("rowEnd : "+searchVO.getRowEnd());
//		
//		int rowStart=searchVO.getRowStart();
//		int rowEnd=searchVO.getRowEnd();
//		
////		ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);
//		//ArrayList<BoardDto> list=null;
//		
//		if (btitle.equals("btitle") && bcontent.equals("")) {
//			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"1"));
//		}else if (btitle.equals("") && bcontent.equals("bcontent")) {
//			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"2"));
//		}else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
//			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"3"));
//		}else if (btitle.equals("") && bcontent.equals("")) {
//			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"4"));
//		}
//			
//		//model.addAttribute("list",list);
//		model.addAttribute("totRowcnt",total);
//		model.addAttribute("searchVo",searchVO);
//		
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
//		bServiceInter=new BWriteService();
		bWriteService.execute(model);
		
//		String bname=request.getParameter("bname");
//		String btitle=request.getParameter("btitle");
//		String bcontent=request.getParameter("bcontent");
		
////		upload
//		String attachPath="resources\\upload\\";
//		String uploadPath=request.getSession().getServletContext().getRealPath("/");
//		System.out.println("uploadPath : "+uploadPath);
//		String path=uploadPath+attachPath;
//		
////		path="C:\\javabigspring\\springwork22\\sprj25replyboard_remypgsupload\\src\\main\\webapp\\resources\\upload";
//		path="C:\\jsp2222set\\work222\\sprj25replyboard2_toupmvc\\src\\main\\webapp\\resources\\upload";
//		
//		MultipartRequest req=
//				new MultipartRequest(request, path, 1024*1024*20, "utf-8",
//						new DefaultFileRenamePolicy());
//		
//		
//		String bname=req.getParameter("bname");
//		String btitle=req.getParameter("btitle");
//		String bcontent=req.getParameter("bcontent");
//		String fname=req.getFilesystemName("file");
//		
//		System.out.println("filename : "+fname);
//		if (fname==null) {
//			fname="";
//		}
//			
//		IDao dao=sqlSession.getMapper(IDao.class);
//		dao.write(bname, btitle, bcontent,fname);
//		
//		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request,
			Model model) {
		System.out.println("======content_view()======");
//		db에 데이터 저장
		//toss
		model.addAttribute("request",request);
//		bServiceInter=new BContentViewService();
		bContentViewService.execute(model);
//		String sbid=request.getParameter("bid");
//		IDao dao=sqlSession.getMapper(IDao.class);
//		
//		dao.upHit(sbid);
//		
//		BoardDto dto=dao.contentView(sbid);
//		model.addAttribute("content_view",dto);
		
		return "content_view";
	}
	@RequestMapping("/download")
	public String download(HttpServletRequest request,HttpServletResponse response,
			Model model) throws Exception {
		System.out.println("======download()======");
		
		
//		String path=request.getParameter("p");
//		String fname=request.getParameter("f");
		String bid=request.getParameter("bid");
//		
//		//down
////		header에 신호주기 이것은 첨부파이라는 신호
//		response.setHeader("Content-Disposition",
//				"Attachment;filename="+URLEncoder.encode(fname,"utf-8"));
////		새로고침후 다운로드해야오류안남, 올릴때 realpath로 넣으면 바로 다운 가능
//		String attachPath="resources\\upload\\";
//		String realPath=request.getSession().getServletContext().getRealPath(attachPath)+"\\"+fname;
//		System.out.println("realPath : "+realPath);
//		
////		stream연결
//		FileInputStream fin=new FileInputStream(realPath);
//		ServletOutputStream sout=response.getOutputStream();
//		
//		byte[] buf=new byte[1024];
//		int size=0;
//		while ((size=fin.read(buf,0,1024))!=-1) {
//			sout.write(buf,0,size);
//		}
//		fin.close();
//		sout.close();
//		
		model.addAttribute("request",request);
		model.addAttribute("response",response);
//		bServiceInter=new BContentViewService();
		bDownLoadService.execute(model);
		
		return "content_view?bid="+bid;
	}
	
	
	
	@RequestMapping("/content_update")
	public String content_update(HttpServletRequest request,
			Model model) {
		System.out.println("======content_update()======");
//		db에 데이터 저장
		//toss
		model.addAttribute("request",request);
//		bServiceInter=new BContentViewService();
		bModifyService.execute(model);
		
//		String sbid=request.getParameter("bid");
//		IDao dao=sqlSession.getMapper(IDao.class);
//		
//		BoardDto dto=dao.contentView(sbid);
//		model.addAttribute("content_view",dto);
		
		
		return "content_update";
	}
	@RequestMapping(method = RequestMethod.POST,value = "/modify")
	public String modify(HttpServletRequest request,
			Model model) {
		System.out.println("======modify()======");
//		db에 데이터 저장
		//toss
		model.addAttribute("request",request);
//		bServiceInter=new BModifyService();
		bModifyService.execute(model);
		
//		String sbid=request.getParameter("bid");
//		String bname=request.getParameter("bname");
//		String btitle=request.getParameter("btitle");
//		String bcontent=request.getParameter("bcontent");
//		
//		IDao dao=sqlSession.getMapper(IDao.class);
//		dao.modify(sbid, bname, btitle, bcontent);
		
		return "redirect:list";
	}
	
//	@Autowired
//	private BServiceInter bServiceInter;
//	@Autowired
//	private BDeleteService bServiceInter;
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			Model model) {
		System.out.println("======delete()======");
//		db에 데이터 삭제
		//toss
		model.addAttribute("request",request);
//		bServiceInter=new BDeleteService();
		bDeleteService.execute(model);
//		String sbid=request.getParameter("bid");
//		IDao dao=sqlSession.getMapper(IDao.class);
//		dao.delete(sbid);
		
		return "redirect:list";
	}
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request,
			Model model) {
		System.out.println("======reply_view()======");
//		db에 데이터 삭제
		//toss
		model.addAttribute("request",request);
//		bServiceInter=new BReplyViewService();
		bReplyViewService.execute(model);
//		String sbid=request.getParameter("bid");
//		IDao dao=sqlSession.getMapper(IDao.class);
//		BoardDto dto=dao.replyView(sbid);
		
//		model.addAttribute("reply_view",dto);
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
//		bServiceInter=new BReplyService();
		bReplyService.execute(model);
//		String bid=request.getParameter("bid");
//		String bname=request.getParameter("bname");
//		String btitle=request.getParameter("btitle");
//		String bcontent=request.getParameter("bcontent");
//		String bgroup=request.getParameter("bgroup");
//		String bstep=request.getParameter("bstep");
//		String bindent=request.getParameter("bindent");
//		IDao dao=sqlSession.getMapper(IDao.class);
//		
//		dao.replyShape(bgroup, bstep);
//		dao.reply(bid, bname, btitle, bcontent,
//				bgroup, bstep, bindent);
//		
		//답글쓰기 폼
		return "redirect:list";
	}
}
