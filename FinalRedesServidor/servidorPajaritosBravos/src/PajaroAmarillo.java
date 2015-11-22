import processing.core.PImage;

public class PajaroAmarillo extends Pajaro {
	
	public PajaroAmarillo(MainAppServer p, int initX, int initY) {
		super(p, p.amarillo, initX, initY);
		speed = 10;
		maxX = 1000;
		maxY = 300;
		minY = 500;
	}

}