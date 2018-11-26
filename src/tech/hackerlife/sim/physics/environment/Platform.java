package tech.hackerlife.sim.physics.environment;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import tech.hackerlife.sim.maths.Vector2D;
import tech.hackerlife.sim.physics.Thing;

public class Platform extends Thing {
	/**
	 * All parameters are in meters
	 */
	public Platform(Vector2D pos, float width, float height) {
		super(pos, width, height);
	}
	
	public Platform withColor(Color color) {
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

	public void update(float scale, ArrayList<Thing> things) {
	}
	
}