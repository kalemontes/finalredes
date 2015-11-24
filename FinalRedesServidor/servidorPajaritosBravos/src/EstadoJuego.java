import processing.core.PApplet;
import processing.data.XML;

public class EstadoJuego {
	private XML xmlDatosJuego;
	private PApplet applet;
	
	////////////////////// datos del juego //////////////////
	
	private int xNido;
	private int yNido;
	private int huevosNido;
	
	private int xCauchera;
	private int yCauchera;
	private int azules;
	private int rojos;
	private int amarillos;
	private int xLanzar;
	private int yLanzar;
	
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
		guardarNido(escenario.getChild("nido"), xNido, yNido, huevosNido);
		guardarCauchera(escenario.getChild("cauchera"), xCauchera, yCauchera, rojos, azules, amarillos);
		applet.saveXML(xmlDatosJuego, "../data/BD_juego.xml");
	}
	
	private void guardarNido(XML parent, int x, int y, int huevos) {
		parent.setInt("x", x);
		parent.setInt("y", y);
		XML child = parent.getChild("huevos");
		child.setIntContent(huevos);
	}
	
	private void guardarCauchera(XML parent, int x, int y, int rojos, int azules, int amarillos) {
		parent.setInt("xPalo", x);
		parent.setInt("yPalo", y);
		parent.setInt("xLanzar", x);
		parent.setInt("yLanzar", y);
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
		chargarCauchera(escenario.getChild("cauchera"));
		chargarNido(escenario.getChild("nido"));
	}
	
	private void chargarCauchera(XML parent) {
		xCauchera = parent.getInt("xPalo");
		yCauchera = parent.getInt("yPalo");
		xLanzar = parent.getInt("xLanzar");
		yLanzar = parent.getInt("yLanzar");
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

	public int getxLanzar() {
		return xLanzar;
	}

	public void setxLanzar(int xLanzar) {
		this.xLanzar = xLanzar;
	}

	public int getyLanzar() {
		return yLanzar;
	}

	public void setyLanzar(int yLanzar) {
		this.yLanzar = yLanzar;
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