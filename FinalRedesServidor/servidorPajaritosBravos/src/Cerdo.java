import processing.core.PApplet;
import processing.core.PImage;

public class Cerdo extends DrawableMovable {
	
	int minX; //el punto mas lejos donde le cerdo puede llegar
	
	public Cerdo(PApplet p, PImage i, int initX, int initY) {
		super(p, i, initX, initY, p.random(10));
	    minX = 50;
	}

	@Override
	public void move() {
		if(x > minX) {
			x -= speed;
		} else {
			dissapears = true;
		}
		calculateCenter();
	}
	
	public void comerHuevo(Huevo huevo){
		if(this.closeEnoughFrom(huevo)) {
			huevo.setMustDisapear(true);
		}
	}
}
