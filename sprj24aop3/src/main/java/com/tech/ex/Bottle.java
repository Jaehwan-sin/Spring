package com.tech.ex;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class Bottle {
		private String name;
		private String kind;
		private int price;
		
		public void getBottle() {
			System.out.println(name+"님 "+kind+"술을 "+price+"원에 마셨다.");
		}
}
