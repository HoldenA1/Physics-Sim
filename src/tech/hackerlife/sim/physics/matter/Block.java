package tech.hackerlife.sim.physics.matter;

import java.awt.Color;
import java.awt.Graphics;
import tech.hackerlife.sim.maths.Vector2D;

public class Block extends Matter {
	/**
	 * @param mass In kilograms
	 * @param position In meters
	 * @param velocity In meters per second (put null for 0)
	 * @param width In meters
	 * @param height In meters
	 */
	public Block(float mass, Vector2D position, Vector2D velocity, float width, float height) {
		super(mass, position, velocity, width, height);
	}
	
	public Block withAcceleration(Vector2D acceleraton) {
		super.withAcceleration(acceleraton);
		return this;
	}
	
	public Block withColor(Color color) {
		super.withColor(color);
		return this;
	}

	public void draw(Graphics g, float scale) {
		// Translates from world-space to screen-space
		Vector2D scaledPos = position.mult(scale);
		int scaledWidth = (int) (width * scale);
		int scaleHeight = (int) (height * scale);
	
		// Translates the center of the block to pos
		int x = (int) (scaledPos.X() - (0.5 * scaledWidth));
		int y = (int) (scaledPos.Y() - (0.5 * scaleHeight));
		
		// Draws object
		g.setColor(color);
		g.fillRect(x, y, scaledWidth, scaleHeight);
	}
	
}
