package tech.hackerlife.sim.physics.environment;

import java.awt.Graphics;
import java.util.ArrayList;

import tech.hackerlife.sim.maths.Vector2D;
import tech.hackerlife.sim.physics.Thing;

public class Rope extends Thing {
	private static final float ROPE_WIDTH = 5;

	public Rope(Vector2D position, float length) {
		super(position, ROPE_WIDTH, length);
	}

	public void draw(Graphics g, float scale) {
		
	}

	public void update(float scale, ArrayList<Thing> things) {
	}

}
