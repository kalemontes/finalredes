
public class PajaroNegro extends Pajaro {

	public int xNido, yNido;
	
	public PajaroNegro(MainAppServer p, int initX, int initY) {
		super(p, p.negro, initX, initY);
		speed = 0;
		maxX = 1080;
		maxY = 0;
		minY = 720;
		
		xNido=initX;
		yNido=initY;
	}
	public void volverAlNido(){
		x=xNido;
		y=yNido;
		calculateCenter();
	}
	public void rescatarHuevo(Huevo huevo){
		x= huevo.x;
		y= huevo.y;
		calculateCenter();
		huevo.setMustDisapear(true);
		Huevo huevoNuevo = new Huevo((MainAppServer) parent, xNido + 30, yNido);
		((MainAppServer) parent).huevosEnElNido.add(huevoNuevo);
	}
}