package com.tech.sprj09;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tech.sprj09.dao.ReviewDao;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired 
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/star", method = RequestMethod.GET)
	public String star(Locale locale, Model model) {
		
		return "star";
	}
	
	@RequestMapping(value = "/viewwrite", method = RequestMethod.GET)
	   public String viewwrite(HttpServletRequest request,Locale locale, Model model) {
	      ReviewDao dao=sqlSession.getMapper(ReviewDao.class);

	      String review=request.getParameter("review");
	      String point=request.getParameter("point");
	      System.out.println("review : "+review);
	      System.out.println("point : "+point);
	      
//	      point세션만 지우기(다른 세션은 필요할 수 있음으로)
	      HttpSession   session=request.getSession(); 
	      session.removeAttribute("point");
//	      
	      dao.reviewwrite(review,point);
	      
	      return "home";
	   }
	
}
