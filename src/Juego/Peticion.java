package Juego;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;


public class Peticion implements Runnable {
    private Socket s;
    private HashMap<String, Jugador> jugadores;

    public Peticion(Socket s, HashMap<String, Jugador> _jugadores) {
        this.s = s;
        this.jugadores = _jugadores;
    }

    public void run() {
    	try (DataInputStream dis = new DataInputStream(s.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream())) {

           	  String nombre = dis.readLine();
                 int puerto = dis.readInt();
                 System.out.println(nombre + " : " + s.getInetAddress().getHostAddress() + ":" + puerto);
               
                 
               int opcion;
               while ((opcion = dis.readInt()) != 4) {
                   switch (opcion) {
                       case 1:
                           // Lógica para la opción 1, por ejemplo, enviar usuarios al cliente
                           out.writeObject(Servidor.getJugadoresActuales());
                           out.flush();
                           out.reset();
                           
                           break;
                       case 2:
                    	   Servidor.actualizaListado(s.getInetAddress().getHostAddress(),nombre,puerto);
                           break;
                       case 3:
                    	   	out.writeObject(Servidor.getJugadoresActuales());
                            out.flush();
                            out.reset();
                            String nombreAux = dis.readLine();
                            Servidor.eliminarListado(nombreAux);
                           break;   
                       default:
                           System.out.println("Opción no reconocida");
                           
                           break;
                   }
               }
               
               System.out.println("Desconectando a " + nombre);
               s.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
    }
}