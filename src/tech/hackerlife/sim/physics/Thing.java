package tech.hackerlife.sim.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.*;
import java.util.ArrayList;
import tech.hackerlife.sim.maths.Vector2D;

public abstract class Thing {
	protected Color color = Color.GRAY;
	protected Vector2D position;
	protected float width, height;
	protected float radius;
	
	public Thing(Vector2D position, float width, float height) {
		this.position = position;
		this.width = width;
		this.height = height;
	}
	
	public Thing(Vector2D position, float radius) {
		this.position = position;
		this.radius = radius;
	}
	
	public Thing withColor(Color color) {
		this.color = color;
		return this;
	}
	
	public void moveTo(Vector2D newPos) {
		position = newPos;
	}
	
	public abstract void update(float scale, ArrayList<Thing> things);
	
	public abstract void draw(Graphics g, float scale);
	
	public Vector2D getPosition() {
		return position;
	}
	
	public boolean isMatter() {
		return false;
	}
	
	/**
	 * Default getArea method. Is overrided in Ball class
	 */
	public Area getArea() {
		return new Area(new Rectangle2D.Float(position.X()-(width*0.5f), position.Y()-(height*0.5f), width, height));
	}

}
