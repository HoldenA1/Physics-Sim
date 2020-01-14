package tech.hackerlife.sim.display.panels;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tech.hackerlife.gui.Mouse;
import tech.hackerlife.math.Vector2f;
import tech.hackerlife.sim.physics.matter.orbitals.Planet;

public class OrbitalSim extends SimPanel {
	
	private Planet m1, m2, m3;
	
	float mass1 = 500;//kg
	float mass2 = 500;//kg
	float mass3 = 500;//kg

	public OrbitalSim(int width, int height) {
		super(width, height);
		reset();
	}
	
	protected void reset() {
		objectManager.reset();
		
		m1 = new Planet(mass1, new Vector2f(0, 0), new Vector2f(0, 0)).withColor(Color.CYAN);
		objectManager.add(m1);
		
		m2 = new Planet(mass2, new Vector2f(100, 0), new Vector2f(0, 90)).withColor(Color.RED);
		objectManager.add(m2);
		
		m3 = new Planet(mass3, new Vector2f(-200, 0), new Vector2f(0, -60)).withColor(Color.MAGENTA);
		objectManager.add(m3);
	}
	
	@Override
	public void draw(Graphics g, JPanel panel, Mouse mouse, float scale) {
		super.draw(g, panel, mouse, scale);
	}
	
	@Override
	public void update(float scale) {
		super.update(scale);
	}

}