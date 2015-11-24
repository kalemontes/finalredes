

public class PajaroAzul extends Pajaro {

	public PajaroAzul(MainAppServer p, int initX, int initY) {
		super(p, p.azul, initX, initY);
		speed = 3;
		maxX = 500;
		maxY = 450;
		minY = 500;
	}

}
