
public class CerdoSoldado extends Cerdo {

	public CerdoSoldado(MainAppServer p, int initX, int initY) {
		super(p, p.cerdo2, initX, initY);
		speed = 1;
		minX = 150;
	}
}
