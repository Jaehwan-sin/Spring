package com.tech.sprj09.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;
import com.tech.sprj09.service.BContentViewService;
import com.tech.sprj09.service.BDeleteService;
import com.tech.sprj09.service.BListService;
import com.tech.sprj09.service.BModifyService;
import com.tech.sprj09.service.BReplyService;
import com.tech.sprj09.service.BReplyViewService;
import com.tech.sprj09.service.BServiceInter;
import com.tech.sprj09.service.BWriteService;
import com.tech.sprj09.vopage.SearchVO;

@Controller /* 2 */
public class BController {
			
			/* 6 (인터페이스 만들고 선언) */
			BServiceInter bServiceInter; 
			
			@Autowired /* (servlet-context.xml 에 있는 sqlSession Bean 가져온다) */
			private SqlSession sqlSession;
	 
			/* 3 */
			@RequestMapping("/list")
			public String list(HttpServletRequest request, SearchVO searchVO, Model model) {/* #2 페이징처리 request로 값 받기 #6 SearchVo 받기*/
				System.out.println("list");
				/*  8 데이터 가져오기 작업*/
//				bServiceInter = new BListService(); 다른 방법으로 DB접속하기
//				bServiceInter.execute(model);
				
				/* 6 새로운 dao */
				IDao dao = sqlSession.getMapper(IDao.class);
				
				/* $2 searching */
				String btitle = "";
				String bcontent = "";
				
				String [] brdtitle = request.getParameterValues("searchType"); // checkbox는 [] 로 받아야한다.
				System.out.println("brdtitle : "+brdtitle);
				if (brdtitle!=null) { // 아무것도 선택하지 않으면 null 값이니 null이 아닐때만 for문을 써라.
					for (int i = 0; i < brdtitle.length; i++) {
						System.out.println("brdtitle : "+brdtitle[i]);
					}
				}
				// 변수에 저장 $3
				if (brdtitle != null) { // 아무것도 선택하지 않으면 null 값이니 null이 아닐때만 for문을 써라.
					for (String var : brdtitle) {
						if (var.equals("btitle")) {
							btitle = "btitle";
							model.addAttribute("btitle","true");// $13
						} else if (var.equals("bcontent")) {
							bcontent = "bcontent";
							model.addAttribute("bcontent","true");// $13
						}
					}
				}
				
				// key값 (검색어) 가져오기 $4
				String searchKeyword = request.getParameter("sk");
				if (searchKeyword==null) { // home.jsp에서 list를 누르면 searchKeyword가 null일 수 밖에없어서 빈칸으로 바꿔준다.
					searchKeyword="";
				}
				model.addAttribute("resk",searchKeyword);// $12
				System.out.println("searchKeyword : "+searchKeyword);
				
				/* #3 페이징 처리 */
				String strPage = request.getParameter("page");
				/* #4 처음 페이지 null 처리 */
				if (strPage==null)
					strPage="1";
				System.out.println("page : "+strPage);
				int page = Integer.parseInt(strPage);				
				/* #7 현재 페이지를 받아온다. */
				searchVO.setPage(page);
				
				/* #8 글의 총 갯수 구하기 */
				//int total = dao.selectBoardTotCount();
				
				/* $5 검색에 따른 총 갯수 변형 */
				int total = 0;
				// 4개의 경우의 수로 총 갯수 구하기
				if (btitle.equals("btitle") && bcontent.equals("")) {
					total = dao.selectBoardTotCount1(searchKeyword);
				} else if (btitle.equals("") && bcontent.equals("bcontent")) {
					total = dao.selectBoardTotCount2(searchKeyword);
				} else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
					total = dao.selectBoardTotCount3(searchKeyword);
				} else if (btitle.equals("") && bcontent.equals("")) {
					total = dao.selectBoardTotCount4(searchKeyword);
				}
				
				System.out.println("total cnt: "+total);
				searchVO.pageCalculate(total);
				/* 계산 결과 출력하기 */
				System.out.println("total row: "+total);
				System.out.println("clickpage: "+searchVO.getPage());
				System.out.println("pageStart: "+searchVO.getPageStart());
				System.out.println("pageEnd: "+searchVO.getPageEnd());
				System.out.println("pageTot: "+searchVO.getTotPage());
				System.out.println("rowStart: "+searchVO.getRowStart());
				System.out.println("rowEnd: "+searchVO.getRowEnd());
				
				/* #9 페이징 글 번호 전달 */
				int rowStart = searchVO.getRowStart();
				int rowEnd = searchVO.getRowEnd();

				//ArrayList<BoardDto> dtos = dao.list(rowStart, rowEnd); $8
				ArrayList<BoardDto> list = null;
				
				if (btitle.equals("btitle") && bcontent.equals("")) {
//					list = dao.list(rowStart, rowEnd, searchKeyword,"1");
					model.addAttribute("list",dao.list(rowStart, rowEnd, searchKeyword,"1"));// $11
				} else if (btitle.equals("") && bcontent.equals("bcontent")) {
//					list = dao.list(rowStart, rowEnd, searchKeyword,"2");
					model.addAttribute("list",dao.list(rowStart, rowEnd, searchKeyword,"2"));
				} else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
//					list = dao.list(rowStart, rowEnd, searchKeyword,"3");
					model.addAttribute("list",dao.list(rowStart, rowEnd, searchKeyword,"3"));
				} else if (btitle.equals("") && bcontent.equals("")) {
//					list = dao.list(rowStart, rowEnd, searchKeyword,"4");
					model.addAttribute("list",dao.list(rowStart, rowEnd, searchKeyword,"4"));
				}
				
