package com.tech.sprj11p.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tech.sprj11p.dto.PizzaDto;

public class PizzaDao {
			
			DataSource dataSource;
	
			public PizzaDao() {
				System.out.println("PizzaDao 신호");
				
				try {
					Context context = new InitialContext();
					dataSource = (DataSource) context.lookup("java:comp/env/jdbc/springxe");
					System.out.println("DB접속");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			public ArrayList<PizzaDto> list() {
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				ArrayList<PizzaDto> dtos = new ArrayList<PizzaDto>();
				
				try {
					con = dataSource.getConnection();
					String sql = "select pzid,pzname,pzsubj,pzcontent,pzdate,pzhit,pzgroup,pzstep,pzintent from pz_board order by pzgroup desc,pzstep asc";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						int pzid = rs.getInt("pzid");
						String pzname = rs.getString("pzname");
						String pzsubj = rs.getString("pzsubj");
						String pzcontent = rs.getString("pzcontent");
						Timestamp pzdate = rs.getTimestamp("pzdate");
						int pzhit = rs.getInt("pzhit");
						int pzgroup = rs.getInt("pzgroup");
						int pzstep = rs.getInt("pzstep");
						int pzintent = rs.getInt("pzintent");
						
						PizzaDto dto = new PizzaDto(pzid, pzname, pzsubj, pzcontent, pzdate, pzhit, pzgroup, pzstep, pzintent);
						dtos.add(dto);
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
				return dtos;
			}

			public void write(String pzname, String pzsubj, String pzcontent) {
				
				Connection con = null;
				PreparedStatement pstmt = null;
				
				try {
					con = dataSource.getConnection();
					String sql = "insert into pz_board values(pz_board_seq.nextval,?,?,?,sysdate,0,pz_board_seq.currval,0,0)";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, pzname);
					pstmt.setString(2, pzsubj);
					pstmt.setString(3, pzcontent);
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

			public PizzaDto PizzaContentView(String pzaid) {
				
				PizzaDto dto = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = dataSource.getConnection();
					String sql = "select pzid,pzname,pzsubj,pzcontent,pzdate,pzhit,pzgroup,pzstep,pzintent from pz_board where pzid=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(pzaid));
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						int pzid = rs.getInt("pzid");
						String pzname = rs.getString("pzname");
						String pzsubj = rs.getString("pzsubj");
						String pzcontent = rs.getString("pzcontent");
						Timestamp pzdate = rs.getTimestamp("pzdate");
						int pzhit = rs.getInt("pzhit");
						int pzgroup = rs.getInt("pzgroup");
						int pzstep = rs.getInt("pzstep");
						int pzintent = rs.getInt("pzintent");
						
						dto = new PizzaDto(pzid, pzname, pzsubj, pzcontent, pzdate, pzhit, pzgroup, pzstep, pzintent);
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

			public void delete(String pzid) {
				
				Connection con = null;
				PreparedStatement pstmt = null;
				
				try {
					con = dataSource.getConnection();
					String sql = "delete from pz_board where pzid=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, pzid);
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

			public void PizzaModify(String pzid, String pzname, String pzsubj, String pzcontent) {
				
				Connection con = null;
				PreparedStatement pstmt = null;
				
				try {
					con = dataSource.getConnection();
					String sql = "update pz_board set pzname=?,  pzsubj=?,  pzcontent=? where pzid=? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, pzname);
					pstmt.setString(2, pzsubj);
					pstmt.setString(3, pzcontent);
					pstmt.setString(4, pzid);
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

			public PizzaDto PizzaReplyView(String pzaid) {
				
				PizzaDto dto = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = dataSource.getConnection();
					String sql = "select pzid,pzname,pzsubj,pzcontent,pzdate,pzhit,pzgroup,pzstep,pzintent from pz_board where pzid=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(pzaid));
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						int pzid = rs.getInt("pzid");
						String pzname = rs.getString("pzname");
						String pzsubj = rs.getString("pzsubj");
						String pzcontent = rs.getString("pzcontent");
						Timestamp pzdate = rs.getTimestamp("pzdate");
						int pzhit = rs.getInt("pzhit");
						int pzgroup = rs.getInt("pzgroup");
						int pzstep = rs.getInt("pzstep");
						int pzintent = rs.getInt("pzintent");
						
						dto = new PizzaDto(pzid, pzname, pzsubj, pzcontent, pzdate, pzhit, pzgroup, pzstep, pzintent);
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

			public void PizzaReply(String pzid, String pzname, String pzsubj, String pzcontent, String pzgroup,
					String pzstep, String pzintent) {
				
				replyShape(pzgroup,pzstep);
				
				Connection con = null;
				PreparedStatement pstmt = null;
				
				try {
					con = dataSource.getConnection();
					String sql = "insert into pz_board(pzid,pzname,pzsubj,pzcontent,pzgroup,pzstep,pzintent)"
							+ " values(pz_board_seq.nextval,?,?,?,?,?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, pzname);
					pstmt.setString(2, pzsubj);
					pstmt.setString(3, pzcontent);
					pstmt.setInt(4, Integer.parseInt(pzgroup));
					pstmt.setInt(5, Integer.parseInt(pzstep));
					pstmt.setInt(6, Integer.parseInt(pzintent));
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

			private void replyShape(String pzgroup, String pzstep) {
				
				Connection con = null;
				PreparedStatement pstmt = null;
				
				try {
					con = dataSource.getConnection();
					System.out.println("sql문 전");
					String sql = "update pz_board set pzstep=pzstep+1  where pzgroup=? and pzstep>? ";
					System.out.println("sql문 후");
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(pzgroup));
					pstmt.setInt(2, Integer.parseInt(pzstep));
					System.out.println("업데이트 전");
					pstmt.executeUpdate();
					System.out.println("업데이트 후");
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
}
