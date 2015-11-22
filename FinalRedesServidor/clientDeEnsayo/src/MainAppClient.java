import java.util.Observable;
import java.util.Observer;

public class MainAppClient {
	public static void main(String[] args){
		Comunicacion.getInstance().addObserver(new Observer() {
			
			@Override
			public void update(Observable o, Object data) {
				String mensaje = (String) data;
		        switch (mensaje) {
			        case "no_conectado":
			        	System.out.println("No se ha podido establecer conexión");
		                break;
		            case "conectado":
		            	System.out.println("Conexión establecida");
		            	Comunicacion.getInstance().enviar("CONEXION_JUGADOR:JUGADOR_1:");
		                break;
		            default:
		                break;
		        }
			}
		});
	}
}
