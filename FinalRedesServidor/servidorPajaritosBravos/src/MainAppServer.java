import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class MainAppServer extends PApplet{
	Servidor server;
	
	public PImage escenario, nido, rojo, azul, amarillo, cauchera, cerdo, cerdo2, cerdo3, huevo; 
	ArrayList<DrawableMovable> objetosEnElLienzo = new ArrayList<>();
	ArrayList<DrawableMovable> objetosARetirar = new ArrayList<>();
	
	@Override
	public void setup() {		
		server = new Servidor(this);
		server.start();
		
		size(1080,720);
		escenario = loadImage("../data/escenario.png");
		rojo = loadImage("../data/rojo.png");
		azul= loadImage("../data/azul.png");
//		amarillo= loadImage("amarillo.png");
//		cerdo = loadImage("cerdo.png");
//		loadImage("");
	}
	
	@Override
	public void draw() {
		background(0);
		image(escenario,0,0);
		
		for(DrawableMovable drawable : objetosEnElLienzo) {
			drawable.display();
			drawable.move();
			
			if (drawable.isMoveEnd()) {
				objetosARetirar.add(drawable);
			}
		}
		
		objetosEnElLienzo.removeAll(objetosARetirar);
		objetosARetirar.clear();
	}
	
	public void agregarAlLienzo(DrawableMovable objeto) {
		objetosEnElLienzo.add(objeto);
	}
}
