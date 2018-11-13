package tech.hackerlife.sim.maths;

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
	
	public void mult(float scalar) {
		x *= scalar;
		y *= scalar;
	}
	
	public void divide(float scalar) {
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
}
