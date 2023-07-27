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
	
			DataSource dataSource; // 12 DataSource 선언, 서버 폴더에 context.xml 내용 추가 필요
			
			/* 10 생성자 만들기*/
			public BoardDao() {
				System.out.println("BoardDao 신호");
				/* 13 생성자 호출을 이용해서 DB 접속 */
				try {
						Context context = new InitialContext();
						dataSource = (DataSource) context.lookup("java:comp/env/jdbc/springxe"); // context.xml에 DB접속 코드를 이용해 실행하게 하는 코드
						System.out.println("DB접속");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			public ArrayList<BoardDto> list() { // 15
				
						Connection con = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						ArrayList<BoardDto> dtos = new ArrayList<BoardDto>(); // 16 BoardDto 작업
						
						try {
							con = dataSource.getConnection(); // DB 연결 코드를 con에 담는다. 
							String sql = "select bid,bname,btitle,bcontent,bdate,bhit,bgroup,bstep,bindent from replyboard"; // 17 데이터 불러오는 sql문
							pstmt = con.prepareStatement(sql);
							rs = pstmt.executeQuery();
							// 19 dtos에 글 전체를 담아주기
							while (rs.next()) {
									int bid = rs.getInt("bid");
									String bname = rs.getString("bname");
									String btitle = rs.getString("btitle");
									String bcontent = rs.getString("bcontent");
									Timestamp bdate = rs.getTimestamp("bdate");
									int bhit = rs.getInt("bhit");
									int bgroup = rs.getInt("bgroup");
									int bstep = rs.getInt("bstep");
									int bindent = rs.getInt("bindent");
								// 20 생성자를 통해 BoardDto에 담아주기
									BoardDto dto = new BoardDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
								// 21 리스트에 담기
									dtos.add(dto);
							}
						} 	catch (SQLException e) {
							// TODO Auto-generated catch block
								e.printStackTrace();
						}
						return dtos; // 18 리턴작업 ArrayList<BoardDto> 리턴 타입
						
			}
}
