package tech.hackerlife.sim.physics.environment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import tech.hackerlife.math.Vector2f;
import tech.hackerlife.sim.physics.Thing;

public class Platform extends Thing {
	private static int numberOfPlatforms = 0;
	private float width, height;
	
	/**
	 * All parameters are in meters
	 */
	public Platform(Vector2f position, float width, float height) {
		super(position);
		this.width = width;
		this.height = height;
		numberOfPlatforms++;
		name = "Platform " + Integer.toString(numberOfPlatforms);
	}
	
	@Override
	public Platform withColor(Color color) {
		super.withColor(color);
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

	public void update(ArrayList<Thing> things) {}

	public Area getArea() {
		return new Area(new Rectangle2D.Float(position.X()-(width*0.5f), position.Y()-(height*0.5f), width, height));
	}
	
}