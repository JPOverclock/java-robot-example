package com.jpoverclock.robot.game;

public class Robot {
	
	private static final int ROTATION_ENERGY = 2;
	private static final int FORWARD_ENERGY = 4;
	private static final int WALL_IMPACT_ENERGY = 1;
	
	private static final int SPIKE_DAMAGE_ENERGY = 15;
	private static final int ENERGY_PICKUP_RECOVERY = 40;
	
	private Position position;
	private Direction direction;
	
	private World world;
	
	private int energy;
	private int maxEnergy;
	
	public Robot(Position position, Direction direction, World world, int energy) {
		this.position = position;
		this.direction = direction;
		
		this.world = world;
		
		this.energy = energy;
		this.maxEnergy = energy;
	}
	
	public Position position() {
		return position;
	}
	
	public Direction direction() {
		return direction;
	}
	
	public int energy() {
		return energy;
	}
	
	public int maxEnergy() {
		return maxEnergy;
	}
	
	public void rotateLeft() {
		switch (direction) {
		case UP: direction = Direction.LEFT; break;
		case LEFT: direction = Direction.DOWN; break;
		case DOWN: direction = Direction.RIGHT; break;
		case RIGHT: direction = Direction.UP; break;
		}
		
		depleteEnergy(ROTATION_ENERGY);
	}
	
	public void rotateRight() {
		switch (direction) {
		case UP: direction = Direction.RIGHT; break;
		case RIGHT: direction = Direction.DOWN; break;
		case DOWN: direction = Direction.LEFT; break;
		case LEFT: direction = Direction.UP; break;
		}
		
		depleteEnergy(ROTATION_ENERGY);
	}
	
	public void forward() {
		if (canMoveForward()) {
			position.advance(direction.i(), direction.j());
			reactToEnvironment();
		} else {
			System.out.println("BUMP!");
			depleteEnergy(WALL_IMPACT_ENERGY);
		}
		
		depleteEnergy(FORWARD_ENERGY);
	}
	
	private void depleteEnergy(int energy) {
		this.energy -= energy;
		
		if (this.energy < 0) {
			throw new RobotDead();
		}
	}
	
	private void recoverEnergy(int energy) {
		this.energy += energy;
		
		if (this.energy > maxEnergy) {
			this.energy = maxEnergy;
		}
	}
	
	private boolean canMoveForward() {
		return world.objectAt(position.i() + direction.i(), position.j() + direction.j()) != WorldObject.WALL;
	}
	
	private void reactToEnvironment() {
		switch(world.objectAt(position.i(), position.j())) {
		case SPIKES:
			depleteEnergy(SPIKE_DAMAGE_ENERGY);
			System.out.println("OUCH!!!");
			break;
		case ENERGY:
			recoverEnergy(ENERGY_PICKUP_RECOVERY);
			System.out.println("Picked up energy!");
			break;
		case HOLE:
			depleteEnergy(energy);
			System.out.println("OH Noooooooooooooooo!!!!");
			break;
		case TREASURE:
			System.out.println("FOUND TREASURE!!!");
			break;
		default:
				break;
		}
	}
}
