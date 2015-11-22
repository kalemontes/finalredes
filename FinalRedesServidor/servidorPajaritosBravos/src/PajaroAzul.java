

public class PajaroAzul extends Pajaro {

	public PajaroAzul(MainAppServer p, int initX, int initY) {
		super(p, p.azul, initX, initY);
		speed = 8;
		maxX = 400;
		maxY = 200;
		minY = 500;
	}

}
