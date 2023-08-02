package com.tech.sprj11p.dao;

import java.util.ArrayList;

import com.tech.sprj11p.dto.PizzaDto;

public interface IDao {

	public ArrayList<PizzaDto> pzlist();

	public void write(String pzname, String pzsubj, String pzcontent);

	public PizzaDto PizzaContentView(String pzid);

	public void delete(String pzid);

	public void upHit(String pzid);

	public void PizzaModify(String pzid, String pzname, String pzsubj, String pzcontent);

	public PizzaDto PizzaReplyView(String pzid);

	public void PizzaReply(String pzid, String pzname, String pzsubj, String pzcontent, String pzgroup, String pzstep,
			String pzintent);

	public void replyShape(String pzgroup, String pzstep);
	
	
}
