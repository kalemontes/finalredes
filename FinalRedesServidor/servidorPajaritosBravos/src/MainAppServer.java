import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;


public class MainAppServer extends PApplet{
	Servidor server;
	
	ArrayList<DrawableMovable> objetosEnElLienzo = new ArrayList<>();
	ArrayList<DrawableMovable> objetosARetirar = new ArrayList<>();
	
	@Override
	public void setup() {		
		server = new Servidor(this);
		server.start();
		
		size(1080,720);
	}
	
	@Override
	public void draw() {
		background(0);
		
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
