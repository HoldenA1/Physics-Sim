package tech.hackerlife.sim.physics.matter.orbitals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import tech.hackerlife.math.Vector2f;
import tech.hackerlife.sim.physics.Thing;
import tech.hackerlife.sim.physics.matter.Ball;
import tech.hackerlife.sim.physics.matter.Matter;

public class Planet extends Ball {
	
	private final static double DEFAULT_AREA_DENSITY = 30;
	private final static float G = 1000;
	private final static int TRAIL_LENGTH = 100; // Is the duration in updates the trail persists
	private ArrayList<Vector2f> trail;

	public Planet(float mass, float radius, Vector2f position, Vector2f velocity) {
		super(mass, radius, velocity, position);
		trail = new ArrayList<Vector2f>();
	}
	
	public Planet(float mass, double areaDensity, Vector2f position, Vector2f velocity) {
		this(mass, (float)(mass / areaDensity), velocity, position);
	}
	
	public Planet(float mass, Vector2f position, Vector2f velocity) {
		this(mass, DEFAULT_AREA_DENSITY, position, velocity);
	}
	
	@Override
	public Planet withColor(Color color) {
		super.withColor(color);
		return this;
	}
	
	@Override
	public Planet withName(String name) {
		super.withName(name);
		return this;
	}
	
	@Override
	public Planet withAcceleration(Vector2f acceleraton) {
		super.withAcceleration(acceleraton);
		return this;
	}
	
	@Override
	public void update(ArrayList<Thing> things) {
		super.update(things);
		
		// Adds gravity
		for (Thing t: things) {
			if (t.isMatter() && !this.equals(t)) {
				Matter m = (Matter) t;
				
				// Calculated the direction and magnitude of gravity
				Vector2f distance = m.getPosition().add(this.getPosition().mult(-1));
				float magnitude = (G * this.getMass() * m.getMass()) / (distance.mag() * distance.mag());
				addForce(new Vector2f(distance.dir(), magnitude));
			}
		}
		
		// Adds points to the trail
		Vector2f pt = position;
		if (trail.size() == 0 || !pt.equals(trail.get(trail.size()-1))) {
			trail.add(pt);
		}
		// Keeps the trail of a reasonable size
		if (trail.size() == TRAIL_LENGTH) {
			trail.remove(0);
		}
	}
	
	@Override
	public void draw(Graphics g, float scale) {
		super.draw(g, scale);
		
		int size = Math.max((int)(radius) >> 3, 1);
		for (Vector2f point: trail) {
			Point p = point.scale(scale);
			g.fillOval(p.x, p.y, size, size);
		}
	}

}