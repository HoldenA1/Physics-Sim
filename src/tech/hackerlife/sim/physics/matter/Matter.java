package tech.hackerlife.sim.physics.matter;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import tech.hackerlife.sim.Main;
import tech.hackerlife.math.Vector2f;
import tech.hackerlife.sim.physics.Thing;

public abstract class Matter extends Thing {
	private float mass;
	private boolean constantAcceleration = false;
	private boolean isElastic = true;
	private Vector2f velocity = new Vector2f(0,0);
	private Vector2f acceleration = new Vector2f(0,0);
	private Vector2f forcesSum = new Vector2f(0,0);
	private ArrayList<Vector2f> constantForces = new ArrayList<Vector2f>();
	private ArrayList<Vector2f> forces = new ArrayList<Vector2f>();
	
	// Part of the collision stuff
	public boolean translate = true;
	
	public Matter(float mass, Vector2f position, Vector2f velocity) {
		super(position);
		this.mass = mass;
		this.velocity = velocity;
	}
	
	public Matter withColor(Color color) {
		super.withColor(color);
		return this;
	}
	
	public Matter withAcceleration(Vector2f acceleration) {
		this.acceleration = acceleration;
		constantAcceleration = true;
		return this;
	}
	
	public void setVelocity(Vector2f velocity) {
		if (velocity == null) {
			velocity = new Vector2f(0,0);
		}
		this.velocity = velocity;
	}
	
	public void setElastic(boolean isElastic) {
		this.isElastic = isElastic;
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
	 * @param g
	 * @param scale refers to how much you want to scale up or down the vectors
	 */
	public void drawForces(Graphics g, float scale) {
		for (Vector2f force: constantForces) {
			force.mult(scale).drawVector(g, position, Main.SCALE, Color.YELLOW);
		}
		for (Vector2f force: forces) {
			force.mult(scale).drawVector(g, position, Main.SCALE, Color.YELLOW);
		}
	}
	
	public void update(ArrayList<Thing> things) {
		// Gets the net force on the object
		for (Vector2f f: constantForces) {
			forcesSum = forcesSum.add(f);
		}
		
		// Update acceleration
		if (!constantAcceleration) {
			acceleration = forcesSum.divideScalar(mass);
		}
		
		// Update velocity
		velocity = velocity.add(acceleration.divideScalar(Main.realTimeUPS));
		
		// Resets the net force to zero (it is summed every time)
		forcesSum = Vector2f.ZERO;
		forces = new ArrayList<Vector2f>();
	}
	
	// Pls make look better it are spegett code
	public void collision(Thing t) {
		Vector2f scaledVelocity = velocity.divideScalar(Main.realTimeUPS);
		Vector2f prevPos = position;
		this.moveTo(position.add(scaledVelocity));
		
		if (t.isMatter()) {
			Matter m = (Matter) t;
			Vector2f scaledVelocityObj = m.getVelocity().divideScalar(Main.realTimeUPS);
			Vector2f prevPosObj = m.getPosition();
			m.moveTo(m.getPosition().add(scaledVelocityObj));
			
			if (hasCollided(m)) {
				this.moveTo(prevPos);
				m.moveTo(prevPosObj);

				// Test X
				this.moveTo(position.add(new Vector2f(scaledVelocity.X(), 0)));
				m.moveTo(m.getPosition().add(new Vector2f(scaledVelocityObj.X(), 0)));
				
				if (hasCollided(m)) {
					this.moveTo(prevPos);
					m.moveTo(prevPosObj);
					if (isElastic || m.isElastic()) {
						float finalVelOfThis = 2 * m.getVelocity().X() * m.getMass() / (mass +  m.getMass());
						float finalVelofOtherObject = 2 * velocity.X() * mass / (mass +  m.getMass());
						velocity.setX(finalVelOfThis);
						m.getVelocity().setX(finalVelofOtherObject);
					} else {
						float finalVel = (mass * velocity.X() + m.getMass() * m.getVelocity().X()) / (mass +  m.getMass());
						velocity.setX(finalVel);
						m.getVelocity().setX(finalVel);
					}
				}
				
				// Updates the previous position
				prevPos = position;
				prevPosObj = m.getPosition();
				
				// Test Y
				this.moveTo(position.add(new Vector2f(0, scaledVelocity.Y())));
				m.moveTo(m.getPosition().add(new Vector2f(0, scaledVelocityObj.Y())));
				
				if (hasCollided(m)) {
					this.moveTo(prevPos);
					m.moveTo(prevPosObj);
					if (isElastic || m.isElastic()) {
						float finalVelOfThis = 2 * m.getVelocity().Y() * m.getMass() / (mass +  m.getMass());
						float finalVelofOtherObject = 2 * velocity.Y() * mass / (mass +  m.getMass());
						velocity.setY(finalVelOfThis);
						m.getVelocity().setY(finalVelofOtherObject);
					} else {
						float finalVel = (mass * velocity.Y() + m.getMass() * m.getVelocity().Y()) / (mass +  m.getMass());
						velocity.setY(finalVel);
						m.getVelocity().setY(finalVel);
					}
				}
				this.translate = false;
				m.translate = false;
			}
			m.moveTo(prevPosObj);	
		} else {
			if (hasCollided(t)) {
				this.moveTo(prevPos);
				// Test X
				this.moveTo(position.add(new Vector2f(scaledVelocity.X(), 0)));
				if (hasCollided(t)) {
					this.moveTo(prevPos);
					if (isElastic) {
						velocity.setX(-velocity.X());
					} else {
						velocity.setX(0);
					}
				}
				
				// Updates the previous position
				prevPos = position;
				
				// Test Y
				this.moveTo(position.add(new Vector2f(0, scaledVelocity.Y())));
				if (hasCollided(t)) {
					this.moveTo(prevPos);
					if (isElastic) {
						velocity.setY(-velocity.Y());
					} else {
						velocity.setY(0);
					}
				}
				this.translate = false;
			}
		}
		this.moveTo(prevPos);
		this.translate = true;
	}
	
	public boolean hasCollided(Thing t) {
		return getArea().intersects(t.getArea().getBounds2D());
	}
	
	public boolean isMatter() {
		return true;
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
	
	public float getMass() {
		return mass;
	}
	
	public boolean isElastic() {
		return isElastic;
	}
	
}