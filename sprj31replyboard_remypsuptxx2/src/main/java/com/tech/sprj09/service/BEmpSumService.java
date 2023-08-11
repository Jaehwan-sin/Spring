package com.tech.sprj09.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;
import com.tech.sprj09.dto.JobDto;

@Service
public class BEmpSumService implements BServiceInter{
	
	private SqlSession sqlSession;
	
	public BEmpSumService(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void execute(Model model) {
		System.out.println(">>>BEmpSumService");
		
		IDao dao=sqlSession.getMapper(IDao.class);
		JSONArray arr = new JSONArray();
		ArrayList<JobDto> jobnsum = dao.sumByjob();
		
		for (JobDto job : jobnsum) {
//			System.out.println(job.getJob()+":"+job.getSalsum());
			JSONObject obj=new JSONObject();
			obj.put("job",job.getJob());
			obj.put("selsum",job.getSalsum());
			   if(obj!=null)
			      arr.add(obj);
		}
		
		model.addAttribute("arr",arr);
		
	}

}
