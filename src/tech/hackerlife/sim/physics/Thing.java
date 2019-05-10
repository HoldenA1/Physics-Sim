package tech.hackerlife.sim.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.*;
import java.util.ArrayList;
import tech.hackerlife.math.Vector2f;

public abstract class Thing {
	protected Color color = Color.GRAY;
	protected Vector2f position;
	protected String name;
	
	public Thing(Vector2f position) {
		this.position = position;
	}
	
	public Thing withName(String name) {
		this.name = name;
		return this;
	}
	
	public Thing withColor(Color color) {
		this.color = color;
		return this;
	}
	
	public void moveTo(Vector2f newPos) {
		position = newPos;
	}
	
	public abstract void update(ArrayList<Thing> things);
	
	public abstract void draw(Graphics g, float scale);
	
	public abstract Area getArea();
	
	public Vector2f getPosition() {
		return position;
	}
	
	public boolean isMatter() {
		return false;
	}
	
	public String getName() {
		return name;
	}

}
