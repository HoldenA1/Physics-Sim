package tech.hackerlife.sim.maths;

import java.awt.Graphics;

public class Vector2D {
	// Down is positive!!
	float x, y;
	final float VECTOR_ARROW_SIZE = 10;
	
	// TODO make constructors unique to remove double conversion
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @param angle in radians
	 * @param magnitude
	 */
	public Vector2D(double angle, float magnitude) {
		int quad = Trig.getQuadrant((float)angle);
		float refAngle = Trig.getRefAngle((float)angle);
		
		if (quad == 1) {
			x = (float) (magnitude*Math.cos(refAngle));
			y = (float) (-magnitude*Math.sin(refAngle));
		} else if (quad == 2) {
			x = (float) (-magnitude*Math.cos(refAngle));
			y = (float) (-magnitude*Math.sin(refAngle));
		} else if (quad == 3) {
			x = (float) (-magnitude*Math.cos(refAngle));
			y = (float) (magnitude*Math.sin(refAngle));
		} else if (quad == 4) {
			x = (float) (magnitude*Math.cos(refAngle));
			y = (float) (magnitude*Math.sin(refAngle));
		}
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
	
	/**
	 * @return angle in radians
	 */
	public float dir() {
		float theta = 0;
		if (x > 0) {
			if (y < 0) {
				theta = (float) (Math.atan(-y/x));
			} else {
				theta = (float) (2*Math.PI - Math.atan(y/x));
			}
		} else if (x == 0) {
			if (y < 0) {
				theta = (float) (Math.PI / 2);
			} else {
				theta = (float) (3*Math.PI / 2);
			}
		} else {
			if (y < 0) {
				theta = (float) (Math.PI - Math.atan(-y/x));
			} else {
				theta = (float) (Math.PI + Math.atan(y/x));
			}
		}
		return theta;
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
	    double xm = D - VECTOR_ARROW_SIZE, xn = xm, ym = VECTOR_ARROW_SIZE, yn = -VECTOR_ARROW_SIZE, x;
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
