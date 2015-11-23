import processing.core.PApplet;
import processing.core.PImage;

public abstract class Drawable {
	
	float xCenter;
	float yCenter;
	int width;
	int height;
	
	float x; // posicion horizontal
	float y; // posicion vertical
	PApplet parent; // The parent PApplet that we will render ourselves onto
	PImage img;
	
	boolean dissapears; //con esto sabemos hay que retirarlo del lienzo
	
	public Drawable(PApplet p, PImage i, int initX, int initY) {
		parent = p;
	    x = initX;
	    y = initY;
	    img = i;
	    dissapears = false;
	    width = i.width;
	    height = i.height;
	    calculateCenter();
	}
	
	public void calculateCenter() {
	    xCenter = x + width/2;
	    yCenter = y + height/2;
	}
	
	public void display() {
		parent.image(img, x, y);
	}

	public boolean mustDisapear() {
		return dissapears;
	}
	
	public void setMustDisapear(boolean d) {
		dissapears = d;
	}
}
