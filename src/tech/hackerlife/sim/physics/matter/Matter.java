package tech.hackerlife.sim.physics.matter;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import tech.hackerlife.sim.Main;
import tech.hackerlife.sim.maths.Vector2D;

abstract class Matter {
	float mass;
	boolean constantAcceleration = false;
	Color color = Color.GRAY;
	Vector2D position;
	Vector2D velocity = new Vector2D(0,0);
	Vector2D acceleration = new Vector2D(0,0);
	Vector2D forcesSum = new Vector2D(0,0);
	ArrayList<Vector2D> forces = new ArrayList<Vector2D>();
	
	public Matter(float mass, Vector2D position, Vector2D velocity) {
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
	
	public void addForce(Vector2D force) {
		forces.add(force);
		forcesSum = forcesSum.add(force);
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
	
	public void update(JPanel panel) {
		// Update acceleration
		if (!constantAcceleration) {
			acceleration = forcesSum.divideScalar(mass);
		}
		
		// Update velocity
		velocity = velocity.add(acceleration.divideScalar(Main.realTimeUPS));
		
		// Update position
		position = position.add(velocity.divideScalar(Main.realTimeUPS));
		
		checkEdges(96, panel.getHeight()/10);
	}
	
	// TODO make collision for platforms
	public void checkCollision() {
		
	}
	
	/**
	 * @param width of physics panel
	 * @param height of physics panel
	 */
	public void checkEdges(float width, float height) {
		Vector2D normalForce = new Vector2D(0, forcesSum.Y()*-1);
		
		if (position.Y() > height && acceleration.Y() != 0) {
			position.setY(height);
			velocity.setY(0);
			this.addForce(normalForce);
		}
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