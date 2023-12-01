package Juego;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Servidor {
	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newCachedThreadPool();
		ServerSocket socket =null;
		Socket oConexion = null;

		
		HashMap<String, Jugador> jugadores = new HashMap<String,Jugador>();
		
		try {
			socket = new ServerSocket(6666);
			while (true) {
				try {
					oConexion = socket.accept();
		
					DataInputStream dis = new DataInputStream(oConexion.getInputStream());
					String nombre = dis.readLine();
					int puerto = dis.readInt();
					System.out.println(nombre);
					System.out.println(puerto);
					if (!jugadores.containsKey(nombre)) {
						
						jugadores.put(nombre, new Jugador(puerto,oConexion.getInetAddress().getHostAddress()));
					}
					
					System.out.println(jugadores.get(oConexion.getInetAddress().getHostAddress()));
					Peticion listaJugadores = new Peticion(oConexion, jugadores);
					pool.execute(listaJugadores);
					
				} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}finally {
			pool.shutdown();
		}
	}
	
}
