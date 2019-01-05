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
	private boolean isElastic = true;
	private Vector2D velocity = new Vector2D(0,0);
	private Vector2D acceleration = new Vector2D(0,0);
	private Vector2D forcesSum = new Vector2D(0,0);
	private ArrayList<Vector2D> constantForces = new ArrayList<Vector2D>();
	
	// Part of the collision stuff
	public boolean translate = true;
	
	public Matter(float mass, Vector2D position, Vector2D velocity, float width, float height) {
		super(position, width, height);
		this.mass = mass;
		if (velocity == null) {
			this.velocity = new Vector2D(0,0);
		} else {
			this.velocity = velocity;
		}
	}
	
	public Matter(float mass, Vector2D position, Vector2D velocity, float radius) {
		super(position, radius);
		this.mass = mass;
		if (velocity == null) {
			this.velocity = new Vector2D(0,0);
		} else {
			this.velocity = velocity;
		}
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
	
	public void setElastic(boolean isElastic) {
		this.isElastic = isElastic;
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
	
	public void addForce(Vector2D force) {
		forcesSum = forcesSum.add(force);
	}
	
	public void drawForces(Graphics g, float scale) {
		for (Vector2D force: constantForces) {
			force.drawVector(g, position, scale, Color.YELLOW);
		}
	}
	
	public void update(float scale, ArrayList<Thing> things) {
		// Resets the net force to zero (it is summed every time)
		forcesSum = new Vector2D(0,0);
		
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
	}
	
	// Pls make look better it are spegett code
	public void collision(Thing t) {
		Vector2D scaledVelocity = velocity.divideScalar(Main.realTimeUPS);
		Vector2D prevPos = position;
		this.moveTo(position.add(scaledVelocity));
		
		if (t.isMatter()) {
			Matter m = (Matter) t;
			Vector2D scaledVelocityObj = m.getVelocity().divideScalar(Main.realTimeUPS);
			Vector2D prevPosObj = m.getPosition();
			m.moveTo(m.getPosition().add(scaledVelocityObj));
			
			if (hasCollided(m)) {
				this.moveTo(prevPos);
				m.moveTo(prevPosObj);

				// Test X
				this.moveTo(position.add(new Vector2D(scaledVelocity.X(), 0)));
				m.moveTo(m.getPosition().add(new Vector2D(scaledVelocityObj.X(), 0)));
				
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
				this.moveTo(position.add(new Vector2D(0, scaledVelocity.Y())));
				m.moveTo(m.getPosition().add(new Vector2D(0, scaledVelocityObj.Y())));
				
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
				this.moveTo(position.add(new Vector2D(scaledVelocity.X(), 0)));
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
				this.moveTo(position.add(new Vector2D(0, scaledVelocity.Y())));
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
	
	public Vector2D getVelocity() {
		return velocity;
	}
	
	public Vector2D getAcceleration() {
		return acceleration;
	}
	
	public Vector2D getForce() {
		return forcesSum;
	}
	
	public float getMass() {
		return mass;
	}
	
	public boolean isElastic() {
		return isElastic;
	}
	
}