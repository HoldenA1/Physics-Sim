package tech.hackerlife.sim.physics.matter;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import tech.hackerlife.sim.Main;
import tech.hackerlife.sim.maths.Vector2D;
import tech.hackerlife.sim.physics.Thing;

public abstract class Matter extends Thing {
	private float mass;
	private boolean constantAcceleration = false;
	private Vector2D velocity = new Vector2D(0,0);
	private Vector2D acceleration = new Vector2D(0,0);
	private Vector2D forcesSum = new Vector2D(0,0);
	private ArrayList<Vector2D> forces = new ArrayList<Vector2D>();
	
	public Matter(float mass, Vector2D position, Vector2D velocity, float width, float height) {
		super(position, width, height);
		this.mass = mass;
		if (velocity == null) {
			velocity = new Vector2D(0,0);
		}
		this.velocity = velocity;
	}
	
	public Matter withColor(Color color) {
		super.withColor(color);
		return this;
	}
	
	public Matter withAcceleration(Vector2D acceleration) {
		this.acceleration = acceleration;
		constantAcceleration = true;
		return this;
	}
	
	public void setVelocity(Vector2D velocity) {
		if (velocity == null) {
			velocity = new Vector2D(0,0);
		}
		this.velocity = velocity;
	}
	
	public void addForce(Vector2D force) {
		forces.add(force);
		forcesSum = forcesSum.add(force);
	}
	
	/**
	 * @return true if force was removed, false if it wasn't present
	 */
	public boolean removeForce(Vector2D force) {
		for (Vector2D f: forces) {
			if (force.equals(f)) {
				forces.remove(f);
				forcesSum = forcesSum.add(force.mult(-1));
				return true;
			}
		}
		return false;
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
	
	public void update(float scale, ArrayList<Thing> things) {
		// Update acceleration
		if (!constantAcceleration) {
			acceleration = forcesSum.divideScalar(mass);
		}
		
		// Update velocity
		velocity = velocity.add(acceleration.divideScalar(Main.realTimeUPS));
		
		// Update position
		position = position.add(velocity.divideScalar(Main.realTimeUPS));
		
		for (Thing t: things) {
			if (checkCollision(t)) {
				moveTo(t.getPointOnEdge(position.add(t.getPosition().mult(-1)).dir()));
				setVelocity(null);
			}
		}
	}
	
	// TODO make collision for platforms
	public abstract boolean checkCollision(Thing thing);
	
	public Vector2D getVelocity() {
		return velocity;
	}
	
	public Vector2D getAcceleration() {
		return acceleration;
	}
	
	public float getMass() {
		return mass;
	}
	
}