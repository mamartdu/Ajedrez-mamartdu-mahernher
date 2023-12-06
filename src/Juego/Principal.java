package Juego;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;

import setup.Constantes;

public class Principal {

	public static void main(String[] args) {
			Juego juego =new Juego();
			juego.colocarFichasIniciales();
			
			Tablero tablero = juego.getTablero();
			
			//Peon
			//System.out.println("---Peon---");
	
			//System.out.println(tablero.getPiezaPosicion(1,1).movimientosPosibles(1,2, tablero));
			/*
			tablero.moverPieza(tablero.getPiezaPosicion(1,1), 1, 3);
			System.out.println(tablero.getPiezaPosicion(1,3).movimientosPosibles(2,4, tablero));
			tablero.moverPieza(tablero.getPiezaPosicion(2,6), 2, 4);
			System.out.println(tablero.getPiezaPosicion(1,3).movimientosPosibles(2,4, tablero));//comer 
			
			//Rey
			System.out.println("---rey---");
			System.out.println(tablero.getPiezaPosicion(3,0).movimientosPosibles(4,0, tablero));
			System.out.println(tablero.getPiezaPosicion(3,0).movimientosPosibles(3,1, tablero));
			tablero.moverPieza(tablero.getPiezaPosicion(3,1), 3, 5);
			System.out.println(tablero.getPiezaPosicion(3,0).movimientosPosibles(3,1, tablero));
			
			tablero.moverPieza(tablero.getPiezaPosicion(3,7), 3, 4);
			tablero.moverPieza(tablero.getPiezaPosicion(3,0), 3, 3);
			System.out.println(tablero.getPiezaPosicion(3,3).movimientosPosibles(3,4, tablero));//comer 
			System.out.println(tablero.getPiezaPosicion(3,3).movimientosPosibles(4,3, tablero));
			System.out.println(tablero.getPiezaPosicion(3,3).movimientosPosibles(3,5, tablero));
			System.out.println(tablero.getPiezaPosicion(3,3).movimientosPosibles(2,2, tablero));
			System.out.println(tablero.getPiezaPosicion(3,3).movimientosPosibles(4,4, tablero));*/
			
	
			System.out.println("---Peon---");
			Interfaz i =new Interfaz(juego,Constantes.COLOR_NEGRO);
			i.mostrarTablero();
			/*System.out.println(tablero.getPiezaPosicion(1,1).movimientosPosibles(1,2, tablero));
			tablero.moverPieza(tablero.getPiezaPosicion(1,1), 1, 2);*/
			/*System.out.print(tablero.getPiezaPosicion(1,1));
			System.out.print(tablero.getPiezaPosicion(1,2));
			i.pintarTablero(juego);*/
			/*while(i.getJuego().getTurno() == Constantes.COLOR_NEGRO) {
			
			}*/
	
			while(true) {
			    try {
	                // Espera hasta que se haga clic en el botón
			    	i.getBarrier().await();
	                System.out.println("El botón ha sido clicado. Continuar con la ejecución.");

	            } catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	
				System.out.println("");
				
				System.out.println(i.getJuego().getTablero().getPiezaPosicion(1, 1));
				System.out.println(i.getJuego().getTablero().getPiezaPosicion(1, 2));
			}
        
			
			
			
			
	}

}
