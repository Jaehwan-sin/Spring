package com.tech.sprj09.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor // 생성자 호출
@NoArgsConstructor // source - using fields 대신 하는 코드
// 16 getter,setter 작업, 생성자 작업 / 컨트롤+시프트+y 소문자
public class BoardDto {
	
	// 오라클 테이블 정보 긁어오기
	private int bid;
	private String bname;
	private String btitle;
	private String bcontent;
	private Timestamp bdate;
	private int bhit;
	private int bgroup;
	private int bstep;
	private int bindent;
	private String filesrc;
	
}
