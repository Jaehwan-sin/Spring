package com.tech.sWeb05;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tech.sWeb05.dao.ReviewDao;
import com.tech.sWeb05.dto.Emp;
import com.tech.sWeb05.dto3.Student2;


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
	
	
	
	@RequestMapping(value = "/joinmap", method = RequestMethod.GET)
	public String joinmap(HttpServletRequest request, Model model) {
		System.out.println("call get db star");
		ReviewDao dao=sqlSession.getMapper(ReviewDao.class);

		ArrayList<Emp> joinlist=dao.getDeptEmpJoin();
		
		for (Emp emp : joinlist) {
			System.out.println(emp.getDeptno()+":"+emp.getDept().getDname());
		}
		
		model.addAttribute("list",joinlist);
		
		return "joinmap";
	}
	
	@RequestMapping(value = "/joinmap3", method = RequestMethod.GET)
	public String joinmap3(HttpServletRequest request, Model model) {
		System.out.println("call join3");
		ReviewDao dao=sqlSession.getMapper(ReviewDao.class);

		ArrayList<Student2> joinStu2list=dao.getStu2SubGrade();
		
		for (Student2 stu2 : joinStu2list) {
			   System.out.println(stu2.getNo()+":"+stu2.getSubject().getSubject_name()+":"+stu2.getScore()+":"+stu2.getGrade().getGrade());
		}
		
		// model.addAttribute("list",joinlist);
		
		return "joinmap3";
	}

	
	@RequestMapping(value = "/empview", method = RequestMethod.GET)
	public String deptview(HttpServletRequest request, Model model) {
		System.out.println("call get db star");
		ReviewDao dao=sqlSession.getMapper(ReviewDao.class);

		ArrayList<Emp> emplist=dao.getEmp();
		
		for (Emp emp : emplist) {
			System.out.println(emp.getDeptno()+":"+emp.getDept());
		}	
		model.addAttribute("list",emplist);		
		return "empview";
	}
	
	
	
}
