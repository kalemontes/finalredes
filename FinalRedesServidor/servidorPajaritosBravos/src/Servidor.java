import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Servidor extends Thread implements Observer {

	private MainAppServer applet;
	private ServerSocket ss;
	private ArrayList<ControlCliente> clientes;
	private EstadoJuego estadoJuego;
	

	public Servidor(MainAppServer app) {
		applet = app;
		clientes = new ArrayList<ControlCliente>();
		estadoJuego = new EstadoJuego(applet);
		
		try {
			ss = new ServerSocket(5000);
			System.out.println("[ SERVIDOR INICIADO EN: "+ss.toString()+" ]");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public EstadoJuego getEstadoJuego() {
		return estadoJuego;
	}
	
	public int clientesConectados() {
		return clientes.size();
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
		
		//"CONEXION_JUGADOR:JUGADOR_1:"
		if(instruccion.equalsIgnoreCase("CONEXION_JUGADOR")) {
			String nombre = instruccion_mensaje[1]; //por ejemplo jugardo1
			System.out.println("Bienvenido jugador : "+nombre); //TODO: esto lo tenemos que presentar en el televisor
			
			if(nombre.equalsIgnoreCase("JUGADOR_1")) {
				((ControlCliente)observado).enviarMensaje("ROL_JUGADOR:EMPOLLADOR");
			} 
			else if(nombre.equalsIgnoreCase("JUGADOR_2")) {
				((ControlCliente)observado).enviarMensaje("ROL_JUGADOR:CERDOKILLER");
			}
			else if(nombre.equalsIgnoreCase("JUGADOR_3")) {
				((ControlCliente)observado).enviarMensaje("ROL_JUGADOR:MONJA");
			}
			
			if(clientes.size() >= 3) { //FIXME deberiamos guardar refencia de que cada typo de jugar realmente se conecto
				//TODO: presentar el escenario en la pantalla del televisor
				applet.iniciarJuego(estadoJuego);
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
					if(applet.huevosEnElNido.size()>0){
					int random = (int) applet.random(0,4);
					switch (random){
					case 0:						
						break;
					case 1:
						PajaroRojo pajaroRojoLanzar = new PajaroRojo(applet, estadoJuego.getxLanzar(), estadoJuego.getyLanzar());
						applet.agregarALaCaucheraRoja(pajaroRojoLanzar);
						applet.huevosEnElNido.remove(0);
						break;
					case 2:
						PajaroAzul pajaroAzulLanzar = new PajaroAzul(applet, estadoJuego.getxLanzar(), estadoJuego.getyLanzar());
						applet.agregarALaCaucheraAzul(pajaroAzulLanzar);
						applet.huevosEnElNido.remove(0);
						break;
					case 3:
					PajaroAmarillo pajaroAmarilloLanzar = new PajaroAmarillo(applet, estadoJuego.getxLanzar(), estadoJuego.getyLanzar());
					applet.agregarALaCaucheraAmarilla(pajaroAmarilloLanzar);
					applet.huevosEnElNido.remove(0);
						break;
					case 4:
						break;
						}
					}
				}
			}
			else if(rolJugador.equalsIgnoreCase("CERDOKILLER")) {
				
			}
				if(accion.equalsIgnoreCase("LANZAR_AZUL")) {
					if(applet.pajaroAzulListo.size()>0){
						PajaroAzul pa= applet.pajaroAzulListo.get(0);
						pa.setNosPodemosMover(true);
						applet.pajarosEnElLienzo.add(pa);
						applet.pajaroAzulListo.remove(pa);
					}
				}
				else if(accion.equalsIgnoreCase("LANZAR_AMARILLO")) {
					if(applet.pajaroAmarilloListo.size()>0){
						PajaroAmarillo pama= applet.pajaroAmarilloListo.get(0);
						pama.setNosPodemosMover(true);
						applet.pajarosEnElLienzo.add(pama);
						applet.pajaroAmarilloListo.remove(pama);
					}
				}
				else if(accion.equalsIgnoreCase("LANZAR_ROJO")) {
					if(applet.pajaroRojoListo.size()>0){
						PajaroRojo pr= applet.pajaroRojoListo.get(0);
						pr.setNosPodemosMover(true);
						applet.pajarosEnElLienzo.add(pr);
						applet.pajaroAmarilloListo.remove(pr);	
				}
			}
			else if(rolJugador.equalsIgnoreCase("MONJA")) {
				if(accion.equalsIgnoreCase("RESCATAR")) {
					if(applet.huevosEnElLienzo.size() > 0) {
						Huevo huevoRescatado = applet.huevosEnElLienzo.get(0);
						applet.baronNegro.rescatarHuevo(huevoRescatado);
						((ControlCliente)observado).enviarMensaje("ACCION_MONJA:HUEVO_RESCATADO");
			
					}
					else {
						applet.baronNegro.volverAlNido();
						((ControlCliente)observado).enviarMensaje("ACCION_MONJA:HUEVO_PERDIDO");
					}
				}
			}
			
			if(accion.equalsIgnoreCase("GUARDAR_JUEGO")) {
				estadoJuego.setAmarillos(applet.pajaroAmarilloListo.size());
				estadoJuego.setRojos(applet.pajaroRojoListo.size());
				estadoJuego.setAzules(applet.pajaroAzulListo.size());
				estadoJuego.setHuevosNido(applet.huevosEnElNido.size());
				estadoJuego.guardar();
			}
			else if(accion.equalsIgnoreCase("RECARGAR_JUEGO")) {
				estadoJuego.chargar();
			}
		}
	}
}
