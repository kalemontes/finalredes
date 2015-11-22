
public class Huevo extends Drawable {

	public Huevo(MainAppServer p, int initX, int initY) {
		super(p, p.huevo, initX, initY); 
	}
	
	@Override
	public void display() {
		parent.image(img, xCenter, yCenter);//el huevo se posiciona segun el centro de la imagen
	}
}
