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
			
			public ArrayList<BoardDto> list() { // 15
				
						Connection con = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						ArrayList<BoardDto> dtos = new ArrayList<BoardDto>(); // 16 BoardDto 작업
						
						try {
							con = dataSource.getConnection(); // DB 연결 코드를 con에 담는다. 
							String sql = "select * from replyboard2 order by brd_group desc,brd_step asc"; // 64 그룹별로 보이게 하는 sql문
							pstmt = con.prepareStatement(sql);
							rs = pstmt.executeQuery();
							// 19 dtos에 글 전체를 담아주기
							while (rs.next()) {
									int brd_id = rs.getInt("brd_id");
									String brd_name = rs.getString("brd_name");
									String brd_title = rs.getString("brd_title");
									String brd_content = rs.getString("brd_content");
									Timestamp brd_date = rs.getTimestamp("brd_date");
									int brd_hit = rs.getInt("brd_hit");
									int brd_group = rs.getInt("brd_group");
									int brd_step = rs.getInt("brd_step");
									int brd_indent = rs.getInt("brd_indent");
								// 20 생성자를 통해 BoardDto에 담아주기
									BoardDto dto = new BoardDto(brd_id, brd_name, brd_title, brd_content, brd_date, brd_hit, brd_group, brd_step, brd_indent);
								// 21 리스트에 담기
									dtos.add(dto);
							}
						} 	catch (SQLException e) {
							// TODO Auto-generated catch block
								e.printStackTrace();
						} finally {
							try {
								// 사용한 자원 회수
								if (rs!=null) rs.close();
								if (pstmt!=null) pstmt.close();
								if (con!=null) con.close();
							} catch (Exception e2) {
								// TODO: handle exception
							}
						}
						return dtos; // 18 리턴작업 ArrayList<BoardDto> 리턴 타입
						
			}

			public void write(String bname, String btitle, String bcontent) {
				// 29 DB 연결 후 insert 쿼리 실행
				Connection con = null;
				PreparedStatement pstmt = null;
				
				try {
					con = dataSource.getConnection();
					String sql = "insert into replyboard values(replyboard_seq.nextval,?,?,?,sysdate,0,replyboard_seq.currval,0,0)";  //  데이터 삽입 sql문
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, bname);
					pstmt.setString(2, btitle);
					pstmt.setString(3, bcontent);
					int rn = pstmt.executeUpdate();
					System.out.println("insert 갯수 : "+rn);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					try {
						// 사용한 자원 회수
						if (pstmt!=null) pstmt.close();
						if (con!=null) con.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				 
			}
			
			// 35
			public BoardDto contentView(String sbid) {
				
				// 50 조회수 증가, upHit 메소드 만들기
				upHit(sbid);
				
				// bid를 조건으로 DB에서 해당글 조회하기
				BoardDto dto = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = dataSource.getConnection();
					String sql = "select bid,bname,btitle,bcontent,bdate,bhit,bgroup,bstep,bindent from replyboard where bid=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(sbid));
					rs = pstmt.executeQuery();
					// rs를 dto에 담아주기
					if (rs.next()) {
						int bid = rs.getInt("bid");
						String bname = rs.getString("bname");
						String btitle = rs.getString("btitle");
						String bcontent = rs.getString("bcontent");
						Timestamp bdate = rs.getTimestamp("bdate");
						int bhit = rs.getInt("bhit");
						int bgroup = rs.getInt("bgroup");
						int bstep = rs.getInt("bstep");
						int bindent = rs.getInt("bindent");
						
						dto = new BoardDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block 
					e.printStackTrace();
				} finally {
					try {
						// 사용한 자원 회수
						if (rs!=null) rs.close();
						if (pstmt!=null) pstmt.close();
						if (con!=null) con.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				return dto;
			}
			
			// 51 조회수 증가 메소드 작업
			public void upHit(String sbid) {
				
				Connection con = null;
				PreparedStatement pstmt = null;
				
				try {
					con = dataSource.getConnection();
					String sql = "update replyboard set bhit=bhit+1 where bid=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(sbid));
					pstmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						// 사용한 자원 회수
						if (pstmt!=null) pstmt.close();
						if (con!=null) con.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				
			}
			
			/* 45 BModifyService에서 보낸 값 받고 DB작업 */
			public void modify(String sbid, String bname, String btitle, String bcontent) {
				
						Connection con = null;
						PreparedStatement pstmt = null;
						
						try {
							con = dataSource.getConnection();
							String sql = "update replyboard set bname=?, btitle=?, bcontent=? where bid=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, bname);
							pstmt.setString(2, btitle);
							pstmt.setString(3, bcontent);
							pstmt.setInt(4, Integer.parseInt(sbid));
							pstmt.executeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							try {
								// 사용한 자원 회수
								if (pstmt!=null) pstmt.close();
								if (con!=null) con.close();
							} catch (Exception e2) {
								// TODO: handle exception
							}
						}
				
			}
			
			/* 49 BDeleteService에서 받은 값을 DB작업 */
			public void delete(String sbid) {
				
				Connection con = null;
				PreparedStatement pstmt = null;
				
				try {
					con = dataSource.getConnection();
					String sql = "delete replyboard where bid=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(sbid));
					pstmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						// 사용한 자원 회수
						if (pstmt!=null) pstmt.close();
						if (con!=null) con.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				
			}
			
			/* 57 BReplyViewService에서 받은 값을 DB작업 */
			public BoardDto replyView(String sbid) {
					
				BoardDto dto = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = dataSource.getConnection();
					String sql = "select bid,bname,btitle,bcontent,bdate,bhit,bgroup,bstep,bindent from replyboard where bid=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(sbid));
					rs = pstmt.executeQuery();
					// rs를 dto에 담아주기
					if (rs.next()) {
						int bid = rs.getInt("bid");
						String bname = rs.getString("bname");
						String btitle = rs.getString("btitle");
						String bcontent = rs.getString("bcontent");
						Timestamp bdate = rs.getTimestamp("bdate");
						int bhit = rs.getInt("bhit");
						int bgroup = rs.getInt("bgroup");
						int bstep = rs.getInt("bstep");
						int bindent = rs.getInt("bindent");
						
						dto = new BoardDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block 
					e.printStackTrace();
				} finally {
					try {
						// 사용한 자원 회수
						if (rs!=null) rs.close();
						if (pstmt!=null) pstmt.close();
						if (con!=null) con.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
					return dto;

			}
			
			/* 63 reply DB작업 */
			public void reply(String bid, String bname, String btitle, String bcontent, String bgroup, String bstep,String bindent) {
				
				/* 65 step 댓글을 달려는 원글의 스탭번호보다 큰 스탭이 있다면 1을 더해준다. */
				replyShape(bgroup,bstep);
				
				Connection con = null;
				PreparedStatement pstmt = null;
				
				try {
					con = dataSource.getConnection();
					String sql = "insert into replyboard(bid,bname,btitle,bcontent,bgroup,bstep,bindent) values(replyboard_seq.nextval,?,?,?,?,?,?)";  
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, bname);
					pstmt.setString(2, btitle);
					pstmt.setString(3, bcontent);
					pstmt.setInt(4, Integer.parseInt(bgroup));// 원글의 번호 유지
					pstmt.setInt(5, Integer.parseInt(bstep)+1);// 순서 원글+1
					pstmt.setInt(6, Integer.parseInt(bindent)+1);// 들여쓰기 원글+1
					pstmt.executeUpdate();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					try {
						// 사용한 자원 회수
						if (pstmt!=null) pstmt.close();
						if (con!=null) con.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				
			}
			
			/* 66 step DB 작업 */
			private void replyShape(String bgroup, String bstep) {
				
				Connection con = null;
				PreparedStatement pstmt = null;
				
				try {
					con = dataSource.getConnection();
					String sql = "update replyboard set bstep=bstep+1 where bgroup=? and bstep>?";  
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(bgroup));
					pstmt.setInt(2, Integer.parseInt(bstep));
					pstmt.executeUpdate();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					try {
						// 사용한 자원 회수
						if (pstmt!=null) pstmt.close();
						if (con!=null) con.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				
			}

			
}
