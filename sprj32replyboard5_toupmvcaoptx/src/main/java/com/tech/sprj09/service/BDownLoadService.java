package com.tech.sprj09.service;

import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.sprj09.dao.IDao;

@Service
public class BDownLoadService implements BServiceInter{
	private SqlSession sqlSession;	
	
	public BDownLoadService(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BDownLoadService");
		
//		map으로변환
		Map<String, Object> map=model.asMap();
		//map에서 request 빼오기
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		HttpServletResponse response=
				(HttpServletResponse) map.get("response");

		
//		BoardDao dao=new BoardDao();
		IDao dao=sqlSession.getMapper(IDao.class);
		String path=request.getParameter("p");
		String fname=request.getParameter("f");
		String bid=request.getParameter("bid");
		
		try {
			//down
//			header에 신호주기 이것은 첨부파이라는 신호
			response.setHeader("Content-Disposition",
					"Attachment;filename="+URLEncoder.encode(fname,"utf-8"));
//			새로고침후 다운로드해야오류안남, 올릴때 realpath로 넣으면 바로 다운 가능
			String attachPath="resources\\upload\\";
			String realPath=request.getSession().getServletContext().getRealPath(attachPath)+"\\"+fname;
			System.out.println("realPath : "+realPath);
			
//			stream연결
			FileInputStream fin=new FileInputStream(realPath);
			ServletOutputStream sout=response.getOutputStream();
			
			byte[] buf=new byte[1024];
			int size=0;
			while ((size=fin.read(buf,0,1024))!=-1) {
				sout.write(buf,0,size);
			}
			fin.close();
			sout.close();
		} catch (Exception e) {
			System.out.println("download error");
		}
			
	}

}
