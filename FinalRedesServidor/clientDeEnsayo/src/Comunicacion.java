
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Observable;

/**
 * Created by josemoncada87 on 14/10/2015. *
 */
public class Comunicacion extends Observable implements Runnable {
	
    private static Comunicacion ref;
    private Socket s;
    private boolean corriendo;
    private String ip = "127.0.0.1";
    private int puerto = 5000;
    private boolean conectando;
    private boolean reset;
    private boolean notificoError;

    private Comunicacion() {
        s = null;
        corriendo = true;
        conectando = true;
        reset = false;
        System.out.println("[ INSTACIA DE COMUNICACION CONSTRUIDA ]");
        notificoError = false;
    }

    public static Comunicacion getInstance() {
        if(ref == null){
            ref =  new Comunicacion();
            Thread t =  new Thread(ref);
            t.start();
        }
        return ref;
    }


    @Override
    public void run() {
    	System.out.println("[ HILO DE COMUNICACION INICIADO ]");
        while (corriendo) {
            try {
                if (conectando) {
                    if (reset) {
                        if (s != null) {
                            try {
                                s.close();
                            } catch (IOException e) {
                            	System.out.println("[ SE RESETEA LA CONEXION CON EL SERVIDOR ]");
                                //e.printStackTrace();
                            } finally {
                                s = null;
                            }
                        }
                        reset = false;
                    }
                    conectando = !conectar();
                } else {
                    if (s != null) {
                        recibir();
                    }
                }
                Thread.sleep(500);
            } catch (SocketTimeoutException e) {
                //Log.d(TAG, "[ SE PERDIÓ LA CONEXIÓN CON EL SERVIDOR ]");
                //corriendo = false;
            } catch (IOException e) {
            	System.out.println("[ SE PERDIO LA CONEXION CON EL SERVIDOR ]");
                //notifyObservers("no_conectado");
                //clearChanged();
                //corriendo = false;
                reintentar();
            } catch (InterruptedException e) {
                // e.printStackTrace();
                setChanged();
                System.out.println("[ INTERRUPCION ]");
            }
        }

        try {
            s.close();
        } catch (IOException e) {
            //e.printStackTrace();
        	System.out.println("[ ERROR CERRANDO EL SOCKET ]");
        } finally {
            s = null;
        }
    }

    private boolean conectar() {
        try {
            InetAddress dirServidor = InetAddress.getByName(ip);
            s = new Socket(dirServidor, puerto);
            s.setSoTimeout(500);
            System.out.println("[ CONECTADO CON: " + s.toString() + " ]");
            setChanged();
            notifyObservers("conectado");
            clearChanged();
        } catch (UnknownHostException e) {
            //e.printStackTrace();
        	System.out.println("[ SERVIDOR DESCONOCIDO ]");
            return false;
        } catch (IOException e) {
            //e.printStackTrace();
        	System.out.println("[ ERROR AL ESTABLECER LA CONEXION ]");
            if (!notificoError) {
                setChanged();
                notifyObservers("no_conectado");
                clearChanged();
                notificoError = true;
            }
            return false;
        }
        return true;
    }

    private void recibir() throws IOException {
        DataInputStream dis = new DataInputStream(s.getInputStream());
        String recibido = dis.readUTF();
        manejarLogin(recibido);
        manejarSignup(recibido);
        manejarMensaje(recibido);
    }

    private void manejarMensaje(String recibido) {
        if (recibido.contains("mensaje_send:")) {
            setChanged();
            notifyObservers(recibido);
            clearChanged();
        }
    }

    private void manejarSignup(String recibido) {
        if (recibido.contains("signup_resp:")) {
            String[] partes = recibido.split(":");
            int resultado = Integer.parseInt(partes[1]);
            setChanged();
            switch (resultado) {
                case 0:
                    notifyObservers("usuario_existe");
                    break;
                case 1:
                    notifyObservers("usuario_registrado");
                    break;
            }
            clearChanged();
        }
    }

    private void manejarLogin(String recibido) {
        if (recibido.contains("login_resp:")) {
            String[] partes = recibido.split(":");
            int resultado = Integer.parseInt(partes[1]);
            setChanged();
            switch (resultado) {
                case 0:
                    notifyObservers("usuario_no_existe");
                    break;
                case 1:
                    notifyObservers("login_ok");
                    break;
                case 2:
                    notifyObservers("login_no_ok");
                    break;
            }
            clearChanged();
        }
    }

    public void enviar(String msn) {
        if(s != null) {
            try {
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                dos.writeUTF(msn);
                dos.flush();
            } catch (IOException e) {
                //e.printStackTrace();
            	System.out.println("[ ERROR AL ENVIAR ]");
            }
        }else{
            setChanged();
            notifyObservers("no_conectado");
            clearChanged();
        }
    }

    public void reintentar() {
        conectando = true;
        reset = true;
        notificoError = false;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
}
