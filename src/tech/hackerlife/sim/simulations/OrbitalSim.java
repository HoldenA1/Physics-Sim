package tech.hackerlife.sim.simulations;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tech.hackerlife.gui.Mouse;
import tech.hackerlife.math.Vector2f;
import tech.hackerlife.sim.physics.matter.orbitals.Planet;

public class OrbitalSim extends SimPanel {
	
	private Planet m1, m2, m3;
	
	float mass1 = 500;//kg
	Vector2f initialPos1 = new Vector2f(0, 0);
	Vector2f initialSpeed1 = new Vector2f(0, 0);
	
	float mass2 = 500;//kg
	Vector2f initialPos2 = new Vector2f(100, 0);
	Vector2f initialSpeed2 = new Vector2f(0, 90);
	
	float mass3 = 500;//kg
	Vector2f initialPos3 = new Vector2f(-200, 0);
	Vector2f initialSpeed3 = new Vector2f(0, -60);
	
	public OrbitalSim(int width, int height) {
		super(width, height);
		
		m1 = new Planet(mass1, initialPos1, initialSpeed1).withColor(Color.CYAN);
		objectManager.add(m1);
		
		m2 = new Planet(mass2, initialPos2, initialSpeed2).withColor(Color.RED);
		objectManager.add(m2);
		
		m3 = new Planet(mass3, initialPos3, initialSpeed3).withColor(Color.MAGENTA);
		objectManager.add(m3);
	}
	
	protected void reset() {
		super.reset();
		
		m1.moveTo(initialPos1);
		m1.setVelocity(initialSpeed1);
		
		m2.moveTo(initialPos2);
		m2.setVelocity(initialSpeed2);
		
		m3.moveTo(initialPos3);
		m3.setVelocity(initialSpeed3);
	}
	
	@Override
	public void draw(Graphics g, JPanel panel, Mouse mouse, float scale) {
		super.draw(g, panel, mouse, scale);
		
		m1.drawForces(g, scale, 0.002f);
	}
	
	@Override
	public void update() {
		super.update();
		
		m1.addForce(m1.calculateGravitationalForce(m2, m3));
		m2.addForce(m2.calculateGravitationalForce(m1, m3));
		m3.addForce(m3.calculateGravitationalForce(m1, m2));
	}

}