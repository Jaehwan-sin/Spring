package com.tech.sprj09.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

import jdk.nashorn.internal.ir.RuntimeNode.Request;

@Controller /* 2 */
public class BController {
			
			/* 6 (인터페이스 만들고 선언) */
			BServiceInter bServiceInter; 
			
			@Autowired /* (servlet-context.xml 에 있는 sqlSession Bean 가져온다) */
			private SqlSession sqlSession;
	 
			/* 3 */
			@RequestMapping("/list")
			public String list(HttpServletRequest request ,Model model) {
				System.out.println("list");
				IDao dao = sqlSession.getMapper(IDao.class);
				model.addAttribute("request",request);
				
				bServiceInter = new BListService();
				bServiceInter.execute(model, dao);

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
				
				IDao dao= sqlSession.getMapper(IDao.class);
				
				model.addAttribute("request",request); // request에는 글쓰기의 내용이 담겨져있다 그걸 model에 담는다.
				/* model의 타입은 String,Object타입이다. */
				
				bServiceInter = new BWriteService(); // 내용을 글쓰기 작업 처리하는 곳에 보낸다.
				bServiceInter.execute(model,dao); // 이 신호로 BWriteService의 execute가 실행된다.
				
//				String bname = request.getParameter("bname");
//				String btitle = request.getParameter("btitle");
//				String bcontent = request.getParameter("bcontent");
				
//				dao.write(bname,btitle,bcontent);
				
				return "redirect:list"; // 글쓰고 나서 list로 리턴하면 글 목록이 안나와서 redirect로 list로 다시 보낸다.
			}
			
			/* 31 제목을 누르면 내용 보이는 작업 */
			@RequestMapping("/content_view")
			public String content_view(HttpServletRequest request, Model model) {
				/* 33 글 내용 조회 */
				IDao dao = sqlSession.getMapper(IDao.class);
				
				model.addAttribute("request",request); // 2번째 request에 bid가 담겨있다 그걸 model에 담는다.
				
				bServiceInter = new BContentViewService();
				bServiceInter.execute(model, dao);
				
//				String bid = request.getParameter("bid");
				
//				dao.upHit(bid);
				
//				BoardDto dto = dao.contentView(bid);
//				model.addAttribute("content_view",dto);
				
				return "content_view";
			}
			
			/* 39 update 매핑 */
			@RequestMapping("/content_update")
			public String content_update(HttpServletRequest request, Model model) {
				
				/* 41 수정은 뷰와 같기 때문에 그대로 사용 / 글 수정 form */
				IDao dao = sqlSession.getMapper(IDao.class);
				
				model.addAttribute("request",request);
				
				bServiceInter = new BContentViewService();
				bServiceInter.execute(model, dao);
				
//				String bid = request.getParameter("bid");
//				
//				BoardDto dto = dao.contentView(bid);
//				model.addAttribute("content_view",dto);
						
				return "content_update";
			}
			
			/* 43 modify 매핑 method가 post방식일 땐 아래처럼 한다.*/
			@RequestMapping(method = RequestMethod.POST, value =  "/modify")
			public String modify(HttpServletRequest request, Model model) {
				
				/* 글 수정 update */
				IDao dao = sqlSession.getMapper(IDao.class);
				
				model.addAttribute("request",request);
				
				bServiceInter = new BModifyService();
				bServiceInter.execute(model, dao);
				
				
//				String bid = request.getParameter("bid");
//				String bname = request.getParameter("bname");
//				String btitle = request.getParameter("btitle");
//				String bcontent = request.getParameter("bcontent");
//				
//				dao.modify(bid,bname,btitle,bcontent);

				return "redirect:list";
			}
			
			/*47 delete 매핑*/
			@RequestMapping("/delete")
			public String delete(HttpServletRequest request, Model model) {
				System.out.println("Delete 신호");
				
				IDao dao = sqlSession.getMapper(IDao.class);
				
				model.addAttribute("request",request);
				bServiceInter = new BDeleteService();
				bServiceInter.execute(model, dao);
				
				
//				String bid = request.getParameter("bid");
				
//				dao.delete(bid);
				
				return "redirect:list";
			}
			
			/* 53 reply_view 매핑 */
			@RequestMapping("/reply_view")
			public String reply_view(HttpServletRequest request, Model model) {
				System.out.println("reply_view 신호");
//				DB에 데이터 조회
				
//				/* 55 BReplyViewService 객체 생성 */
				
				IDao dao = sqlSession.getMapper(IDao.class);
				
				model.addAttribute("request",request);
				
				bServiceInter = new BReplyViewService();
				bServiceInter.execute(model, dao);
				
//				String bid = request.getParameter("bid");
				
//				BoardDto dto = dao.replyView(bid);
				
//				model.addAttribute("reply_view",dto);
				
				return "reply_view";
			}
			
			/* 61 reply_view의 form 실행시 매핑 */
			@RequestMapping(method = RequestMethod.POST, value =  "/reply")
			public String reply(HttpServletRequest request, Model model) {
				
				/* 댓글달기 */
				IDao dao = sqlSession.getMapper(IDao.class);
				
				model.addAttribute("request",request);
				
				bServiceInter = new BReplyService();
				bServiceInter.execute(model, dao);

				
//				String bid = request.getParameter("bid");
//				String bgroup = request.getParameter("bgroup");
//				String bstep = request.getParameter("bstep");
//				String bindent = request.getParameter("bindent");
//				String bname = request.getParameter("bname");
//				String btitle = request.getParameter("btitle");
//				String bcontent = request.getParameter("bcontent");
				
//				dao.replyShape(bgroup,bstep);
//				dao.reply(bid,bname,btitle,bcontent,bgroup,bstep,bindent);
				
				return "redirect:list";
			}
}
