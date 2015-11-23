import processing.core.PApplet;
import processing.data.XML;

public class EstadoJuego {
	private XML xmlDatosJuego;
	private PApplet applet;
	
	////////////////////// datos del juego //////////////////
	
	int huevosRojosEnElEscenario;
	int huevosAzulesEnElEscenario;
	int huevosAmarillosEnElEscenario;
	
	int puntajeEmpollador;
	int huevosRojosEmpollador;
	int huevosAzulesEmpollador;
	int huevosAmarillosEmpollador;
	int pajarosRojosEmpollador;
	int pajarosAzulesEmpollador;
	int pajarosAmarillosEmpollador;
	
	int puntajeCerdoKiller;
	int pajarosRojosCerdoKiller;
	int pajarosAzulesCerdoKiller;
	int pajarosAmarillosCerdoKiller;
	
	int puntajeMonja;
	String estadoMonja;
	
	//////////////////////datos del juego //////////////////
	
	public EstadoJuego(PApplet app) {
		this.applet = app;
		
		try {
			chargar();
		} catch (Exception e) {
			System.err.println("NO SE PUEDE COMPENZAR EL JUEGO SIN DATOS DE JUEGO");
		}
	}
	
	public void guardar() {
		XML escenario = xmlDatosJuego.getChild("escenario");
		guardarHuevos(escenario, huevosRojosEnElEscenario, huevosAzulesEnElEscenario, huevosAmarillosEnElEscenario);
		
		XML jugadores = xmlDatosJuego.getChild("jugadores");
		for (XML jugador : jugadores.getChildren("jugador")) {
			if(jugador.getString("rol").equalsIgnoreCase("empollador")) {
				jugador.getChild("puntos").setIntContent(puntajeEmpollador);
				guardarHuevos(jugador, huevosRojosEmpollador, huevosAzulesEmpollador, huevosAmarillosEmpollador);
				guardarPajaros(jugador, pajarosRojosEmpollador, pajarosAzulesEmpollador, pajarosAmarillosEmpollador);
			}
			else if(jugador.getString("rol").equalsIgnoreCase("cerdokiller")) {
				jugador.getChild("puntos").setIntContent(puntajeCerdoKiller);
				guardarPajaros(jugador, pajarosRojosCerdoKiller, pajarosAzulesCerdoKiller, pajarosAmarillosCerdoKiller);
			}
			else if(jugador.getString("rol").equalsIgnoreCase("monja")) {
				jugador.getChild("puntos").setIntContent(puntajeMonja);
				jugador.getChild("estado").setContent(estadoMonja);
			}
		}
		applet.saveXML(xmlDatosJuego, "../data/BD_juego.xml");
	}
	
	private void guardarHuevos(XML parent, int rojos, int azules, int amarillos) {
		for (XML huevos : parent.getChildren("huevos")) {
			if(huevos.getString("color").equalsIgnoreCase("rojo")) {
				huevos.setIntContent(rojos);
			}
			else if(huevos.getString("color").equalsIgnoreCase("azul")) {
				huevos.setIntContent(azules);
			}
			else if(huevos.getString("color").equalsIgnoreCase("amarillo")) {
				huevos.setIntContent(amarillos);
			}
		}
	}
	
	private void guardarPajaros(XML parent, int rojos, int azules, int amarillos) {
		for (XML pajaros : parent.getChildren("pajaros")) {
			if(pajaros.getString("color").equalsIgnoreCase("rojo")) {
				pajaros.setIntContent(rojos);
			}
			else if(pajaros.getString("color").equalsIgnoreCase("azul")) {
				pajaros.setIntContent(azules);
			}
			else if(pajaros.getString("color").equalsIgnoreCase("amarillo")) {
				pajaros.setIntContent(amarillos);
			}
		}
	}
	
	public void chargar() {
		xmlDatosJuego = applet.loadXML("../data/BD_juego.xml");
		
		XML escenario = xmlDatosJuego.getChild("escenario");
		huevosRojosEnElEscenario = chargarHuevos(escenario, "rojo");
		huevosAmarillosEnElEscenario = chargarHuevos(escenario, "azul");
		huevosAzulesEnElEscenario = chargarHuevos(escenario, "amarillo");
		
		XML jugadores = xmlDatosJuego.getChild("jugadores");
		for (XML jugador : jugadores.getChildren("jugador")) {
			if(jugador.getString("rol").equalsIgnoreCase("empollador")) {
				puntajeEmpollador = jugador.getChild("puntos").getIntContent();
				huevosRojosEmpollador = chargarHuevos(jugador, "rojo");
				huevosAzulesEmpollador = chargarHuevos(jugador, "azul");
				huevosAmarillosEmpollador = chargarHuevos(jugador, "amarillo");
				pajarosRojosEmpollador = chargarPajaros(jugador, "rojo");
				pajarosAzulesEmpollador = chargarPajaros(jugador, "azul");
				pajarosAmarillosEmpollador = chargarPajaros(jugador, "amarillo");
			}
			else if(jugador.getString("rol").equalsIgnoreCase("cerdokiller")) {
				puntajeCerdoKiller = jugador.getChild("puntos").getIntContent();
				pajarosRojosCerdoKiller = chargarPajaros(jugador, "rojo");
				pajarosAzulesCerdoKiller = chargarPajaros(jugador, "azul");
				pajarosAmarillosCerdoKiller = chargarPajaros(jugador, "amarillo");
			}
			else if(jugador.getString("rol").equalsIgnoreCase("monja")) {
				puntajeMonja = jugador.getChild("puntos").getIntContent();
				estadoMonja = jugador.getChild("estado").getContent();
			}
		}

	}
	
