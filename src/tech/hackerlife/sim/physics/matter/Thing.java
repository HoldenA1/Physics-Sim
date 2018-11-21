package tech.hackerlife.sim.physics.matter;

import java.awt.Color;
import java.awt.Graphics;

import tech.hackerlife.sim.maths.Vector2D;

public abstract class Thing {
	protected Color color = Color.GRAY;
	protected Vector2D position;
	
	public Thing(Vector2D position) {
		this.position = position;
	}
	
	public Thing withColor(Color color) {
		this.color = color;
		return this;
	}
	
	public abstract void draw(Graphics g, float scale);

}
