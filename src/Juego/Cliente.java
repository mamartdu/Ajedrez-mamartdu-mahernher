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
		System.out.println("Introduce tu puerto");
		int puerto1 = entrada.nextInt();

		try (Socket s = new Socket("localhost", 6666);
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());) {
			dos.writeInt(puerto1);
			dos.flush();
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			
			HashMap<String, HashMap<String,Integer>> jugadores = new HashMap<String, HashMap<String,Integer>>();		        
			
			System.out.println("JUGADORES CONECTADOS:");
			for (String clave : jugadores.keySet()) {
			    System.out.println(clave);
			}
			
			s.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
