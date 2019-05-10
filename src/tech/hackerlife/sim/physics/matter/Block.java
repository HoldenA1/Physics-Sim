package tech.hackerlife.sim.physics.matter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import tech.hackerlife.math.Vector2f;

public class Block extends Matter {
	private static int numberOfBlocks = 0;
	private float width, height;
	
	/**
	 * @param mass In kilograms
	 * @param position In meters
	 * @param velocity In meters per second
	 * @param width In meters
	 * @param height In meters
	 */
	public Block(float mass, Vector2f position, Vector2f velocity, float width, float height) {
		super(mass, position, velocity);
		this.width = width;
		this.height = height;
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
		Point scaledPos = position.scale(scale);
		int scaledWidth = (int) (width * scale);
		int scaleHeight = (int) (height * scale);
	
		// Translates the center of the block to pos
		int x = scaledPos.x - (scaledWidth >> 1);
		int y = scaledPos.y - (scaleHeight >> 1);
		
		// Draws object
		g.setColor(color);
		g.fillRect(x, y, scaledWidth, scaleHeight);
	}

	@Override
	public Area getArea() {
		return new Area(new Rectangle2D.Float(position.X()-(width*0.5f), position.Y()-(height*0.5f), width, height));
	}
	
}
