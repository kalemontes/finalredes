import java.awt.Container;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class MainAppServer extends PApplet{
	Servidor server;
	
	public PImage escenario, nido, rojo, azul, amarillo, cauchera, cerdo, cerdo2, cerdo3, huevo; 
	
	ArrayList<Pajaro> pajarosEnElLienzo = new ArrayList<>();
	ArrayList<DrawableMovable> pajarosARetirarDelLienzo = new ArrayList<>();
	
	ArrayList<Cerdo> cerdosEnElLienzo = new ArrayList<>();
	ArrayList<Cerdo> cerdosARetirarDelLienzo = new ArrayList<>();
	
	ArrayList<Huevo> huevosEnElLienzo = new ArrayList<>();
	ArrayList<Huevo> huevosARetirarDelLienzo = new ArrayList<>();
	
	ArrayList<Drawable> objetosFijos = new ArrayList<>();
	
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
		cauchera = loadImage("../data/cauchera.png");
		juegoIniciado = false;
		
		//--> esto es llamado cuando los 3 utilizadores estan conectados : 
		iniciarJuego();
	}
	
	@Override
	public void draw() {
		background(0);
		
		if(juegoIniciado) {
			image(escenario,0,0);
			for(Drawable objetoFijo : objetosFijos){
				objetoFijo.display();
			}
			for(Huevo huevo : huevosEnElLienzo) {
				huevo.display();
				if (huevo.mustDisapear()) {
					huevosARetirarDelLienzo.add(huevo);
				}
			}
			huevosEnElLienzo.removeAll(huevosARetirarDelLienzo);
			huevosARetirarDelLienzo.clear();
			
			for(Pajaro pajaro : pajarosEnElLienzo) {
				pajaro.display();
				pajaro.move();
				
				for(Cerdo cerdo : cerdosEnElLienzo) {
					pajaro.mataCerdo(cerdo);
				}
				
				if (pajaro.mustDisapear()) {
					pajarosARetirarDelLienzo.add(pajaro);
				}
			}
			
			pajarosEnElLienzo.removeAll(pajarosARetirarDelLienzo);
			pajarosARetirarDelLienzo.clear();
			
			for(Cerdo cerdo : cerdosEnElLienzo) {
				cerdo.display();
				cerdo.move();
				
				for(Huevo huevo : huevosEnElLienzo) {
					cerdo.comerHuevo(huevo);
				}
				
				if (cerdo.mustDisapear()) {
					cerdosARetirarDelLienzo.add(cerdo);
				}
			}
			
			cerdosEnElLienzo.removeAll(cerdosARetirarDelLienzo);
			cerdosARetirarDelLienzo.clear();
		}
		else {
			textAlign(CENTER);
			text("Esperando jugadores : "+server.clientesConectados() + "/3", largoLienzo/2, alturaLienzo/2);
		}
	}
	
	public void agregarAlLienzo(Pajaro objeto) {
		pajarosEnElLienzo.add(objeto);
	}
	
	public void agregarAlLienzo(Cerdo objeto) {
		cerdosEnElLienzo.add(objeto);
	}
	
	public void agregarAlLienzo(Huevo objeto) {
		huevosEnElLienzo.add(objeto);
	}
	private void agregarAlLienzo(Cauchera objeto) {
		objetosFijos.add(objeto);
	}
	
	public void iniciarJuego() {
		juegoIniciado = true;
		//PajaroRojo baronRojo = new PajaroRojo(this, 35, 410);
		//baronRojo.setNosPodemosMover(true);
		
		agregarAlLienzo(new Cauchera(this, 25,310));
		
		//Agregamos los cerdos 
		agregarAlLienzo(new CerdoConejo(this, 1000, 500));
		agregarAlLienzo(new CerdoSoldado(this, 480, 500));
		agregarAlLienzo(new CerdoNormal(this, 500, 500));
		
		//Agregamos los huevos
		agregarAlLienzo(new Huevo(this, 100, 500));
		agregarAlLienzo(new Huevo(this, 300, 500));
		agregarAlLienzo(new Huevo(this, 360, 363));
		
		//agregarAlLienzo(baronRojo);
		agregarAlLienzo(new PajaroRojo(this, 10, 500));
		agregarAlLienzo(new PajaroAmarillo(this, 10, 500));
		agregarAlLienzo(new PajaroAzul(this, 10, 500));
//		agregarAlLienzo(new CerdoNormal(this, 720, 400));
		
	}

	
	
}
