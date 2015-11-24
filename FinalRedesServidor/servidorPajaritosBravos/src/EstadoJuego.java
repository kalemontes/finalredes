import processing.core.PApplet;
import processing.data.XML;

public class EstadoJuego {
	private XML xmlDatosJuego;
	private PApplet applet;
	
	////////////////////// datos del juego //////////////////
	
	int xNido;
	int yNido;
	int huevosNido;
	
	int xCauchera;
	int yCauchera;
	int azules;
	int rojos;
	int amarillos;
	
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
		guardarNido(escenario, xNido, yNido, huevosNido);
		guardarCauchera(escenario, xCauchera, yCauchera, rojos, azules, amarillos);
		applet.saveXML(xmlDatosJuego, "../data/BD_juego.xml");
	}
	
	private void guardarNido(XML parent, int x, int y, int huevos) {
		parent.setInt("x", x);
		parent.setInt("y", y);
		XML child = parent.getChild("huevos");
		child.setIntContent(huevos);
	}
	
	private void guardarCauchera(XML parent, int x, int y, int rojos, int azules, int amarillos) {
		parent.setInt("x", x);
		parent.setInt("y", y);
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
		chargarCauchera(escenario);
		chargarNido(escenario);
	}
	
	private void chargarCauchera(XML parent) {
		xCauchera = parent.getInt("x");
		yCauchera = parent.getInt("y");
		
		for (XML pajaros : parent.getChildren("pajaros")) {
			if(pajaros.getString("color").equalsIgnoreCase("rojo")) {
				rojos = pajaros.getIntContent();
			}
			else if(pajaros.getString("color").equalsIgnoreCase("azul")) {
				azules = pajaros.getIntContent();
			}
			else if(pajaros.getString("color").equalsIgnoreCase("amarillo")) {
				amarillos = pajaros.getIntContent();
			}
		}
	}
	
	private void chargarNido(XML parent) {
		xNido = parent.getInt("x");
		yNido = parent.getInt("y");
		huevosNido = parent.getChild("huevos").getIntContent();
	}

	public int getxNido() {
		return xNido;
	}

	public void setxNido(int xNido) {
		this.xNido = xNido;
	}

	public int getyNido() {
		return yNido;
	}

	public void setyNido(int yNido) {
		this.yNido = yNido;
	}

	public int getHuevosNido() {
		return huevosNido;
	}

	public void setHuevosNido(int huevosNido) {
		this.huevosNido = huevosNido;
	}

	public int getxCauchera() {
		return xCauchera;
	}

	public void setxCauchera(int xCauchera) {
		this.xCauchera = xCauchera;
	}

	public int getyCauchera() {
		return yCauchera;
	}

	public void setyCauchera(int yCauchera) {
		this.yCauchera = yCauchera;
	}

	public int getAzules() {
		return azules;
	}

	public void setAzules(int azules) {
		this.azules = azules;
	}

	public int getRojos() {
		return rojos;
	}

	public void setRojos(int rojos) {
		this.rojos = rojos;
	}

	public int getAmarillos() {
		return amarillos;
	}

	public void setAmarillos(int amarillos) {
		this.amarillos = amarillos;
	}
}