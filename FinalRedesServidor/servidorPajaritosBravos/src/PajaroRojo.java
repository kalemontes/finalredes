import processing.core.PImage;

public class PajaroRojo extends Pajaro {

	public PajaroRojo(MainAppServer p, int initX, int initY) {
		super(p, p.rojo, initX, initY);
		speed = 2;
		maxX = 800;
		maxY = 100;
		minY = 500;
	}

}
