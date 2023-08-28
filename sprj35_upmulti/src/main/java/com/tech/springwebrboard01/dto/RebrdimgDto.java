package com.tech.springwebrboard01.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;


//@Setter
//@Getter
public class RebrdimgDto {
	
	private int rebno;
	private int bid;
	private String reborgfile;
	private String rebchgfile;
	
	public RebrdimgDto() {
		
	}

	public RebrdimgDto(int rebno, int bid, String reborgfile, String rebchgfile) {

		this.rebno = rebno;
		this.bid = bid;
		this.reborgfile = reborgfile;
		this.rebchgfile = rebchgfile;
	}

	public int getRebno() {
		return rebno;
	}

	public void setRebno(int rebno) {
		this.rebno = rebno;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getReborgfile() {
		return reborgfile;
	}

	public void setReborgfile(String reborgfile) {
		this.reborgfile = reborgfile;
	}

	public String getRebchgfile() {
		return rebchgfile;
	}

	public void setRebchgfile(String rebchgfile) {
		this.rebchgfile = rebchgfile;
	}
	
	

}
