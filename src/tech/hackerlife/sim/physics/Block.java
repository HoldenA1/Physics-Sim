package tech.hackerlife.sim.physics;

import java.awt.Graphics;
import tech.hackerlife.sim.maths.Vector2D;

public class Block extends Matter {
	int width, height;
	
	/**
	 * putting null for a vector means zero
	 * @param mass In kilograms
	 * @param position In meters
	 * @param velocity In meters per second
	 * @param acceleration In meters per second squared
	 * @param width In meters
	 * @param height In meters
	 */
	public Block(float mass, Vector2D position, Vector2D velocity, Vector2D acceleration, int width, int height) {
		super(mass, position, velocity, acceleration);
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(Graphics g, float scale) {
		
//		drawForces(g, scale);
		drawVelocity(g, scale);
		
		// Translates from world-space to screen-space
		Vector2D scaledPos = Vector2D.mult(position, scale);
		int scaledWidth = (int) (width * scale);
		int scaleHeight = (int) (height * scale);
	
		// Translates the center of the block to pos
		int x = (int) (scaledPos.X() - (0.5 * scaledWidth));
		int y = (int) (scaledPos.Y() - (0.5 * scaleHeight));
		
		// Draws object
		g.fillRect(x, y, scaledWidth, scaleHeight);
	}
	
}
