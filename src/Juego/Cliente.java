package Juego;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduce tu nombre");
		String nombre = entrada.nextLine();
		System.out.println("Introduce tu puerto");
		int puerto = entrada.nextInt();
		
		try (Socket s = new Socket("localhost", 6666);
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());) {
			dos.writeBytes(nombre+"\n");
			dos.writeInt(puerto);
			dos.flush();
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			
			HashMap<String, Jugador> jugadores = (HashMap<String, Jugador>) in.readObject();	        
			
			System.out.println("JUGADORES CONECTADOS:");
			for (String clave : jugadores.keySet()) {
			    System.out.println(clave);
			}
			
			System.out.println("Crear mi partida pulse 1");
			System.out.println("Unirse a un jugador pulse 2");
			String opcion = entrada.nextLine();
			
			switch (opcion) {
				case "1":
					
					try (ServerSocket ss = new ServerSocket(puerto);
						ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
						ObjectInputStream ois = new ObjectInputStream(s.getInputStream());) {
						
						Juego juego = new Juego();
						Interfaz i =new Interfaz(juego);
						i.mostrarTablero();
						
						
					}
				case "2":
			}
			
			s.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(
				"Si desea esperar a que alguien le solicite una partida pulse 1 y si desea jugar con un usuario conectado concreto pulse 2: ");
		int opcion = entrada.nextInt();
		
		if (opcion == 1) {
			System.out.println("Introduce la ip con la que se desea conectar: ");
			String ip = entrada.nextLine();
			ip = entrada.nextLine();
			System.out.println("Introduzca su puerto:");
			int puerto = entrada.nextInt();
			
			try (Socket miSocket = new Socket(ip, puerto);
				ObjectOutputStream outputJugador1 = new ObjectOutputStream(miSocket.getOutputStream());
				ObjectInputStream inputJugador1 = new ObjectInputStream(miSocket.getInputStream());) {
				
				System.out.println("Ya estamos conectados");
				Juego juego = new Juego();
				juego.colocarFichasIniciales();
				Interfaz tablero = new Interfaz(juego);
				
				while(!tablero.finPartida()) {	
					
					outputJugador1.writeObject(tablero);
					tablero = (Interfaz) inputJugador1.readObject();
					if(juego.isTurnoNegras()) {
						//MUEVEN NEGRAS
						juego.muevesPieza();	
						tablero.bloquear();
						outputJugador1.writeObject(tablero);
						outputJugador1.flush();
					}
					
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}else {
			
		}
	}
}
