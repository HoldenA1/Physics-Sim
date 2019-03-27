package tech.hackerlife.sim.physics.matter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import tech.hackerlife.math.Vector2f;

public class Ball extends Matter {
	private static int numberOfBalls = 0;
	/**
	 * @param mass In kilograms
	 * @param position In meters
	 * @param velocity In meters per second (put null for 0)
	 * @param width In meters
	 * @param height In meters
	 */
	public Ball(float mass, Vector2f position, Vector2f velocity, float radius) {
		super(mass, position, velocity, radius);
		numberOfBalls++;
		name = "Ball " + Integer.toString(numberOfBalls);
	}
	
	public Ball withAcceleration(Vector2f acceleraton) {
		super.withAcceleration(acceleraton);
		return this;
	}
	
	public Ball withColor(Color color) {
		super.withColor(color);
		return this;
	}
	
	public Ball withName(String name) {
		super.withName(name);
		return this;
	}

	public void draw(Graphics g, float scale) {
		// Translates from world-space to screen-space
		Vector2f scaledPos = position.mult(scale);
		int scaledRad = (int) (radius * scale);
	
		// Translates the center of the block to pos
		int x = (int) (scaledPos.X() - scaledRad);
		int y = (int) (scaledPos.Y() - scaledRad);
		
		// Draws object
		g.setColor(color);
		g.fillOval(x, y, 2*scaledRad, 2*scaledRad);
	}

	/**
	 * @param theta in radians
	 */
	public float getDistFromEdge(double theta) {
		return radius;
	}
	
	public Area getArea() {
		return new Area(new Ellipse2D.Float(position.X()-radius, position.Y()-radius, 2*radius, 2*radius));
	}
	
}
