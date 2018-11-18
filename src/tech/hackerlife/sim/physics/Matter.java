package tech.hackerlife.sim.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import tech.hackerlife.sim.Main;
import tech.hackerlife.sim.maths.Vector2D;

abstract class Matter {
	float mass;
	Vector2D position;
	Vector2D velocity;
	Vector2D acceleration;
	Vector2D forcesSum = new Vector2D(0,0);
	ArrayList<Vector2D> forces = new ArrayList<Vector2D>();
	
	public Matter(float mass, Vector2D position, Vector2D velocity, Vector2D acceleration) {
		if (position == null) {
			position = new Vector2D(0,0);
		}
		if (velocity == null) {
			velocity = new Vector2D(0,0);
		}
		if (acceleration == null) {
			acceleration = new Vector2D(0,0);
		}
		
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
	
	public void addForce(Vector2D force) {
		forces.add(force);
		forcesSum.add(force);
	}
	
	public void drawForces(Graphics g, float scale) {
		Color temp = g.getColor();
		g.setColor(Color.YELLOW);
		
		for (Vector2D force: forces) {
			force.drawVector(g, position, scale);
		}
		g.setColor(temp);
	}
	
	public void drawVelocity(Graphics g, float scale) {
		Color temp = g.getColor();
		g.setColor(Color.GREEN);
		
		velocity.drawVector(g, position, scale);
		
		g.setColor(temp);
	}
	
	public void update() {
		// Update acceleration
		acceleration = Vector2D.divide(forcesSum, mass);
		
		// Update velocity
		velocity.add(Vector2D.divide(acceleration, Main.realTimeUPS));
		
		// Update position
		position.add(Vector2D.divide(velocity, Main.realTimeUPS));
	}
	
	public Vector2D getPosition() {
		return position;
	}
	
	public Vector2D getVelocity() {
		return velocity;
	}
	
	public Vector2D getAcceleration() {
		return acceleration;
	}
	
	public abstract void draw(Graphics g, float scale);
	
}