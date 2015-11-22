import processing.core.PApplet;
import processing.core.PImage;


public class MainAppServer extends PApplet{	
	Servidor server;
	
	private PApplet applet;
	private PImage escenario, nido, rojo, azul, amarillo, cauchera, cerdo, cerdo2, cerdo3, huevo; 
	

	

	
	@Override
	public void setup() {		
		server = new Servidor(this);
		server.start();
		
		size(1080,720);
	    background(0);
	    
		escenario = loadImage("../data/escenario.png");
//		rojo = loadImage("rojo.png");
//		azul= loadImage("azul.png");
//		amarillo= loadImage("amarillo.png");
//		cerdo = loadImage("cerdo.png");
//		loadImage("");
	}
	public void draw(){
		image(escenario,0,0);
	}
}
