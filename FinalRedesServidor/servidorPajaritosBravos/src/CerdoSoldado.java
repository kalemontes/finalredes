
public class CerdoSoldado extends Cerdo {

	public CerdoSoldado(MainAppServer p, int initX, int initY) {
		super(p, p.cerdo2, initX, initY - 43);
		speed = 1;
		minX = 200;
	}
}
