package tech.hackerlife.sim.physics.environment;

import java.awt.Color;
import java.awt.Graphics;
import tech.hackerlife.sim.maths.Vector2D;

public class Platform {
	Vector2D pos;
	float theta = 0;
	int width, height;
	Color color = Color.BLACK;
	
	/**
	 * All parameters are in meters
	 */
	public Platform(Vector2D pos, int width, int height) {
		this.pos = pos;
		this.width = width;
		this.height = height;
	}
	
	public Platform withColor(Color color) {
		this.color = color;
		return this;
	}
	
	/**
	 * Do not use yet!!
	 * @param theta is measured in degrees
	 */
	public Platform withRotation(float theta) {
		this.theta = theta;
		return this;
	}
	
	public void draw(Graphics g, float scale) {
		// Translates from world-space to screen-space
		Vector2D scaledPos = Vector2D.mult(pos, scale);
		int scaledWidth = (int) (width * scale);
		int scaleHeight = (int) (height * scale);
		
		// Draws object
		g.setColor(color);
		g.fillRect((int) scaledPos.X(), (int) scaledPos.Y(), scaledWidth, scaleHeight);
	}
}