package com.jpoverclock.robot;

import com.jpoverclock.robot.game.Direction;
import com.jpoverclock.robot.game.Position;
import com.jpoverclock.robot.game.Robot;
import com.jpoverclock.robot.game.World;

public class Main {
	
	private static void printGame(World world, Robot robot) {
		System.out.println("ENERGY = " + robot.energy() + "/" + robot.maxEnergy());
		
		switch(robot.direction()) {
		case UP: System.out.println("DIRECTION = ⬆️"); break;
		case DOWN: System.out.println("DIRECTION = ⬇️"); break;
		case LEFT: System.out.println("DIRECTION = ⬅️"); break;
		case RIGHT: System.out.println("DIRECTION = ➡️️"); break;
		}
		
		for (int i = 0; i < world.height(); i++) {
			for (int j = 0; j < world.width(); j++) {
				if (i == robot.position().i() && j == robot.position().j()) {
					// Draw the robot
					System.out.print("🤖");
				} else {
					switch (world.objectAt(i, j)) {
					case WALL: System.out.print("🟦"); break;
					case HOLE: System.out.print("⚫️"); break;
					case ENERGY: System.out.print("🔋"); break;
					case SPIKES: System.out.print("☠️"); break;
					case TREASURE: System.out.print("💎"); break;
					default: System.out.print("⬜️"); break;
					}
				}
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {

		// Load world map
		World world = new World(10, 10);
		
		String[] map = new String[] {
				"##########",
				"#    #####",
				"# ^#X#####",
				"# ^#O#####",
				"#      e##",
				"#### #####",
				"#### #####",
				"####  ####",
				"###     e#",
				"##########"
		};
		
		world.load(map);
		
		// Create a new robot
		Robot robot = new Robot(new Position(8, 3), Direction.UP, world, 100);
		
		/*
		 * Take that treasure!
		 * 
		 * The robot can only move forward using `robot.forward()` and turn left or right using `robot.rotateLeft()` and `robot.rotateRight()`
		 * You can print the full map and stats at any time by calling `printGame(world, robot)`
		 */
		printGame(world, robot);
	}
}
