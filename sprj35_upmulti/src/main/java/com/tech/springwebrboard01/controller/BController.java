package com.tech.springwebrboard01.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

//import com.oreilly.servlet.MultipartRequest;
//import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tech.springwebrboard01.dao.IDao;
import com.tech.springwebrboard01.dto.BoardDto;
import com.tech.springwebrboard01.dto.RebrdimgDto;
import com.tech.springwebrboard01.service.BServiceInf;
import com.tech.springwebrboard01.vopage.SearchVO;

@Controller
public class BController {
	BServiceInf commandInf;
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, SearchVO searchVO, Model model) {
		System.out.println("=========pass by list()=============");
//		db접속 데이터 가져오기
//		commandInf=new BListService();
//		commandInf.execute(model);
		
//		search
		String btitle="";
		String bcontent="";
		String[] brdtitle=request.getParameterValues("searchType");
		if(brdtitle!=null) {
			for(int i=0;i<brdtitle.length;i++) {
				System.out.println("brdtitle : "+brdtitle[i]);
			}
		}
		
//		ch 값 변수에 저장
		if(brdtitle!=null) {
			for (String val : brdtitle) {
				if(val.equals("btitle")) {
					model.addAttribute("btitle","true");//검색유지
					btitle="btitle";
				}else if(val.equals("bcontent")) {
					model.addAttribute("bcontent","true");//검색유지
					bcontent="bcontent";
				}
			}
		}
		
		
		String searchKeyword=request.getParameter("sk");
		if(searchKeyword==null)
			searchKeyword="";
		System.out.println("searchKeyWord : "+searchKeyword);
		
		
		
//		paging
		String strPage=request.getParameter("page");
		System.out.println("strPage1 : "+strPage);
		if(strPage==null)
			strPage="1";
		System.out.println("strPage2 : "+strPage);
		
		int page=Integer.parseInt(strPage);
		searchVO.setPage(page);
		
		
		IDao dao=sqlSession.getMapper(IDao.class);
//		totcnt
		//int total=dao.selectBoardTotCount();
		int total=0;
		if(btitle.equals("btitle") && bcontent.equals("")) {
			total=dao.selectBoardTotCount(searchKeyword,"1");
		}else if(btitle.equals("") && bcontent.equals("bcontent")) {
			total=dao.selectBoardTotCount(searchKeyword,"2");
		}else if(btitle.equals("btitle") && bcontent.equals("bcontent")) {
			total=dao.selectBoardTotCount(searchKeyword,"3");
		}else if(btitle.equals("") && bcontent.equals("")) {
			total=dao.selectBoardTotCount(searchKeyword,"0");
		}
		
		
		
		
		searchVO.pageCalculate(total);
		
		
		System.out.println("Totrowcnt : "+total);
		System.out.println("clickPage : "+strPage);
		System.out.println("pageStart : "+searchVO.getPageStart());
		System.out.println("pageEnd : "+searchVO.getPageEnd());
		System.out.println("pageTot : "+searchVO.getTotPage());
		System.out.println("rowStart : "+searchVO.getRowStart());
		System.out.println("rowEnd : "+searchVO.getRowEnd());
		
		int rowStart=searchVO.getRowStart();
		int rowEnd=searchVO.getRowEnd();
		
		
		if(btitle.equals("btitle") && bcontent.equals("")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"1"));
		}else if(btitle.equals("") && bcontent.equals("bcontent")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"2"));
		}else if(btitle.equals("btitle") && bcontent.equals("bcontent")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"3"));
		}else if(btitle.equals("") && bcontent.equals("")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"0"));
		}
		
		model.addAttribute("totRowCnt",total);
		model.addAttribute("searchVO",searchVO);
		return "list";
	}

	@RequestMapping("/write_view")
	public String write_view() {
		System.out.println("=========pass by write_view()=============");
		
		return "write_view";
	}
	@RequestMapping("/write")
	public String write(MultipartHttpServletRequest mtfRequest,
			Model model) throws Exception {
		System.out.println("=========pass by write()=============");
		//db에 글쓰기진행

		String bName=mtfRequest.getParameter("bName");
		String bTitle=mtfRequest.getParameter("bTitle");
		String bContent=mtfRequest.getParameter("bContent");
		
		System.out.println("bname : "+bName+" btitle : "+bName+" bcontent : "+bContent);
		
//		mybatis작업	
		IDao dao=sqlSession.getMapper(IDao.class);
		//for(int i=0;i<1000;i++) {
			dao.write(bName, bTitle, bContent,"nullfile");
			//Thread.sleep(50);
		//}
		
		//최근 입력된 글번호 가져오기
		int bid=dao.selBid();	
		
		String root="C:\\Users\\goott04\\Documents\\카카오톡 받은 파일\\springwebrboard07_upmulti\\src\\main\\webapp\\resources\\upload";
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
		
		for (MultipartFile mf : fileList) {
			String originFile=mf.getOriginalFilename();
			System.out.println("빈오리진 파일 확인:"+originFile);
			long longtime=System.currentTimeMillis();
			String changeFile=longtime+"_"+mf.getOriginalFilename();
			String pathfile=root+"\\"+changeFile;
			try {
				if (!originFile.equals("")) {
					mf.transferTo(new File(pathfile));//path장소 수정필요
					System.out.println("다중 업로드 성공!!!");
					dao.imgwrite(bid,originFile, changeFile);//db에 기록
					System.out.println("rebrdimgtb write 성공!!!");
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:list";
	}
	
	@RequestMapping("/download")
	public String download(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		System.out.println("=========pass by download()=============");
		String path=request.getParameter("p");
		String fname=request.getParameter("f");
		String bid=request.getParameter("bid");
		
		response.setHeader("Content-Disposition",
				"Attachment;filename="+URLEncoder.encode(fname,"utf-8"));//첨부파일임을 알려주고, 한글처리
		
		//down
		String attachPath="resources\\upload\\";
		//String realPath=request.getSession().getServletContext().getRealPath(attachPath)+"\\"+fname;
		//아래 코드 그대로ok
		String realPath="C:\\Users\\goott04\\Documents\\카카오톡 받은 파일\\springwebrboard07_upmulti\\src\\main\\webapp\\resources\\upload\\"+fname;
		
		System.out.println("realpath : "+realPath);
		
		FileInputStream fin=new FileInputStream(realPath);
		ServletOutputStream sout=response.getOutputStream();
		
		byte[] buf=new byte[1024];
		int size=0;
		while ((size=fin.read(buf, 0, 1024))!=-1) {
			sout.write(buf,0,size);
		}
		fin.close();
		sout.close();
				
		return "content_view?bid="+bid;
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("=========pass by content_view()=============");
		
//		model.addAttribute("request",request);
//		commandInf=new BContentViewService();
//		commandInf.execute(model);
		String bid=request.getParameter("bid");
//		mybatis작업
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.upHit(bid);//upHit처리
		
		BoardDto dto=dao.contentView(bid);
		model.addAttribute("content_view",dto);
		
		//이미지 테이블에서 복수개 이미지 가져오기
		ArrayList<RebrdimgDto> imglist=dao.selectImg(bid);
		model.addAttribute("imglist",imglist);
//		
		
		return "content_view";
	}
	@RequestMapping("/content_update")
	public String content_update(HttpServletRequest request, Model model) {
		System.out.println("=========pass by content_updateform()=============");
//		model.addAttribute("request",request);
//		commandInf=new BContentViewService();
//		commandInf.execute(model);
		
		String bid=request.getParameter("bid");
		IDao dao=sqlSession.getMapper(IDao.class);
		
		BoardDto dto=dao.contentView(bid);
		model.addAttribute("content_view",dto);
		
		return "content_update";
	}
	@RequestMapping(method=RequestMethod.POST,value="/modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("=========pass by modify()=============");
//		model.addAttribute("request",request);
//		commandInf=new BModifyService();
//		commandInf.execute(model);
		
		String bid=request.getParameter("bid");
		String bName=request.getParameter("bName");
		String bTitle=request.getParameter("bTitle");
		String bContent=request.getParameter("bContent");
		
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.modify(bid, bName, bTitle, bContent);
		
		return "redirect:list";
	}
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("=========pass by delete()=============");
//		model.addAttribute("request",request);
//		commandInf=new BDeleteService();
//		commandInf.execute(model);
		
		String bid=request.getParameter("bid");
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.delete(bid);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("=========pass by reply_view()=============");
//		model.addAttribute("request",request);
//		commandInf=new BReplyViewService();
//		commandInf.execute(model);
		
		String bid=request.getParameter("bid");
		IDao dao=sqlSession.getMapper(IDao.class);
		
		BoardDto dto=dao.replyView(bid);
		model.addAttribute("reply_view",dto);
		
		return "reply_view";
	}
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("=========pass by reply()=============");
//		model.addAttribute("request",request);
//		commandInf=new BReplyService();
//		commandInf.execute(model);
		String bid=request.getParameter("bid");
		String bName=request.getParameter("bName");
		String bTitle=request.getParameter("bTitle");
		String bContent=request.getParameter("bContent");
		String bgroup=request.getParameter("bgroup");
		String bstep=request.getParameter("bstep");
		String bindent=request.getParameter("bindent");
		
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.replyShape(bgroup, bstep);
		dao.reply(bid, bName, bTitle, bContent, bgroup, bstep, bindent);
		return "redirect:list";
	}
}
