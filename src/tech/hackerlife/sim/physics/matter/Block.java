package tech.hackerlife.sim.physics.matter;

import java.awt.Color;
import java.awt.Graphics;
import tech.hackerlife.math.Vector2f;

public class Block extends Matter {
	private static int numberOfBlocks = 0;
	/**
	 * @param mass In kilograms
	 * @param position In meters
	 * @param velocity In meters per second (put null for 0)
	 * @param width In meters
	 * @param height In meters
	 */
	public Block(float mass, Vector2f position, Vector2f velocity, float width, float height) {
		super(mass, position, velocity, width, height);
		numberOfBlocks++;
		name = "Block " + Integer.toString(numberOfBlocks);
	}
	
	public Block withAcceleration(Vector2f acceleraton) {
		super.withAcceleration(acceleraton);
		return this;
	}
	
	public Block withColor(Color color) {
		super.withColor(color);
		return this;
	}
	
	public Block withName(String name) {
		super.withName(name);
		return this;
	}

	public void draw(Graphics g, float scale) {
		// Translates from world-space to screen-space
		Vector2f scaledPos = position.mult(scale);
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
