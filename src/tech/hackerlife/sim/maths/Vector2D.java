package tech.hackerlife.sim.maths;

import java.awt.Graphics;

public class Vector2D {
	float x, y;
	
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(Vector2D vec) {
		x += vec.X();
		y += vec.Y();
	}
	
	public void add(float scalar) {
		x += scalar;
		y += scalar;
	}
	
	private void divide(float scalar) {
		x /= scalar;
		y /= scalar;
	}
	
	public float mag() {
		return (float) Math.sqrt(x*x + y*y);
	}
	
	public void normalize() {
		float magnitude = this.mag();
		if (magnitude > 0) {
			this.divide(magnitude);
		}
	}
	
	/**
	 * @param scale Put 1 for normal use
	 */
	public void drawVector(Graphics g, Vector2D pos, float scale) {
		Vector2D scaledInitial = Vector2D.mult(pos, scale);
		Vector2D scaledFinal = Vector2D.mult(this, scale);
		scaledFinal.add(scaledInitial);
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
	
	public static Vector2D divide(Vector2D vec1, Vector2D vec2) {
		return new Vector2D(vec1.X()/vec2.X(), vec1.Y()/vec2.Y());
	}
	
	public static Vector2D divide(Vector2D vec, float scalar) {
		return new Vector2D(vec.X()/scalar, vec.Y()/scalar);
	}
	
	public static Vector2D mult(Vector2D vec, float scalar) {
		return new Vector2D(vec.X()*scalar, vec.Y()*scalar);
	}
}
