package tech.hackerlife.sim.physics.matter;

import java.awt.*;
import java.util.ArrayList;
import tech.hackerlife.math.Vector2f;
import tech.hackerlife.sim.display.Util;

/**
 * Anything that has mass is matter.
 * @author holden
 *
 */
public abstract class Matter {
	protected Color color = Color.GRAY;
	private String name;
	private float mass;
	protected Vector2f position;
	private Vector2f velocity = Vector2f.zero();
	private Vector2f acceleration = Vector2f.zero();
	private Vector2f forcesSum = Vector2f.zero();
	private ArrayList<Vector2f> constantForces = new ArrayList<Vector2f>();
	private ArrayList<Vector2f> forces = new ArrayList<Vector2f>();
	
	public Matter(String name, float mass, Vector2f position, Vector2f velocity) {
		this.name = name;
		this.position = position;
		this.velocity = velocity;
		this.mass = mass;
	}

	public Matter withName(String name) {
		this.name = name;
		return this;
	}
	
	public Matter withColor(Color color) {
		this.color = color;
		return this;
	}
	
	public Matter withAcceleration(Vector2f acceleration) {
		this.acceleration = acceleration;
		return this;
	}
	
	public void moveTo(Vector2f position) {
		this.position = position;
	}
	
	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}
	
	public void addConstantForce(Vector2f force) {
		constantForces.add(force);
	}
	
	/**
	 * @return true if force was removed, false if it wasn't present
	 */
	public boolean removeConstantForce(Vector2f force) {
		for (Vector2f f: constantForces) {
			if (force.equals(f)) {
				constantForces.remove(f);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Only acts for the next update
	 * @param force
	 */
	public void addForce(Vector2f force) {
		forces.add(force);
		forcesSum = forcesSum.add(force);
	}
	
	/**
	 * @param length is multiplied by the size of the vector. To shrink vector <1; to scale up vector >1;
	 */
	public void drawForces(Graphics g, float scale, float length) {
		for (Vector2f force: constantForces) {
			Util.drawVector(g, force.scale(scale*length), position.scale(scale), Color.YELLOW);
		}
		for (Vector2f force: forces) {
			Util.drawVector(g, force.scale(scale*length), position.scale(scale), Color.YELLOW);
		}
	}
	
	public void update(float dt) {
		// Sums the net force on the object
		for (Vector2f f: constantForces) {
			forcesSum = forcesSum.add(f);
		}
		
		// F = m * a
		acceleration = forcesSum.div(mass);
		
		// V = V0 + a * t
		velocity = velocity.add(acceleration.mult(dt));
		
		// X = X0 + V * t
		position = position.add(velocity.mult(dt));
		
		// Resets the net force to zero (it is summed every time)
		forcesSum = Vector2f.zero();
		forces = new ArrayList<Vector2f>();
	}
	
	public abstract void draw(Graphics g, float scale);
	
	public String getName() {
		return name;
	}
	
	public float getMass() {
		return mass;
	}
	
	public Vector2f getPosition() {
		return position;
	}
	
	public Vector2f getVelocity() {
		return velocity;
	}
	
	public Vector2f getAcceleration() {
		return acceleration;
	}
	
	public Vector2f getForce() {
		return forcesSum;
	}
	
}