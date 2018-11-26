package tech.hackerlife.sim.maths;

public class Trig {
	
	public static float getRefAngle(float theta) {
		float pi = (float) Math.PI;		
		float refAngle;
		
		if (theta <= pi/2) {
			refAngle = theta;
		} else if (theta <= pi) {
			refAngle = pi-theta;
		} else if (theta <= 3*pi/2) {
			refAngle = pi+theta;
		} else if (theta <= 2*pi) {
			refAngle = 2*pi-theta;
		} else {
			refAngle = -1;
		}
		return refAngle;
	}
	
	public static int getQuadrant(float theta) {
		float pi = (float) Math.PI;		
		int quad;
		
		if (theta <= pi/2) {
			quad = 1;
		} else if (theta <= pi) {
			quad = 2;
		} else if (theta <= 3*pi/2) {
			quad = 3;
		} else if (theta <= 2*pi) {
			quad = 3;
		} else {
			quad = -1;
		}
		return quad;
	}

}
