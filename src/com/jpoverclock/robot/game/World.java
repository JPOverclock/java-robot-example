package com.jpoverclock.robot.game;

public class World {
	
	private int width;
	private int height;
	
	private WorldObject[][] map;
	
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		
		map = new WorldObject[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				map[i][j] = WorldObject.EMPTY;
			}
		}
	}
	
	public int width() {
		return width;
	}
	
	public int height() {
		return height;
	}
	
	public WorldObject objectAt(int i, int j) {
		return map[i][j];
	}
	
	public void load(String[] lines) {
		for (int i = 0; i < Math.min(lines.length, height); i++) {
			for (int j = 0; j < Math.min(lines[i].length(), width); j++) {
				switch(lines[i].charAt(j)) {
				case '#': map[i][j] = WorldObject.WALL; break;
				case 'O': map[i][j] = WorldObject.HOLE; break;
				case '^': map[i][j] = WorldObject.SPIKES; break;
				case 'e': map[i][j] = WorldObject.ENERGY; break;
				case 'X': map[i][j] = WorldObject.TREASURE; break;
				default: break;
				}
			}
		}
	}
}
