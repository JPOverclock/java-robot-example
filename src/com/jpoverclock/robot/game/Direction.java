package com.jpoverclock.robot.game;

public enum Direction {
	UP(-1, 0),
	DOWN(1, 0),
	LEFT(0, -1),
	RIGHT(0, 1);
	
	private int i;
	private int j;
	
    private Direction(int i, int j) {
		this.i = i;
		this.j = j;
	}
    
    public int i() {
    	return i; 
    }
    
    public int j() {
    	return j;
    }
}
