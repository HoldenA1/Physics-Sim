package tech.hackerlife.sim.physics;

import java.awt.Graphics;

import tech.hackerlife.sim.Main;
import tech.hackerlife.sim.maths.Vector2D;

public class Block extends Matter {
	int width, height;
	
	/**
	 * @param mass In kilograms
	 * @param position In meters
	 * @param velocity In meters per second
	 * @param acceleration In meters per second squared
	 * @param width In meters
	 * @param height In meters
	 */
	public Block(double mass, Vector2D position, Vector2D velocity, Vector2D acceleration, int width, int height) {
		super(mass, position, velocity, acceleration);
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(Graphics g) {
		// Translates from world-space to screen-space
		int x = (int) (position.X() * Main.SCALE);
		int y = (int) (position.Y() * Main.SCALE);
		int w = (int) (width * Main.SCALE);
		int h = (int) (height * Main.SCALE);
		
		// Draws object
		g.fillRect(x, y, w, h);
	}
	
}