	private int chargarPajaros(XML parent, String color) {
		for (XML huevos : parent.getChildren("pajaros")) {
			if(huevos.getString("color").equalsIgnoreCase(color)) {
				return huevos.getIntContent();
			}
		}
		return 0;
	}
	
	private int chargarHuevos(XML parent, String color) {
		for (XML huevos : parent.getChildren("huevos")) {
			if(huevos.getString("color").equalsIgnoreCase(color)) {
				return huevos.getIntContent();
			}
		}
		return 0;
	}

	public int getPuntajeEmpollador() {
		return puntajeEmpollador;
	}

	public void setPuntajeEmpollador(int puntajeEmpollador) {
		this.puntajeEmpollador = puntajeEmpollador;
	}

	public int getHuevosRojosEmpollador() {
		return huevosRojosEmpollador;
	}

	public void setHuevosRojosEmpollador(int huevosRojosEmpollador) {
		this.huevosRojosEmpollador = huevosRojosEmpollador;
	}

	public int getHuevosAzulesEmpollador() {
		return huevosAzulesEmpollador;
	}

	public void setHuevosAzulesEmpollador(int huevosAzulesEmpollador) {
		this.huevosAzulesEmpollador = huevosAzulesEmpollador;
	}

	public int getHuevosAmarillosEmpollador() {
		return huevosAmarillosEmpollador;
	}

	public void setHuevosAmarillosEmpollador(int huevosAmarillosEmpollador) {
		this.huevosAmarillosEmpollador = huevosAmarillosEmpollador;
	}

	public int getPajarosRojosEmpollador() {
		return pajarosRojosEmpollador;
	}

	public void setPajarosRojosEmpollador(int pajarosRojosEmpollador) {
		this.pajarosRojosEmpollador = pajarosRojosEmpollador;
	}

	public int getPajarosAzulesEmpollador() {
		return pajarosAzulesEmpollador;
	}

	public void setPajarosAzulesEmpollador(int pajarosAzulesEmpollador) {
		this.pajarosAzulesEmpollador = pajarosAzulesEmpollador;
	}

	public int getPajarosAmarillosEmpollador() {
		return pajarosAmarillosEmpollador;
	}

	public void setPajarosAmarillosEmpollador(int pajarosAmarillosEmpollador) {
		this.pajarosAmarillosEmpollador = pajarosAmarillosEmpollador;
	}

	public int getPuntajeCerdoKiller() {
		return puntajeCerdoKiller;
	}

	public void setPuntajeCerdoKiller(int puntajeCerdoKiller) {
		this.puntajeCerdoKiller = puntajeCerdoKiller;
	}

	public int getPajarosRojosCerdoKiller() {
		return pajarosRojosCerdoKiller;
	}

	public void setPajarosRojosCerdoKiller(int pajarosRojosCerdoKiller) {
		this.pajarosRojosCerdoKiller = pajarosRojosCerdoKiller;
	}

	public int getPajarosAzulesCerdoKiller() {
		return pajarosAzulesCerdoKiller;
	}

	public void setPajarosAzulesCerdoKiller(int pajarosAzulesCerdoKiller) {
		this.pajarosAzulesCerdoKiller = pajarosAzulesCerdoKiller;
	}

	public int getPajarosAmarillosCerdoKiller() {
		return pajarosAmarillosCerdoKiller;
	}

	public void setPajarosAmarillosCerdoKiller(int pajarosAmarillosCerdoKiller) {
		this.pajarosAmarillosCerdoKiller = pajarosAmarillosCerdoKiller;
	}

	public int getPuntajeMonja() {
		return puntajeMonja;
	}

	public void setPuntajeMonja(int puntajeMonja) {
		this.puntajeMonja = puntajeMonja;
	}

	public String getEstadoMonja() {
		return estadoMonja;
	}

	public void setEstadoMonja(String estadoMonja) {
		this.estadoMonja = estadoMonja;
	}
}
