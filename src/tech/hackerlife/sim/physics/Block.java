package tech.hackerlife.sim.physics;

import java.awt.Graphics;

import tech.hackerlife.sim.maths.Vector2D;

public class Block extends Matter {
	int width, height;
	
	public Block(double mass, Vector2D position, Vector2D velocity, Vector2D acceleration, int width, int height) {
		super(mass, position, velocity, acceleration);
		this.width = width;
		this.height = height;
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
}
