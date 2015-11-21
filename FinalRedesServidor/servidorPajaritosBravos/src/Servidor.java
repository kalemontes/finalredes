import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class Servidor extends Thread implements Observer {

	private ServerSocket ss;
	private ArrayList<ControlCliente> clientes;

	public Servidor() {
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
		String mensajeRecibidoClient = (String) mensajeString;
		
		String[] instruccion_mensaje = mensajeRecibidoClient.split(":");
		String instruccion = instruccion_mensaje[0];
		
		if(instruccion.equalsIgnoreCase("JUGADOR")) {
			String nombre = instruccion_mensaje[1];
			//TODO: prepare la pantalla del servidor
			((ControlCliente)observado).enviarMensaje("JUGADOR:OK_ERES_EMPOLLADOR");
		}
		
		if (mensajeRecibidoClient.contains("login_req:")) {
			((ControlCliente)observado).enviarMensaje("Intento de login");
		}
		else if (mensajeRecibidoClient.contains("signup_req:")) {
			((ControlCliente)observado).enviarMensaje("Intento de signup");		
		}
		else if (mensajeRecibidoClient.contains("cliente_no_disponible")) {
			clientes.remove(observado);
			System.out.println("[ SE HA IDO UN CLIENTE, QUEDAN: " + clientes.size()+ " ]");
		}
		else if (mensajeRecibidoClient.contains("mensaje_send:")) {
			((ControlCliente)observado).enviarMensaje("Intento de mensaje_send");
			//System.out.println("[ SE HA IDO UN CLIENTE, QUEDAN: " + clientes.size()+ " ]");
		}
	}
}
