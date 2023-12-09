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


		try (Socket s = new Socket("localhost", 6666);
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(s.getInputStream());) {
			
			Scanner entrada = new Scanner(System.in);
			System.out.println("Introduce tu nombre");
			String nombre = entrada.nextLine();
			System.out.println("Introduce tu puerto");
			int puerto = entrada.nextInt();
			entrada.nextLine();
			HashMap<String, Jugador> jugadores = null;
			
			dos.writeBytes(nombre+"\n");
			dos.writeInt(puerto);
			dos.flush();
			

            int opcion = -1;
            while (opcion != 4) {
                // Resto del código para mostrar el menú y obtener la opción
            	   System.out.println("----------");
            	   System.out.println("Elige opción:");
                   System.out.println("1- Mostrar Usuarios");
                   System.out.println("2- Esperar a partida");
                   System.out.println("3- Conectarse a usuario para jugar una partida");
                   System.out.println("4- Desconectarse");
                   System.out.println("----------");
                   opcion = entrada.nextInt();
                   dos.writeInt(opcion);
                   dos.flush();
                   entrada.nextLine();

                switch (opcion) {
                    case 1:
                        // Lógica para la opción 1, por ejemplo, recibir y mostrar usuarios
            			jugadores = (HashMap<String, Jugador>) in.readObject();	 	        
            			
            			System.out.println("JUGADORES CONECTADOS:");
            			for (String clave : jugadores.keySet()) {
            			    System.out.println(clave);
            			}
                        break;
                    case 2:
                    	try (ServerSocket ss = new ServerSocket(puerto);) {

        					try (Socket sJugador = ss.accept();
        							ObjectOutputStream outputJugador1 = new ObjectOutputStream(sJugador.getOutputStream());
        							ObjectInputStream inputJugador1 = new ObjectInputStream(sJugador.getInputStream());
        							BufferedReader br = new BufferedReader(new InputStreamReader(sJugador.getInputStream()));) {
        							
        							System.out.println(br.readLine());
        						
        							Juego juego =new Juego();
        							juego.colocarFichasIniciales();
        							Interfaz i =new Interfaz(juego,Constantes.COLOR_BLANCO);
        							i.mostrarTablero();
        							
        							while(true) {
        								
        								Juego nuevojuego = (Juego) inputJugador1.readObject();
        								i.setJuego(nuevojuego);
        								i.pintarTablero(nuevojuego);
        								
        								if(i.getJuego().comprobarMate()) {
    										System.out.println("Jaque mate, ganan negras");
    										i. terminar();
        									break;
        								}
        								System.out.println("es tu turno, mueven blancas");
        								
        							    try {
        					                // Espera hasta que se mueva una ficha
        							    	i.getBarrier().await();
        									
        									outputJugador1.writeObject(i.getJuego());
        									outputJugador1.flush();
        									outputJugador1.reset();
        									
        									if(i.getJuego().comprobarMate()) {
        										System.out.println("Jaque mate, ganan blancas");
        										i. terminar();
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
        							
        							System.out.println("****");
        							System.out.println("Patida Terminada");
        							System.out.println("****");
        							
        							
        							
        					} catch (IOException e) {
        						e.printStackTrace();
        					}
        				

        			} catch (IOException e) {
        				e.printStackTrace();
        			}
                    break;
                    case 3:
                    	jugadores = (HashMap<String, Jugador>) in.readObject();	 	        
            			
            			System.out.println("JUGADORES CONECTADOS:");
            			for (String clave : jugadores.keySet()) {
            			    System.out.println(clave);
            			}
                    	System.out.println("Introduce el jugador con el que se desea conectar: ");
            			String nombreAux = entrada.nextLine();
            			Jugador jugador = jugadores.get(nombreAux);	
            			if(jugador == null) {
            				System.out.print("jugador no encotrado");
            			}else {
            				try (Socket sPersonal = new Socket(jugadores.get(nombreAux).getIp(), jugadores.get(nombreAux).getPuerto());
        							ObjectOutputStream outputJugador1 = new ObjectOutputStream(sPersonal.getOutputStream());
        							ObjectInputStream inputJugador1 = new ObjectInputStream(sPersonal.getInputStream());
                	        		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sPersonal.getOutputStream()));) {
        							
		            				dos.writeBytes(nombreAux+"\n");        				
		            				dos.flush();
            					
            					
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
        									
        									outputJugador1.writeObject(i.getJuego());
        									outputJugador1.flush();
        									outputJugador1.reset();
        									
        									if(i.getJuego().comprobarMate()) {
        										System.out.println("Jaque mate, ganan negras");
        										i. terminar();
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
        								i.setJuego(nuevojuego);
        								i.pintarTablero(nuevojuego);
        								
        								
        								if(i.getJuego().comprobarMate()) {
        									System.out.println("Jaque mate, ganan blancas");
        									i. terminar();
        									break;
        								}
        								System.out.println("es tu turno, mueven negras");
        								
        								
        							}
        							
        							System.out.println("****");
        							System.out.println("Patida Terminada");
        							System.out.println("****");
        				
        			} catch (UnknownHostException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
            			}
            	
                    	break;
                        
                    case 4:
                    	s.close();
                        System.out.println("Desconectando");
                        break;
                    default:
                        System.out.println("Número no reconocido");
                        break;
                }
            }
			


		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
