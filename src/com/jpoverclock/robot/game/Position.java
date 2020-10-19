package com.jpoverclock.robot.game;

public class Position {
	private int i;
	private int j;
	
	public Position(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public int i() {
		return i;
	}
	
	public int j() {
		return j;
	}
	
	public void advance(int i, int j) {
		this.i += i;
		this.j += j;
	}
}
