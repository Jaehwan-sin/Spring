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
	private int brd_id;
	private String brd_name;
	private String brd_title;
	private String brd_content;
	private Timestamp brd_date;
	private int brd_hit;
	private int brd_group;
	private int brd_step;
	private int brd_indent;

}
