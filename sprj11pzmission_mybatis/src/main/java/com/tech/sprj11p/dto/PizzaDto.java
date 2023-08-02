package com.tech.sprj11p.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDto {
			
	private int pzid;
	private String pzname;
	private String pzsubj;
	private String pzcontent;
	private Timestamp pzdate;
	private int pzhit;
	private int pzgroup;
	private int pzstep;
	private int pzintent;
	
}
