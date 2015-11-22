
public class CerdoNormal extends Cerdo {
	
	public CerdoNormal(MainAppServer p, int initX, int initY) {
		super(p, p.cerdo, initX, initY - 47);
		speed = 2;
		minX = 200;
	}

}
