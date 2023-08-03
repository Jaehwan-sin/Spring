package com.tech.sprj09.dao;

import java.util.ArrayList;

import com.tech.sprj09.dto.BoardDto;

/*5번째 인터페이스 생성*/
public interface IDao {

	public ArrayList<BoardDto> list(int start, int end); /* 7 list 메소드 생성 #10 start,end 값 받기 */

	public void write(String bname, String btitle, String bcontent);
	
	public BoardDto contentView(String bid);
	
	public void modify(String bid, String bname, String btitle, String bcontent);
	
	public void upHit(String sbid);

	public void delete(String bid);

	public BoardDto replyView(String bid);

	public void reply(String bid, String bname, String btitle, String bcontent, String bgroup, String bstep,
			String bindent);

	public void replyShape(String bgroup, String bstep);

	public int selectBoardTotCount();
}
