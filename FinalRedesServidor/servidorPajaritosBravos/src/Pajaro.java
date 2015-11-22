import processing.core.PApplet;
import processing.core.PImage;

public class Pajaro implements DrawableMovable {
	  float x;       // posicion horizontal
	  float y;       // posicion vertical
	  float speed;   // velocidad
	  PApplet parent; // The parent PApplet that we will render ourselves onto
	  PImage img;
	  
	  int maxX; //el punto mas lejos donde le pajaro puede llegar
	  int maxY; //el punto mas alto que el pajaro puede alcanzar
	  int minY; //el suelo
	  
	  boolean moveEnd; //con esto sabemos cuando el pajaro toco el suelo
	  boolean upEnd; //con esto sabemos cuando el pajaro llego al punto mas alto para poderlo hacer bajar

	  public Pajaro(PApplet p, PImage i, int initX, int initY) {
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