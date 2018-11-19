package tech.hackerlife.sim.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import tech.hackerlife.sim.Main;
import tech.hackerlife.sim.maths.Vector2D;

abstract class Matter {
	float mass;
	boolean constantAcceleration = false;
	Color color = Color.GRAY;
	Vector2D position;
	Vector2D velocity;
	Vector2D acceleration = new Vector2D(0,0);
	Vector2D forcesSum = new Vector2D(0,0);
	ArrayList<Vector2D> forces = new ArrayList<Vector2D>();
	
	public Matter(float mass, Vector2D position, Vector2D velocity) {
		if (velocity == null) {
			velocity = new Vector2D(0,0);
		}
		
		this.mass = mass;
		this.position = position;
		this.velocity = velocity;
	}
	
	public Matter withAcceleration(Vector2D acceleration) {
		this.acceleration = acceleration;
		constantAcceleration = true;
		return this;
	}
	
	public Matter withColor(Color color) {
		this.color = color;
		return this;
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
	
	public void drawAcceleration(Graphics g, float scale) {
		Color temp = g.getColor();
		g.setColor(Color.RED);
		
		acceleration.drawVector(g, position, scale);
		
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
		if (!constantAcceleration) {
			acceleration = Vector2D.divide(forcesSum, mass);
		}
		
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