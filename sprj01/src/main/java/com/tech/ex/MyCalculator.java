package com.tech.ex;

public class MyCalculator {
	private Calculation calculation;
	private int firstNum;
	private int secondNum;

	public MyCalculator() {
		// TODO Auto-generated constructor stub
	}

	public Calculation getCalculation() {
		return calculation;
	}

	public void setCalculation(Calculation calculation) {
		this.calculation = calculation;
	}

	public int getFirstNum() {
		return firstNum;
	}

	public void setFirstNum(int firstNum) {
		this.firstNum = firstNum;
	}

	public int getSecondNum() {
		return secondNum;
	}

	public void setSecondNum(int secondNum) {
		this.secondNum = secondNum;
	}

	public void add() {
		calculation.add(firstNum, secondNum);
	}

	public void sub() {
		calculation.sub(firstNum, secondNum);
	}

	public void multi() {
		calculation.multi(firstNum, secondNum);
	}

	public void div() {
		calculation.div(firstNum, secondNum);
	}

}
