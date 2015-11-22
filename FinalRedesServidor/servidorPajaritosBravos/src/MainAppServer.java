import processing.core.PApplet;


public class MainAppServer extends PApplet{	
	Servidor server;	
	@Override
	public void setup() {		
		server = new Servidor(this);
		server.start();
		
		size(1080,720);
	    background(0);
	}
}
