package com.tech.sprj09.dao;

import java.util.ArrayList;

import com.tech.sprj09.dto.BoardDto;

/*5번째 인터페이스 생성*/
public interface IDao {

	public ArrayList<BoardDto> list(int start, int end, String sk, String selNum); /* 7 list 메소드 생성 */

	public void write(String brd_name, String brd_title, String brd_content);
	
	public BoardDto contentView(String brd_id);
	
	public void modify(String brd_id, String brd_name, String brd_title, String brd_content);
	
	public void upHit(String sbid);

	public void delete(String brd_id);

	public BoardDto replyView(String brd_id);

	public void reply(String brd_id, String brd_name, String brd_title, String brd_content, String brd_group, String brd_step,
			String brd_indent);

	public void replyShape(String bgroup, String bstep);

//	public int selectBoardTotCount();

	public int selectBoardTotCount1(String sk);
	
	public int selectBoardTotCount2(String sk);
	
	public int selectBoardTotCount3(String sk);
	
	public int selectBoardTotCount4(String sk);
}
