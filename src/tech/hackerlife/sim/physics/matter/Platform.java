package tech.hackerlife.sim.physics.matter;

import java.awt.Color;
import java.awt.Graphics;
import tech.hackerlife.sim.maths.Vector2D;

public class Platform extends Thing {
	float theta = 0;
	float width, height;
	Color color = Color.BLACK;
	
	/**
	 * All parameters are in meters
	 */
	public Platform(Vector2D pos, float width, float height) {
		super(pos);
		this.width = width;
		this.height = height;
	}
	
	public Platform withColor(Color color) {
		super.withColor(color);
		return this;
	}
	
//	/**
//	 * @param theta is measured in degrees
//	 */
//	public Platform withRotation(float theta) {
//		this.theta = theta;
//		return this;
//	}
	
	public void draw(Graphics g, float scale) {
		// Translates from world-space to screen-space
		Vector2D scaledPos = position.mult(scale);
		int scaledWidth = (int) (width * scale);
		int scaleHeight = (int) (height * scale);
		
		// Draws object
		g.setColor(color);
		g.fillRect((int) scaledPos.X(), (int) scaledPos.Y(), scaledWidth, scaleHeight);
	}
}