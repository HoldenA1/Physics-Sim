package tech.hackerlife.sim.physics;

import java.awt.Graphics;
import tech.hackerlife.sim.maths.Vector2D;

abstract class Matter {
	double mass;
	Vector2D position;
	Vector2D velocity;
	Vector2D acceleration;
	
	public Matter(double mass, Vector2D position, Vector2D velocity, Vector2D acceleration) {
		this.mass = mass;
		this.position = position;
		this.velocity = velocity;
		this.acceleration = acceleration;
	}
	
	public void moveTo(Vector2D newPos) {
		position = newPos;
	}
	
	public void moveTo(float x, float y) {
		position.setX(x);
		position.setY(y);
	}
	
	public abstract void draw(Graphics g);
	
	public abstract void update();
}