package com.tech.sprj09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tech.sprj09.dao.IDao;

@Service
public class BWriteService implements BServiceInter{

	private SqlSession sqlSession;	
	
	public BWriteService(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	@Override
	public void execute(Model model) {
		System.out.println(">>>BWriteService");
		//모델에서 request를 풀기
//		맵으로변환
		Map<String, Object> map=model.asMap();
//		맵에서 request를 풀기
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
//		String bname=request.getParameter("bname");
//		String btitle=request.getParameter("btitle");
//		String bcontent=request.getParameter("bcontent");
//		
		
//		upload
		String attachPath="resources\\upload\\";
		String uploadPath=request.getSession().getServletContext().getRealPath("/");
		System.out.println("uploadPath : "+uploadPath);
		String path=uploadPath+attachPath;
		
//		path="C:\\javabigspring\\springwork22\\sprj25replyboard_remypgsupload\\src\\main\\webapp\\resources\\upload";
		path="C:\\jsp2222set\\work222\\sprj25replyboard2_toupmvc\\src\\main\\webapp\\resources\\upload";
		
		MultipartRequest req=null;
		try {
			req=
					new MultipartRequest(request, path, 1024*1024*20, "utf-8",
							new DefaultFileRenamePolicy());
			
		} catch (Exception e) {
			System.out.println("MultipartRequest error");
		}
		
		String bname=req.getParameter("bname");
		String btitle=req.getParameter("btitle");
		String bcontent=req.getParameter("bcontent");
		String fname=req.getFilesystemName("file");
		
		System.out.println("filename : "+fname);
		if (fname==null) {
			fname="";
		}
			
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.write(bname, btitle, bcontent,fname);
		
		
//		BoardDao dao=new BoardDao();//db연결객체가 생성됨
//		dao.write(bname,btitle,bcontent);
		
	}

}
