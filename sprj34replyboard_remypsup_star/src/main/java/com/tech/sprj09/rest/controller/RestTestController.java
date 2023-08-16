package com.tech.sprj09.rest.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.sprj09.dto.DeptDto;
import com.tech.sprj09.dto.MemberDto;

@RestController
@RequestMapping("/test/*")
public class RestTestController {
		
		@RequestMapping("/hello")
		public String hello() {
			return "Hello REST";
		}
	
		/* JSON 형식으로 key,value 형식으로 화면 출력된다. */
		@RequestMapping("/member")
		public MemberDto member() {
			
			MemberDto dto = new MemberDto();
			dto.setId("hong");
			dto.setPwd("1234");
			dto.setName("홍길동");
			dto.setEmail("hong@naver.com");
			
			return dto;
		}
		
		@RequestMapping("/membersMap")
		public Map<Integer, MemberDto> members() {
			
			Map<Integer, MemberDto> map = new HashMap<Integer, MemberDto>();
			
			for (int i = 0; i < 10; i++) {
				MemberDto dto = new MemberDto();
				dto.setId("hong");
				dto.setPwd("1234");
				dto.setName("홍길동");
				dto.setEmail("hong@naver.com");
				map.put(i, dto); 
			}
			
			return map;
		}
		
		/* DB에서 데이터를 조회 리스트에 담아 결과를 뷰에 출력하기 */
		@RequestMapping("/deptlist")
		public List<DeptDto> deptlist() throws Exception {
			System.out.println("deptlist");
			// 데이터를 조회
			String sql = "select * from dept";
			List<DeptDto> list = new ArrayList<DeptDto>();
			// 드라이버 로드 
			Class.forName("oracle.jdbc.driver.OracleDriver");			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "123456";
			Connection con = DriverManager.getConnection(url,user,pw);
			// 실행
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				DeptDto d = new DeptDto();
				d.setDeptno(rs.getInt("deptno"));
				d.setDname(rs.getString("dname"));
				d.setLoc(rs.getString("loc"));
				list.add(d);
			}
			
			return list;
			
		}
}
