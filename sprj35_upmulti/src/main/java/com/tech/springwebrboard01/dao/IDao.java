package com.tech.springwebrboard01.dao;

import java.util.ArrayList;

import com.tech.springwebrboard01.dto.BoardDto;
import com.tech.springwebrboard01.dto.RebrdimgDto;



public interface IDao {
	public ArrayList<BoardDto> list(int start,int end,String searchKeyword,String selNum);
	public void write(String bName,
			String bTitle, String bContent,String fName);
	public void imgwrite(int bid,String orgFile,String chgFile);
	public int selBid();
	
	
	public BoardDto contentView(String sbid);
	public ArrayList<RebrdimgDto> selectImg(String sbid);
	
	
	
	public void upHit(String sbid);
	public void delete(String bid);
	public void modify(String bid, String bName,
			String bTitle, String bContent);
	public BoardDto replyView(String sbid);
	
//	reply
	public void reply(String bid, String bName,
			String bTitle, String bContent,
			String bgroup, String bstep,
			String bindent);
	
	
//	replyShape
	public void replyShape(String bgroup,String bstep);
	
	public int selectBoardTotCount(String searchKeyword,String selNum);
	/*public int selectBoardTotCount2(String searchKeyword);
	public int selectBoardTotCount3(String searchKeyword);
	public int selectBoardTotCount0(String searchKeyword);*/
	
	
	
}
