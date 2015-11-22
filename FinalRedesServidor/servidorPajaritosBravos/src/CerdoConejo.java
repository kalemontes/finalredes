
public class CerdoConejo extends Cerdo {

	public CerdoConejo(MainAppServer p, int initX, int initY) {
		super(p, p.cerdo3, initX, initY - 74);
		speed = 3;
		minX = 200;
	}

}
