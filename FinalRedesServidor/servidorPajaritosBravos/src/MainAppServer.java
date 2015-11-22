import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class MainAppServer extends PApplet{
	Servidor server;
	
	public PImage escenario, nido, rojo, azul, amarillo, cauchera, cerdo, cerdo2, cerdo3, huevo; 
	ArrayList<DrawableMovable> objetosEnElLienzo = new ArrayList<>();
	ArrayList<DrawableMovable> objetosARetirar = new ArrayList<>();
	
	int largoLienzo = 1080;
	int alturaLienzo = 720;
	boolean juegoIniciado;
	
	@Override
	public void setup() {		
		server = new Servidor(this);
		server.start();
		
		size(largoLienzo,alturaLienzo);
		escenario = loadImage("../data/escenario.png");
		rojo = loadImage("../data/rojo.png");
		azul= loadImage("../data/azul.png");
		amarillo= loadImage("../data/amarillo.png");
		cerdo = loadImage("../data/cerdo.png");
		cerdo2 = loadImage("../data/cerdo2.png");
		cerdo3 = loadImage("../data/cerdo3.png");
		huevo = loadImage("../data/huevo.png");
		juegoIniciado = false;
	}
	
	@Override
	public void draw() {
		background(0);
		
		if(juegoIniciado) {
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
		else {
			textAlign(CENTER);
			text("Esperando jugadores : "+server.clientesConectados() + "/3", largoLienzo/2, alturaLienzo/2);
		}
	}
	
	public void agregarAlLienzo(DrawableMovable objeto) {
		objetosEnElLienzo.add(objeto);
	}
	
	public void iniciarJuego() {
		juegoIniciado = true;
		
		//Agregamos los cerdos 
		agregarAlLienzo(new CerdoConejo(this, 1000, 500));
		agregarAlLienzo(new CerdoSoldado(this, 400, 500));
		agregarAlLienzo(new CerdoNormal(this, 500, 500));
	}
}
