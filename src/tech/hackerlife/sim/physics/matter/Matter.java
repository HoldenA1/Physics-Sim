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
	private ArrayList<Vector2D> constantForces = new ArrayList<Vector2D>();
	private float elasticity = 0;
	
	public Matter(float mass, Vector2D position, Vector2D velocity, float width, float height) {
		super(position, width, height);
		this.mass = mass;
		if (velocity == null) {
			velocity = new Vector2D(0,0);
		}
		this.velocity = velocity;
	}
	
	public Matter(float mass, Vector2D position, Vector2D velocity, float radius) {
		super(position, radius);
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
	
	public void addConstantForce(Vector2D force) {
		constantForces.add(force);
	}
	
	/**
	 * @return true if force was removed, false if it wasn't present
	 */
	public boolean removeConstantForce(Vector2D force) {
		for (Vector2D f: constantForces) {
			if (force.equals(f)) {
				constantForces.remove(f);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param elasticity goes from 0 to 1. 1 is completely elastic and 0 is completely inelastic
	 */
	public void setElasticity(float elasticity) {
		this.elasticity = elasticity;
	}
	
	public void addForce(Vector2D force) {
		forcesSum = forcesSum.add(force);
	}
	
	public void drawForces(Graphics g, float scale) {
		for (Vector2D force: constantForces) {
			force.drawVector(g, position, scale, Color.YELLOW);
		}
	}
	
	public void update(float scale, ArrayList<Thing> things) {
		// Gets the net force on the object
		for (Vector2D f: constantForces) {
			forcesSum = forcesSum.add(f);
		}
		
		// Update acceleration
		if (!constantAcceleration) {
			acceleration = forcesSum.divideScalar(mass);
		}
		
		// Update velocity
		velocity = velocity.add(acceleration.divideScalar(Main.realTimeUPS));
		
		// Check if the object has collided with any other things and responds accordingly
		checkCollisions(things);
		
		// Update position
		position = position.add(velocity.divideScalar(Main.realTimeUPS));
		
		// Resets the net force to zero (it is summed every time)
		forcesSum = new Vector2D(0,0);
	}
	
	public void checkCollisions(ArrayList<Thing> things) {
		for (Thing t: things) {
			if (!this.equals(t)) {
				if (getArea().intersects(t.getArea().getBounds2D())) {
					setVelocity(null);
				}
			}
		}
	}
	
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