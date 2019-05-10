package tech.hackerlife.sim.physics;

import java.awt.Graphics;
import java.util.ArrayList;
import tech.hackerlife.sim.Main;
import tech.hackerlife.sim.physics.matter.*;

public class ObjectManager {
	private ArrayList<Matter> matter;
	private ArrayList<Thing> things;
	
	public ObjectManager() {
		reset();
	}
	
	public void reset() {
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
	
	public void draw(Graphics g, float scale) {
		for (Thing t: things) {
			t.draw(g, scale);
		}
	}
	
	public void update(float scale) {
		for (Thing t: things) {
			t.update(things);
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