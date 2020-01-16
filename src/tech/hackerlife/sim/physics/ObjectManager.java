package tech.hackerlife.sim.physics;

import java.awt.Graphics;
import java.util.ArrayList;
import tech.hackerlife.sim.Main;
import tech.hackerlife.sim.physics.matter.*;

public class ObjectManager {
	private ArrayList<Matter> matter;
	
	public ObjectManager() {
		matter = new ArrayList<Matter>();
	}
	
	public void add(Matter element) {
		matter.add(element);
	}
	
	public void draw(Graphics g, float scale) {
		for (Matter m: matter) {
			m.draw(g, scale);
		}
	}
	
	public void update() {
		for (Matter m: matter) {
			m.update(1.0f/Main.realTimeUPS);
		}
	}
	
	public ArrayList<Matter> getMatterList() {
		return matter;
	}

}