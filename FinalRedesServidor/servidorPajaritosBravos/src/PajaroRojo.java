import processing.core.PApplet;
import processing.core.PImage;

public class PajaroRojo implements DrawableMovable {
	  float x;       // horizontal location of stripe
	  float y;       // vertical location of stripe
	  float speed;   // speed of stripe
	  PApplet parent; // The parent PApplet that we will render ourselves onto
	  PImage img;
	  
	  int maxX;
	  int maxY;
	  int minY;
	  
	  boolean moveEnd;
	  boolean upEnd;

	  public PajaroRojo(PApplet p, PImage i, int initX, int initY) {
	    parent = p;
	    x = initX;
	    y = initY;
	    speed = parent.random(10);
	    img = i;
	    maxX = 800;
	    maxY = 100;
	    minY = 500;
	    moveEnd = false;
	    upEnd = false;
	  }

	  // Draw stripe
	  public void display() {
		  parent.image(img, x, y);
	  }

	  // Move stripe
	  public void move() {
		  if(upEnd) {
			  y += speed;
			  if(y > minY) {
				  moveEnd = true;
			  }
		  } else {
			  if(y > maxY) {
				  y -= speed;
			  }
			  else {
				  upEnd = true;
			  }
		  }
		  
		  if(x < maxX) {
			  x += speed;
		  }
		  else {
			  moveEnd = true;
		  }
	  }

	public boolean isMoveEnd() {
		return moveEnd;
	}
}