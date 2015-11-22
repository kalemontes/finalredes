import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Servidor extends Thread implements Observer {

	private MainAppServer applet;
	private ServerSocket ss;
	private ArrayList<ControlCliente> clientes;

	public Servidor(MainAppServer app) {
		applet = app;
		clientes = new ArrayList<ControlCliente>();
		try {
			ss = new ServerSocket(5000);
			System.out.println("[ SERVIDOR INICIADO EN: "+ss.toString()+" ]");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("[ ESPERANDO CLIENTE ]");
				clientes.add(new ControlCliente(ss.accept(), this));
				System.out.println("[ NUEVO CLIENTE ES: " + clientes.get(clientes.size()-1).toString() + " ]");
				System.out.println("[ CANTIDAD DE CLIENTES: " + clientes.size() + " ]");
				sleep(100);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Observable observado, Object mensajeString) {
		
		//Cuando recibo un mensaje de cualquiera
		String mensajeRecibidoClient = (String) mensajeString;
		
		//Necesito extraer el contido del mensaje que est del estilo siguiente "INSTRUCCION:MENSAGE:OTROS_PARAMETROS"
		String[] instruccion_mensaje = mensajeRecibidoClient.split(":");
		String instruccion = instruccion_mensaje[0];
		
		//"CONEXION_JUGADOR:jugardo1:"
		if(instruccion.equalsIgnoreCase("CONEXION_JUGADOR")) {
			String nombre = instruccion_mensaje[1]; //por ejemplo jugardo1
			System.out.println("Bienvenido jugador : "+nombre); //TODO: esto lo tenemos que presentar en el televisor
			
			if(nombre.equalsIgnoreCase("JUGADOR_1")) {
				PajaroRojo p1 = new PajaroRojo(applet, applet.loadImage("../libs/rojo.png"), 10, 500);
				applet.agregarAlLienzo(p1);
				
				((ControlCliente)observado).enviarMensaje("ROL_JUGADOR:EMPOLLADOR");
			} 
			else if(nombre.equalsIgnoreCase("JUGADOR_2")) {
				PajaroRojo p1 = new PajaroRojo(applet, applet.loadImage("../libs/rojo.png"), 10, 300);
				applet.agregarAlLienzo(p1);
				
				((ControlCliente)observado).enviarMensaje("ROL_JUGADOR:CERDOKILLER");
			}
			else if(nombre.equalsIgnoreCase("JUGADOR_3")) {
				((ControlCliente)observado).enviarMensaje("ROL_JUGADOR:MONJA");
			}
			
			if(clientes.size() >= 3) { //FIXME deberiamos guardar refencia de que cada typo de jugar realmente se conecto
				//TODO: presentar el escenario en la pantalla del televisor
				for(ControlCliente cliente : clientes) {
					cliente.enviarMensaje("INICIO_AUTORIZADO:LISTO_PAPA!!");
				}
			}
		}
		//"ACCION_JUGADOR:CERDOKILLER:LANZAR_AZUL"
		else if(instruccion.equalsIgnoreCase("ACCION_JUGADOR")) {
			String rolJugador = instruccion_mensaje[1];
			String accion = instruccion_mensaje[2]; //por ejemplo empezar a empollar o lanzar pajarito azul
			
			if(rolJugador.equalsIgnoreCase("EMPOLLADOR")) {
				if(accion.equalsIgnoreCase("RECIBIR_HUEVO")) {
					
				}
				else if(accion.equalsIgnoreCase("DESMPOLLAR")) {
					
				}
			}
			else if(rolJugador.equalsIgnoreCase("CERDOKILLER")) {
				if(accion.equalsIgnoreCase("LANZAR_AZUL")) {
					
				}
				else if(accion.equalsIgnoreCase("LANZAR_AMARILLO")) {
					
				}
				else if(accion.equalsIgnoreCase("LANZAR_ROJO")) {
					
				}
			}
			else if(rolJugador.equalsIgnoreCase("MONJA")) {
				if(accion.equalsIgnoreCase("RESCATAR")) {
					
					//if
					((ControlCliente)observado).enviarMensaje("ACCION_MONJA:HUEVO_RESCATADO");
				}
			}
		}
	}
}
