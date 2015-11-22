import processing.core.PApplet;
import processing.core.PImage;

public class Cerdo implements DrawableMovable {

	float x; // posicion horizontal
	float y; // posicion vertical
	float speed; // velocidad
	PApplet parent; // The parent PApplet that we will render ourselves onto
	PImage img;
	
	int minX; //el punto mas lejos donde le cerdo puede llegar
	
	boolean moveEnd; //con esto sabemos cuando el cerdo llega al nido o algo
	
	public Cerdo(PApplet p, PImage i, int initX, int initY) {
		parent = p;
	    x = initX;
	    y = initY;
	    speed = parent.random(10);
	    img = i;
	    minX = 200;
	    moveEnd = false;
	}

	@Override
	public void move() {
		if(x > minX) {
			x -= speed;
		} else {
			moveEnd = true;
		}
	}

	@Override
	public void display() {
		parent.image(img, x, y);
	}

	@Override
	public boolean isMoveEnd() {
		return moveEnd;
	}

}
