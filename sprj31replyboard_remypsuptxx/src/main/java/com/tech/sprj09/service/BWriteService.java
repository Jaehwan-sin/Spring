package com.tech.sprj09.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;

public class BWriteService implements BServiceInter{

	private SqlSession sqlSession;
	
	public BWriteService(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void execute(Model model) {
		System.out.println(">>>BWriteService");
//		모델에서 request를 풀어서
//		맵으로 전환
		Map<String, Object> map = model.asMap();
//		맵에서 request를 뽑아내기
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
//		경로 만들기
//		String attachPath="resources\\upload\\";
//		String uploadPath=request.getSession().getServletContext().getRealPath("/");
//		System.out.println("uploadPath:"+uploadPath);
//		String path=uploadPath+attachPath;
//		System.out.println("pathhhh: "+path);
		String path="C:\\Users\\goott04\\git\\spring\\sprj31replyboard_remypsuptxx\\src\\main\\webapp\\resources\\upload";
		MultipartRequest req = null;
		
		try {
			req = new MultipartRequest(request, path, 1024*1024*20, "utf-8",
							new DefaultFileRenamePolicy());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String bname=req.getParameter("bname");
		String btitle=req.getParameter("btitle");
		String bcontent=req.getParameter("bcontent");
		String fname=req.getFilesystemName("file");
		
		if (fname==null) {
			fname="";
		}
		
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.write(bname,btitle,bcontent,fname);
		
	}

}
