package tech.hackerlife.sim.physics;

import java.awt.Graphics;
import tech.hackerlife.sim.Main;
import tech.hackerlife.sim.maths.Vector2D;

abstract class Matter {
	double mass;
	Vector2D position;
	Vector2D velocity;
	Vector2D acceleration;
	
	public Matter(double mass, Vector2D position, Vector2D velocity, Vector2D acceleration) {
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
	
	public void update() {
//		Vector2D scaledAcceleration = acceleration;
//		scaledAcceleration.divide(Main.realTimeUPS);
//		velocity.add(scaledAcceleration);
		
		Vector2D scaledVelocity = new Vector2D(velocity.X(),velocity.Y());
		scaledVelocity.divide(Main.realTimeUPS);
		position.add(scaledVelocity);
	}
	
	public abstract void draw(Graphics g);
	
}