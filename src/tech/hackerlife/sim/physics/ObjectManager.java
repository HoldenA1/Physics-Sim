package tech.hackerlife.sim.physics;

import java.awt.Graphics;
import java.util.ArrayList;

import tech.hackerlife.sim.maths.Vector2D;
import tech.hackerlife.sim.physics.matter.*;

public class ObjectManager {
	private ArrayList<Matter> matter;
	private ArrayList<Thing> env;
	private ArrayList<Thing> things;
	
	public ObjectManager() {
		matter = new ArrayList<Matter>();
		env = new ArrayList<Thing>();
		things = new ArrayList<Thing>();
	}
	
	public void add(Matter element) {
		matter.add(element);
		things.add(element);
	}
	
	public void add(Thing element) {
		env.add(element);
		things.add(element);
	}
	
	public void addGravity() {
		for (Matter m: matter) {
			m.addForce(new Vector2D(0, 9.8f*m.getMass()));
		}
	}
	
	public void removeGravity() {
		for (Matter m: matter) {
			m.removeForce(new Vector2D(0, 9.8f*m.getMass()));
		}
	}
	
	public void draw(Graphics g, float scale) {
		for (Thing t: env) {
			t.draw(g, scale);
		}
		for (Matter m: matter) {
			m.draw(g, scale);
		}
	}
	
	public void update(float scale) {
		for (Thing t: env) {
			t.update(scale, things);
		}
		for (Matter m: matter) {
			m.update(scale, things);
		}
	}

}