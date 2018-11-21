package tech.hackerlife.sim.maths;

import java.awt.Graphics;

public class Vector2D {
	float x, y;
	
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D add(Vector2D vec) {
		float x = this.x + vec.X();
		float y = this.y + vec.Y();
		return new Vector2D(x, y);
	}
	
	public Vector2D add(float scalar) {
		float x = this.x + scalar;
		float y = this.y + scalar;
		return new Vector2D(x, y);
	}
	
	public Vector2D divideVec(Vector2D vec) {
		float x = this.x / vec.X();
		float y = this.y / vec.Y();
		return new Vector2D(x, y);
	}
	
	public Vector2D divideScalar(float scalar) {
		float x = this.x / scalar;
		float y = this.y / scalar;
		return new Vector2D(x, y);
	}
	
	public Vector2D mult(float scalar) {
		float x = this.x * scalar;
		float y = this.y * scalar;
		return new Vector2D(x, y);
	}
	
	public float mag() {
		return (float) Math.sqrt(x*x + y*y);
	}
	
	public Vector2D normalize() {
		float magnitude = this.mag();
		if (magnitude > 0) {
			return this.divideScalar(magnitude);
		}
		return this;
	}
	
	public boolean equals(Vector2D vec) {
		return x == vec.X() && y == vec.Y();
	}
	
	/**
	 * @param scale Put 1 for normal use
	 */
	public void drawVector(Graphics g, Vector2D pos, float scale) {
		Vector2D scaledInitial = pos.mult(scale);
		Vector2D scaledFinal = this.mult(scale);
		scaledFinal = scaledFinal.add(scaledInitial);
	    int dx = (int) (scaledFinal.X() - scaledInitial.X());
	    int dy = (int) (scaledFinal.Y() - scaledInitial.Y());
	    double D = Math.sqrt(dx*dx + dy*dy);
	    double xm = D - scale, xn = xm, ym = scale, yn = -scale, x;
	    double sin = dy / D, cos = dx / D;

	    x = xm*cos - ym*sin + scaledInitial.X();
	    ym = xm*sin + ym*cos + scaledInitial.Y();
	    xm = x;

	    x = xn*cos - yn*sin + scaledInitial.X();
	    yn = xn*sin + yn*cos + scaledInitial.Y();
	    xn = x;

	    int[] xpoints = {(int) scaledFinal.X(), (int) xm, (int) xn};
	    int[] ypoints = {(int) scaledFinal.Y(), (int) ym, (int) yn};

	    g.drawLine((int) scaledInitial.X(), (int) scaledInitial.Y(), (int) scaledFinal.X(), (int) scaledFinal.Y());
	    g.fillPolygon(xpoints, ypoints, 3);
	}
	
	public float X() {
		return x;
	}
	
	public float Y() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
}
