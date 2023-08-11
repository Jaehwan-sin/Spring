package com.tech.sprj09.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;
import com.tech.sprj09.vopage.SearchVO;


@Service
public class BListService implements BServiceInter{
	private SqlSession sqlSession;	
	
	public BListService(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	@Override
	public void execute(Model model) {
		System.out.println(">>>BListService");
		
//		BoardDao dao=new BoardDao();
//		ArrayList<BoardDto> dtos=dao.list();//db에서 글전체를 가져오기
//		//리턴받은 내용을 모델에 담기
//		model.addAttribute("list",dtos);
//		==============================
		Map<String, Object> map=model.asMap();
//		맵에서 request를 풀기
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		
		SearchVO searchVO=
				(SearchVO) map.get("searchVO");
		
		
		
		
		IDao dao=sqlSession.getMapper(IDao.class);
		/* searching */
		
		String btitle="";
		String bcontent="";
		
		String[] brdtitle=request.getParameterValues("searchType");
		if(brdtitle!=null) {
			for(int i=0;i<brdtitle.length;i++) {
				System.out.println("brdtitle : "+brdtitle[i]);
			}
		}
//		변수에 저장
		if (brdtitle!=null) {
			for (String val : brdtitle) {
				if (val.equals("btitle")) {
					model.addAttribute("btitle","true");//검색체크유지
					btitle="btitle";
				}else if (val.equals("bcontent")) {
					model.addAttribute("bcontent","true");//검색체크유지
					bcontent="bcontent";
				}
			}
		}	
		
		//검색결과 유지하기	
		String bt=request.getParameter("btitle");
		String bc=request.getParameter("bcontent");
		
		if(bt!=null) {
			if (bt.equals("btitle")) {
				btitle=bt;
				model.addAttribute("btitle","true");//검색체크유지
			}
		}
		if(bc!=null) {
			if (bc.equals("bcontent")) {
				bcontent=bc;
				model.addAttribute("bcontent","true");//검색체크유지
			}
		}
		
		
		String searchKeyword=request.getParameter("sk");
		if(searchKeyword==null)
			searchKeyword="";
		model.addAttribute("resk",searchKeyword);//***
		System.out.println("searchkeyword : "+searchKeyword);
				
		/* paging */
		String strPage=request.getParameter("page");	
//		null검사
		if(strPage==null)
			strPage="1";

		int page=Integer.parseInt(strPage);
		searchVO.setPage(page);
		
//		total글의 개수구하기
//		int total=dao.selectBoardTotCount();		
//		조건에 따른 갯수구하기
		int total=0;
		if (btitle.equals("btitle") && bcontent.equals("")) {
			total=dao.selectBoardTotCount1(searchKeyword);
		}else if (btitle.equals("") && bcontent.equals("bcontent")) {
			total=dao.selectBoardTotCount2(searchKeyword);
		}else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			total=dao.selectBoardTotCount3(searchKeyword);
		}else if (btitle.equals("") && bcontent.equals("")) {
			total=dao.selectBoardTotCount4(searchKeyword);
		}
		

		searchVO.pageCalculate(total);
		
		//계산된 내용 출력
		System.out.println("totRow : "+total);
		System.out.println("clickpage : "+strPage);
		System.out.println("pageStart : "+searchVO.getPageStart());
		System.out.println("pageEnd : "+searchVO.getPageEnd());
		System.out.println("pageTot : "+searchVO.getTotPage());
		System.out.println("rowStart : "+searchVO.getRowStart());
		System.out.println("rowEnd : "+searchVO.getRowEnd());
		
		int rowStart=searchVO.getRowStart();
		int rowEnd=searchVO.getRowEnd();
		
//		ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);
		//ArrayList<BoardDto> list=null;
		
		if (btitle.equals("btitle") && bcontent.equals("")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"1"));
		}else if (btitle.equals("") && bcontent.equals("bcontent")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"2"));
		}else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"3"));
		}else if (btitle.equals("") && bcontent.equals("")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"4"));
		}
			
		//model.addAttribute("list",list);
		model.addAttribute("totRowcnt",total);
		model.addAttribute("searchVo",searchVO);
		
		
	}

}
