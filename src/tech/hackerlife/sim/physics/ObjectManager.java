package tech.hackerlife.sim.physics;

import java.awt.Graphics;
import java.util.ArrayList;
import tech.hackerlife.sim.Main;
import tech.hackerlife.math.Vector2f;
import tech.hackerlife.sim.physics.matter.*;

public class ObjectManager {
	private ArrayList<Matter> matter;
	private ArrayList<Thing> things;
	
	public ObjectManager() {
		matter = new ArrayList<Matter>();
		things = new ArrayList<Thing>();
	}
	
	public void add(Matter element) {
		matter.add(element);
		things.add(element);
	}
	
	public void add(Thing element) {
		things.add(element);
	}
	
	public void addGravity() {
		for (Matter m: matter) {
			m.addConstantForce(new Vector2f(0, 9.8f*m.getMass()));
		}
	}
	
	public void removeGravity() {
		for (Matter m: matter) {
			m.removeConstantForce(new Vector2f(0, 9.8f*m.getMass()));
		}
	}
	
	public void draw(Graphics g, float scale) {
		for (Thing t: things) {
			t.draw(g, scale);
		}
	}
	
	public void update(float scale) {
		for (Thing t: things) {
			t.update(scale, things);
		}
		for (Matter m: matter) {
			if (m.translate) {
				for (Thing t: things) {
					if (!m.equals(t)) {
						m.collision(t);
					}
				}
			}
			if (m.translate) {
				m.moveTo(m.getPosition().add(m.getVelocity().divideScalar(Main.realTimeUPS)));
			}
		}
		for (Matter m: matter) {
			m.translate = true;
		}
	}
	
	public ArrayList<Matter> getMatterList() {
		return matter;
	}

}