import processing.core.PApplet;
import processing.core.PImage;

public abstract class Drawable {
	
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
	}
	
	public void display() {
		parent.image(img, x, y);
	}

	public boolean mustDisapear() {
		return dissapears;
	}
}
