package Juego;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduce tu nombre");
		String nombre = entrada.nextLine();
		System.out.println("Introduce tu puerto");
		int puerto = entrada.nextInt();
		HashMap<String, Jugador> jugadores = null;

		try (Socket s = new Socket("localhost", 6666);
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());) {
			dos.writeBytes(nombre+"\n");
			dos.writeInt(puerto);
			dos.flush();
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			
			jugadores = (HashMap<String, Jugador>) in.readObject();	 	        
			
			System.out.println("JUGADORES CONECTADOS:");
			for (String clave : jugadores.keySet()) {
			    System.out.println(clave);
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
			try (ServerSocket ss = new ServerSocket(puerto);) {

				while (true) {
					try (Socket s = ss.accept();) {
						ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
						ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
						
						System.out.println(ois.readLine());
					
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}else {
			System.out.println("Introduce la jugador con la que se desea conectar: ");
			entrada.next();
			String nombreAux = entrada.nextLine();
			Jugador jugador = jugadores.get(nombreAux);
			System.out.print(jugador);			
			try (Socket sPersonal = new Socket(jugadores.get(nombreAux).getIp(), jugadores.get(nombreAux).getPuerto());
							ObjectOutputStream oos = new ObjectOutputStream(sPersonal.getOutputStream());
							ObjectInputStream ois = new ObjectInputStream(sPersonal.getInputStream());) {
				
				oos.writeBytes("me conecto\n");
				
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
