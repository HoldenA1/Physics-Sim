package tech.hackerlife.sim.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Util {
	private final static float VECTOR_ARROW_SIZE = 10;
	
	/**
	 * @param pos where the arrow should be drawn from on screen
	 */
	public static void drawVector(Graphics g, Point vec, Point pos, Color vectorColor) {
		g.setColor(vectorColor);
		
	    int dx = vec.x;
	    int dy = vec.y;
	    vec.translate(pos.x, pos.y);
	    double D = Math.sqrt(dx*dx + dy*dy);
	    double xm = D - VECTOR_ARROW_SIZE, xn = xm, ym = VECTOR_ARROW_SIZE, yn = -VECTOR_ARROW_SIZE, x;
	    double sin = dy / D, cos = dx / D;

	    x = xm*cos - ym*sin + pos.x;
	    ym = xm*sin + ym*cos + pos.y;
	    xm = x;

	    x = xn*cos - yn*sin + pos.x;
	    yn = xn*sin + yn*cos + pos.y;
	    xn = x;

	    int[] xpoints = {vec.x, (int) xm, (int) xn};
	    int[] ypoints = {vec.y, (int) ym, (int) yn};
	    
	    g.drawLine(pos.x, pos.y, vec.x, vec.y);
	    g.fillPolygon(xpoints, ypoints, 3);
	}

}