//				model.addAttribute("list",list);
				/* #12 페이지 계산을 위해 list.jsp에 값 전달 */
				model.addAttribute("totRowcnt",total);
				model.addAttribute("searchVO",searchVO);

				return "list";
			}
			
			/* 25 글쓰기 페이지 호출 */
			@RequestMapping("/write_view")
			public String write_view() {
				return "write_view";
			}
			
			/* 27 글쓰기 버튼 눌렀을 때 작업 */
			@RequestMapping("/write")
			public String write(HttpServletRequest request, Model model) {
				/* 글쓰기 작업 진행, 내용을 받아와야하니 request,model 매개변수 지정*/
				
//				model.addAttribute("request",request); // request에는 글쓰기의 내용이 담겨져있다 그걸 model에 담는다.
				/* model의 타입은 String,Object타입이다. */
//				bServiceInter = new BWriteService(); // 내용을 글쓰기 작업 처리하는 곳에 보낸다.
//				bServiceInter.execute(model); // 이 신호로 BWriteService의 execute가 실행된다.
				String bname = request.getParameter("bname");
				String btitle = request.getParameter("btitle");
				String bcontent = request.getParameter("bcontent");
				
				IDao dao= sqlSession.getMapper(IDao.class);
				dao.write(bname,btitle,bcontent);
				
				return "redirect:list"; // 글쓰고 나서 list로 리턴하면 글 목록이 안나와서 redirect로 list로 다시 보낸다.
			}
			
			/* 31 제목을 누르면 내용 보이는 작업 */
			@RequestMapping("/content_view")
			public String content_view(HttpServletRequest request, Model model) {
				/* 33 글 내용 조회 */
//				model.addAttribute("request",request); // 2번째 request에 bid가 담겨있다 그걸 model에 담는다.
//				bServiceInter = new BContentViewService();
//				bServiceInter.execute(model);
				
				String bid = request.getParameter("bid");
				IDao dao = sqlSession.getMapper(IDao.class);
				
				dao.upHit(bid);
				
				BoardDto dto = dao.contentView(bid);
				model.addAttribute("content_view",dto);
				
				return "content_view";
			}
			
			/* 39 update 매핑 */
			@RequestMapping("/content_update")
			public String content_update(HttpServletRequest request, Model model) {
				
				/* 41 수정은 뷰와 같기 때문에 그대로 사용 / 글 수정 form */
//				model.addAttribute("request",request);
//				bServiceInter = new BContentViewService();
//				bServiceInter.execute(model);
				
				String bid = request.getParameter("bid");
				
				IDao dao = sqlSession.getMapper(IDao.class);
				BoardDto dto = dao.contentView(bid);
				model.addAttribute("content_view",dto);
						
				return "content_update";
			}
			
			/* 43 modify 매핑 method가 post방식일 땐 아래처럼 한다.*/
			@RequestMapping(method = RequestMethod.POST, value =  "/modify")
			public String modify(HttpServletRequest request, Model model) {
				
				/* 글 수정 update */
//				model.addAttribute("request",request);
//				bServiceInter = new BModifyService();
//				bServiceInter.execute(model);
				
				IDao dao = sqlSession.getMapper(IDao.class);
				
				String bid = request.getParameter("bid");
				String bname = request.getParameter("bname");
				String btitle = request.getParameter("btitle");
				String bcontent = request.getParameter("bcontent");
				
				dao.modify(bid,bname,btitle,bcontent);
				
				return "redirect:list";
			}
			
			/*47 delete 매핑*/
			@RequestMapping("/delete")
			public String delete(HttpServletRequest request, Model model) {
				System.out.println("Delete 신호");
//				model.addAttribute("request",request);
//				bServiceInter = new BDeleteService();
//				bServiceInter.execute(model);
				
				IDao dao = sqlSession.getMapper(IDao.class);
				
				String bid = request.getParameter("bid");
				
				dao.delete(bid);
				
				return "redirect:list";
			}
			
			/* 53 reply_view 매핑 */
			@RequestMapping("/reply_view")
			public String reply_view(HttpServletRequest request, Model model) {
				System.out.println("reply_view 신호");
//				DB에 데이터 조회
				
//				model.addAttribute("request",request);
//				/* 55 BReplyViewService 객체 생성 */
//				bServiceInter = new BReplyViewService();
//				bServiceInter.execute(model);
				
				String bid = request.getParameter("bid");
				IDao dao = sqlSession.getMapper(IDao.class);
				
				BoardDto dto = dao.replyView(bid);
				
				model.addAttribute("reply_view",dto);
				
				return "reply_view";
			}
			
			/* 61 reply_view의 form 실행시 매핑 */
			@RequestMapping(method = RequestMethod.POST, value =  "/reply")
			public String reply(HttpServletRequest request, Model model) {
				
				/* 댓글달기 */
//				model.addAttribute("request",request);
//				bServiceInter = new BReplyService();
//				bServiceInter.execute(model);

				IDao dao = sqlSession.getMapper(IDao.class);
				
				String bid = request.getParameter("bid");
				String bgroup = request.getParameter("bgroup");
				String bstep = request.getParameter("bstep");
				String bindent = request.getParameter("bindent");
				String bname = request.getParameter("bname");
				String btitle = request.getParameter("btitle");
				String bcontent = request.getParameter("bcontent");
				
				dao.replyShape(bgroup,bstep);
				dao.reply(bid,bname,btitle,bcontent,bgroup,bstep,bindent);
				
				return "redirect:list";
			}
}
