package com.tech.sprj09.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tech.sprj09.dto.BoardDto;

public class BoardDao {
	DataSource dataSource;
	
//	public BoardDao() {
//		System.out.println("hihi");
////		db접속
//		try {
//			Context context=new InitialContext();
//			dataSource=(DataSource) context.lookup("java:comp/env/jdbc/springxe");
//			System.out.println("db success");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}	
//	}
	public ArrayList<BoardDto> list() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<BoardDto> dtos=new ArrayList<BoardDto>();
		
		try {
			con=dataSource.getConnection();
//			String sql="select bid,bname,btitle,bcontent,bdate,"
//					+ "bhit,bgroup,bstep,bindent from replyboard";
			
			String sql="select bid,bname,btitle,bcontent,bdate,"
					+ "bhit,bgroup,bstep,bindent from replyboard "
					+ "order by bgroup desc,bstep asc";
			
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
//			dtos에 글전체를 담아주기
			while (rs.next()) {
				int bid=rs.getInt("bid");
				String bname=rs.getString("bname");
				String btitle=rs.getString("btitle");
				String bcontent=rs.getString("bcontent");
				Timestamp bdate=rs.getTimestamp("bdate");
				
				int bhit=rs.getInt("bhit");
				int bgroup=rs.getInt("bgroup");
				int bstep=rs.getInt("bstep");
				int bindent=rs.getInt("bindent");
//				생성자를 통해 BoardDto에 담아주기
				BoardDto dto=new BoardDto(bid, bname,
						btitle, bcontent, bdate, 
						bhit, bgroup, bstep, bindent,null);
				//리스트에 담기
				dtos.add(dto);		
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return dtos;
	}
	public void write(String bname,String btitle,String bcontent) {
		//db연결후 insert쿼리 실행
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=dataSource.getConnection();
			String sql="insert into replyboard values(replyboard_seq.nextval,"
					+ "?,?,?,sysdate,0,"
					+ "replyboard_seq.currval,0,0)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			int rn=pstmt.executeUpdate();
			System.out.println("insert cnt : "+rn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	public BoardDto contentView(String sbid ) {
		
		//조회수 증가
		upHit(sbid);
		
		//bid를 조건으로 db에서 해당글 조회
		BoardDto dto=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=dataSource.getConnection();
			String sql="select bid,bname,btitle,bcontent,bdate,"
					+ "bhit,bgroup,bstep,bindent " 
					+"from replyboard where bid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sbid));
			rs=pstmt.executeQuery();
			//rs를 dto에 담아주기
			if(rs.next()) {
				int bid=rs.getInt("bid");
				String bname=rs.getString("bname");
				String btitle=rs.getString("btitle");
				String bcontent=rs.getString("bcontent");
				Timestamp bdate=rs.getTimestamp("bdate");
				
				int bhit=rs.getInt("bhit");
				int bgroup=rs.getInt("bgroup");
				int bstep=rs.getInt("bstep");
				int bindent=rs.getInt("bindent");
				
				dto=new BoardDto(bid, bname, btitle,
						bcontent, bdate, bhit, bgroup,
						bstep, bindent,null);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return dto;
	}
	public void upHit(String sbid) {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=dataSource.getConnection();
			String sql="update replyboard " + 
					"set bhit=bhit+1 " + 
					"where bid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sbid));
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
	}
	
	
	
	public void modify(String sbid,String bname,
			String btitle,String bcontent) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=dataSource.getConnection();
			String sql="update replyboard "
					+ "set bname=?,btitle=?,"
					+ "bcontent=? where bid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setInt(4, Integer.parseInt(sbid));
//			업데이트 실행
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	public void delete(String sbid ) {
		//bid를 조건으로 db에서 해당글 삭제
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=dataSource.getConnection();
			String sql="delete from replyboard where bid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sbid));
//			삭제 실행
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	public BoardDto replyView(String sbid) {
		//bid를 조건으로 db에서 해당글 조회
		BoardDto dto=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=dataSource.getConnection();
			String sql="select bid,bname,btitle,bcontent,bdate,"
					+ "bhit,bgroup,bstep,bindent " 
					+"from replyboard where bid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sbid));
			rs=pstmt.executeQuery();
			//rs를 dto에 담아주기
			if(rs.next()) {
				int bid=rs.getInt("bid");
				String bname=rs.getString("bname");
				String btitle=rs.getString("btitle");
				String bcontent=rs.getString("bcontent");
				Timestamp bdate=rs.getTimestamp("bdate");
				
				int bhit=rs.getInt("bhit");
				int bgroup=rs.getInt("bgroup");
				int bstep=rs.getInt("bstep");
				int bindent=rs.getInt("bindent");
				
				dto=new BoardDto(bid, bname, btitle,
						bcontent, bdate, bhit, bgroup,
						bstep, bindent,null);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return dto;
	}
	
	public void reply(String bid,String bname,String btitle,
			String bcontent,String bgroup,String bstep,String bindent) {
//		댓글을 달려는 원글의 스텝번호보다 큰 스텝이 있다면
//		1을 더해준다.
		replyShape(bgroup,bstep);
		
		//db연결후 댓글insert쿼리 실행
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=dataSource.getConnection();
			String sql="insert into replyboard(bid,bname,btitle,bcontent,bgroup,bstep,bindent) " + 
					"values(replyboard_seq.nextval,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			
			pstmt.setInt(4, Integer.parseInt(bgroup));//원글의 번호 유지
			pstmt.setInt(5, Integer.parseInt(bstep)+1);//순서 원글+1
			pstmt.setInt(6, Integer.parseInt(bindent)+1);//들여쓰기 원글+1
			
			pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	public void replyShape(String bgroup,String bstep) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=dataSource.getConnection();
			String sql="update replyboard " + 
					"set bstep=bstep+1 " + 
					"where bgroup=? and bstep>?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bgroup));
			pstmt.setInt(2, Integer.parseInt(bstep));
			
			pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
		
}
