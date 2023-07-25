package com.tech.ex;

public class Calculation {
	public void add(int i, int j) {
		System.out.println("add");
		System.out.println("first+second : "+(i+j));
	}
	public void sub(int i, int j) {
		System.out.println("sub");
		System.out.println("first-second : "+(i-j));
	}
	public void multi(int i, int j) {
		System.out.println("multi");
		System.out.println("first*second : "+(i*j));
	}
	public void div(int i, int j) {
		System.out.println("div");
		System.out.println("first/second : "+(i/j));
	}
}
