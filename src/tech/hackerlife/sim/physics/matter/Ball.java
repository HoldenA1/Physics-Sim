package tech.hackerlife.sim.physics.matter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import tech.hackerlife.math.Vector2f;

public class Ball extends Matter {
	private static int numberOfBalls = 0;
	protected float radius;
	
	/**
	 * @param mass In kilograms
	 * @param position In meters
	 * @param velocity In meters per second
	 * @param radius In meters
	 */
	public Ball(float mass, float radius, Vector2f position, Vector2f velocity) {
		super("Ball " + Integer.toString(numberOfBalls+1), mass, position, velocity);
		this.radius = radius;
		numberOfBalls++;
	}
	
	@Override
	public Ball withName(String name) {
		super.withName(name);
		return this;
	}
	
	@Override
	public Ball withColor(Color color) {
		super.withColor(color);
		return this;
	}
	
	@Override
	public Ball withAcceleration(Vector2f acceleraton) {
		super.withAcceleration(acceleraton);
		return this;
	}

	public void draw(Graphics g, float scale) {
		// Translates from world-space to screen-space
		Point scaledPos = position.scale(scale);
		int scaledRad = (int) (radius * scale);
		int diam = 2 * scaledRad;
	
		// Translates the center of the block to pos
		int x = scaledPos.x - scaledRad;
		int y = scaledPos.y - scaledRad;
		
		// Draws object
		g.setColor(color);
		g.fillOval(x, y, diam, diam);
	}
	
}
