package Juego;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;

import setup.Constantes;

public class Cliente {
	public static void main(String[] args) throws ClassNotFoundException {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduce tu nombre");
		String nombre = entrada.nextLine();
		System.out.println("Introduce tu puerto");
		int puerto = entrada.nextInt();
		entrada.nextLine();
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
		entrada.nextLine();
		
		if (opcion == 1) {
			try (ServerSocket ss = new ServerSocket(puerto);) {

				while (true) {
					try (Socket s = ss.accept();
							ObjectOutputStream outputJugador1 = new ObjectOutputStream(s.getOutputStream());
							ObjectInputStream inputJugador1 = new ObjectInputStream(s.getInputStream());
							BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));) {
							
							System.out.print(br.readLine());
						
							Juego juego =new Juego();
							juego.colocarFichasIniciales();
							Interfaz i =new Interfaz(juego,Constantes.COLOR_BLANCO);
							i.mostrarTablero();
							
							while(true) {
								
								Juego nuevojuego = (Juego) inputJugador1.readObject();
								System.out.println(nuevojuego.getTurno());
								i.setJuego(nuevojuego);
								i.pintarTablero(nuevojuego);
								
								if(i.getJuego().comprobarMate()) {
									break;
								}
								System.out.print("es tu turno, mueven blancas");
								
							    try {
					                // Espera hasta que se mueva una ficha
							    	i.getBarrier().await();
									
									outputJugador1.writeObject(i.getJuego());
									outputJugador1.flush();
									outputJugador1.reset();
									
									System.out.print(i.getJuego().getTurno());
									System.out.print(i.getJuego().comprobarMate());
									
									if(i.getJuego().comprobarMate()) {
										break;
									}
	
					            } catch (BrokenBarrierException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							
							System.out.println("Patida Terminada");
							
							
							
							
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}else {
			System.out.println("Introduce la jugador con la que se desea conectar: ");
			String nombreAux = entrada.nextLine();
			Jugador jugador = jugadores.get(nombreAux);	
			try (Socket sPersonal = new Socket(jugadores.get(nombreAux).getIp(), jugadores.get(nombreAux).getPuerto());
							ObjectOutputStream outputJugador1 = new ObjectOutputStream(sPersonal.getOutputStream());
							ObjectInputStream inputJugador1 = new ObjectInputStream(sPersonal.getInputStream());
        	        		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sPersonal.getOutputStream()));) {
							
							bw.write("tu oponente es "+nombre + "\n");
							bw.flush();
							
							Juego juego =new Juego();
							juego.colocarFichasIniciales();
							Interfaz i =new Interfaz(juego,Constantes.COLOR_NEGRO);
							i.mostrarTablero();
							System.out.println("Ya estamos conectados");
							System.out.println("tu turno,eres negras");
							
							while(true) {
							    try {
					                // Espera hasta que se mueva una ficha
							    	i.getBarrier().await();
							    	
									System.out.println(i.getJuego().getTablero().getPiezaPosicion(1, 1));
									System.out.println(i.getJuego().getTablero().getPiezaPosicion(1, 2));
									
									outputJugador1.writeObject(i.getJuego());
									outputJugador1.flush();
									outputJugador1.reset();
									
									if(i.getJuego().comprobarMate()) {
										break;
									}
	
					            } catch (BrokenBarrierException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							    
							    
								Juego nuevojuego = (Juego) inputJugador1.readObject();
								System.out.println(nuevojuego.getTurno());
								i.setJuego(nuevojuego);
								i.pintarTablero(nuevojuego);
								
								if(i.getJuego().comprobarMate()) {
									break;
								}
								
							}
							
							
							System.out.println("Patida Terminada");
				
				
				
				
				
				
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
