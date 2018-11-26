package tech.hackerlife.sim.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import tech.hackerlife.sim.maths.Trig;
import tech.hackerlife.sim.maths.Vector2D;

public abstract class Thing {
	protected Color color = Color.GRAY;
	protected Vector2D position;
	protected float width, height;
	
	public Thing(Vector2D position, float width, float height) {
		this.position = position;
		this.width = width;
		this.height = height;
	}
	
	public Thing withColor(Color color) {
		this.color = color;
		return this;
	}
	
	public void moveTo(Vector2D newPos) {
		position = newPos;
	}
	
	public abstract void update(float scale, ArrayList<Thing> things);
	
	public abstract void draw(Graphics g, float scale);
	
	public Vector2D getPosition() {
		return position;
	}
	
	/**
	 * @param theta in radians
	 */
	public float getDistFromEdge(float theta) {
		float dist;
		float A = width / 2, B = height / 2;
		
		float refAngle = Trig.getRefAngle(theta);

		dist = (float) Math.min(A / Math.cos(refAngle), B / Math.sin(refAngle));
	
		return dist;
	}
	
	/**
	 * @param theta in radians
	 */
	public Vector2D getPointOnEdge(float theta) {
		float A = width / 2, B = height / 2;
		float refAngle = Trig.getRefAngle(theta);
		float dist = (float) Math.min(A / Math.cos(refAngle), B / Math.sin(refAngle));
		
		return new Vector2D((double)theta, dist).add(position);
	}

}
