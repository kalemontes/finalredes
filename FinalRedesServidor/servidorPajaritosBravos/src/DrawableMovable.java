import processing.core.PApplet;
import processing.core.PImage;

public abstract class DrawableMovable extends Drawable {
	
	float speed; // velocidad
	
	public DrawableMovable(PApplet p, PImage i, int initX, int initY, float s) {
		super(p, i, initX, initY);
		speed = s;
	}

	public abstract void move();
}
