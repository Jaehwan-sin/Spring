package com.tech.sprj09.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;
import com.tech.sprj09.vopage.SearchVO;

/*7*/
public class BListService implements BServiceInter {

	@Override
	public void execute(Model model, IDao dao) {
		System.out.println("BListService 신호");
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String strPage = request.getParameter("page");
			
		System.out.println("page : "+strPage);
		int page = Integer.parseInt(strPage);
		
		SearchVO searchVO = new SearchVO();
		searchVO.setPage(page);
		
		int total = dao.selectBoardTotCount();
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

		ArrayList<BoardDto> dtos = dao.list(rowStart, rowEnd);
		model.addAttribute("list",dtos);
		/* #12 페이지 계산을 위해 list.jsp에 값 전달 */
		model.addAttribute("totRowcnt",total);
		model.addAttribute("searchVO",searchVO);
	}
			
}
