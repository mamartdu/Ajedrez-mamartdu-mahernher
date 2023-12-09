package Juego;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Servidor {
	
	private static HashMap<String, Jugador> jugadores = new HashMap<String,Jugador>();
	
	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newCachedThreadPool();


		
		try (ServerSocket serverSocket = new ServerSocket(6666);){
			
	
				while (true) {
			        try {
			            Socket connection = serverSocket.accept();     
			                    // Crear un nuevo hilo para manejar la conexión del cliente
			                    pool.execute(new Peticion(connection, jugadores));
			                } catch (IOException e) {
			                    e.printStackTrace();
			                }
			            }
					
				} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}finally {
        			pool.shutdown();
        		}
			
	}

	public static synchronized HashMap<String, Jugador> getJugadoresActuales() {
		return jugadores;
	}
	
	public static synchronized void actualizaListado(String ip,String n, int puerto) {
	       if (!jugadores.containsKey(n)) {
	    	   jugadores.put(n, new Jugador(puerto, ip));
           }
	}
	
	public static synchronized void eliminarListado(String n) {
	       if (jugadores.containsKey(n)) {
	    	   jugadores.remove(n);
	       }
	}

	
}
