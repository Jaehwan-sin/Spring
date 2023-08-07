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
			public String list(HttpServletRequest request, Model model, SearchVO searchVO) {
				System.out.println("list");
				/*  8 데이터 가져오기 작업*/
//				bServiceInter = new BListService(); 다른 방법으로 DB접속하기
//				bServiceInter.execute(model);
				
				/* 6 새로운 dao */
				IDao dao = sqlSession.getMapper(IDao.class);
				
				String brd_title = "";
				String brd_content = "";
				
				String []  brdtitle = request.getParameterValues("searchType");
				
//				if (brdtitle!=null) { // 아무것도 선택하지 않으면 null 값이니 null이 아닐때만 for문을 써라.
//					for (int i = 0; i < brdtitle.length; i++) {
//						System.out.println("brdtitle : "+brdtitle[i]);
//					}
//				}
				
				if (brdtitle != null) {
					for (String var : brdtitle) {
						if (var.equals("brd_title")) {
							brd_title = "brd_title";
							model.addAttribute("brd_title","true");
						} else if (var.equals("brd_content")) {
							brd_content = "brd_content";
							model.addAttribute("brd_content","true");
						}
					}
				}
				
				String bt =  request.getParameter("brd_title");
				String bc =  request.getParameter("brd_content");
				
				if (bt != null) {
					if (bt.equals("brd_title")) {
						brd_title = bt;
						model.addAttribute("brd_title","true");
					}
				}
				
				if (bc != null) {
					if (bc.equals("brd_content")) {
						brd_title = bc;
						model.addAttribute("brd_content","true");
					}
				}
				
				String sk = request.getParameter("sk");
				
				if (sk==null) sk = "";
				model.addAttribute("resk",sk);
				
				String strPage = request.getParameter("page");
				
				if (strPage == null) strPage = "1";
				int page = Integer.parseInt(strPage);
				
				searchVO.setPage(page);
				
//				int total = dao.selectBoardTotCount();
				
				int total = 0;
				
				if (brd_title.equals("brd_title") && brd_content.equals("")) { 
					total = dao.selectBoardTotCount1(sk);
				} else if (brd_title.equals("") && brd_content.equals("brd_content")) {
					total = dao.selectBoardTotCount2(sk);
				} else if (brd_title.equals("brd_title") && brd_content.equals("brd_content")) {
					total = dao.selectBoardTotCount3(sk);
				} else if (brd_title.equals("") && brd_content.equals("")) {
					total = dao.selectBoardTotCount4(sk);
				}
				
				searchVO.pageCalculate(total);
				
				int rowStart = searchVO.getRowStart();
				int rowEnd = searchVO.getRowEnd();
				
//				ArrayList<BoardDto> dtos = dao.list(rowStart,rowEnd);
				ArrayList<BoardDto> list = null;
				
				if (brd_title.equals("brd_title") && brd_content.equals("")) {
					model.addAttribute("list",dao.list(rowStart, rowEnd,sk,"1"));
				} else if (brd_title.equals("") && brd_content.equals("brd_content")) {
					model.addAttribute("list",dao.list(rowStart, rowEnd,sk,"2"));
				} else if (brd_title.equals("brd_title") && brd_content.equals("brd_content")) {
					model.addAttribute("list",dao.list(rowStart, rowEnd,sk,"3"));
				} else if (brd_title.equals("") && brd_content.equals("")) {
					model.addAttribute("list",dao.list(rowStart, rowEnd,sk,"4"));
				}

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
				String brd_name = request.getParameter("brd_name");
				String brd_title = request.getParameter("brd_title");
				String brd_content = request.getParameter("brd_content");
				
				IDao dao= sqlSession.getMapper(IDao.class);
				dao.write(brd_name,brd_title,brd_content);
				
				return "redirect:list"; // 글쓰고 나서 list로 리턴하면 글 목록이 안나와서 redirect로 list로 다시 보낸다.
			}
			
			/* 31 제목을 누르면 내용 보이는 작업 */
			@RequestMapping("/content_view")
			public String content_view(HttpServletRequest request, Model model) {
				/* 33 글 내용 조회 */
//				model.addAttribute("request",request); // 2번째 request에 bid가 담겨있다 그걸 model에 담는다.
//				bServiceInter = new BContentViewService();
//				bServiceInter.execute(model);
				
				String brd_id = request.getParameter("brd_id");
				IDao dao = sqlSession.getMapper(IDao.class);
				
				dao.upHit(brd_id);
				
				BoardDto dto = dao.contentView(brd_id);
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
				
				String brd_id = request.getParameter("brd_id");
				
				IDao dao = sqlSession.getMapper(IDao.class);
				BoardDto dto = dao.contentView(brd_id);
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
				
				String brd_id = request.getParameter("brd_id");
				String brd_name = request.getParameter("brd_name");
				String brd_title = request.getParameter("brd_title");
				String brd_content = request.getParameter("brd_content");
				
				dao.modify(brd_id,brd_name,brd_title,brd_content);
				
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
				
				String brd_id = request.getParameter("brd_id");
				
				dao.delete(brd_id);
				
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
				
				String brd_id = request.getParameter("brd_id");
				IDao dao = sqlSession.getMapper(IDao.class);
				
				BoardDto dto = dao.replyView(brd_id);
				
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
				
				String brd_id = request.getParameter("brd_id");
				String brd_group = request.getParameter("brd_group");
				String brd_step = request.getParameter("brd_step");
				String brd_indent = request.getParameter("brd_indent");
				String brd_name = request.getParameter("brd_name");
				String brd_title = request.getParameter("brd_title");
				String brd_content = request.getParameter("brd_content");
				
				dao.replyShape(brd_group,brd_step);
				dao.reply(brd_id,brd_name,brd_title,brd_content,brd_group,brd_step,brd_indent);
				
				return "redirect:list";
			}
}
