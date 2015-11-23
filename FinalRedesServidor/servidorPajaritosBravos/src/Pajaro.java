import processing.core.PApplet;
import processing.core.PImage;

public class Pajaro extends DrawableMovable {
	
	  int maxX; //el punto mas lejos donde le pajaro puede llegar
	  int maxY; //el punto mas alto que el pajaro puede alcanzar
	  int minY; //el suelo
	  boolean upEnd; //con esto sabemos cuando el pajaro llego al punto mas alto para poderlo hacer bajar
	private boolean nosPodemosMover;

	  public Pajaro(PApplet p, PImage i, int initX, int initY) {
		super(p, i, initX, initY, p.random(10));
	    
	    maxX = 800;
	    maxY = 100;
	    minY = 500;
	    upEnd = false;
	    nosPodemosMover = false;
	  }

	  public boolean isNosPodemosMover() {
		return nosPodemosMover;
	}

	public void setNosPodemosMover(boolean nosPodemosMover) {
		this.nosPodemosMover = nosPodemosMover;
	}

	// Move stripe
	  public void move() {
		  if(nosPodemosMover){
		  if(upEnd) {
			  y += speed;
			  if(y > minY) {
				  dissapears = true;
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
			  dissapears = true;
		  }
		calculateCenter();
		  }
	  }

	public void mataCerdo(Cerdo cerdo) {
		if(this.closeEnoughFrom(cerdo)) {
			cerdo.setMustDisapear(true);
		}
	}
}