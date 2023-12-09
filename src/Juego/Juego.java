package Juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Fichas.Alfil;
import Fichas.Caballo;
import Fichas.Reina;
import Fichas.Peon;
import Fichas.Pieza;
import Fichas.Rey;
import Fichas.Torre;
import setup.Constantes;

public class Juego implements Serializable{
	private Tablero tablero;
	private int turno;
	private Rey reyNegro;
	private Rey reyBlanco;
	 
	public Juego() {
		this.tablero=new Tablero();
		turno = Constantes.COLOR_NEGRO;
	}
	
	public Tablero getTablero() {
		return this.tablero;
	}
	
	public void colocarFichasIniciales() {
		for(int i=0;i<8;i++) {
			tablero.colocarPieza(new Peon(Constantes.COLOR_BLANCO,i,6));
			tablero.colocarPieza(new Peon(Constantes.COLOR_NEGRO,i,1));
		}
		tablero.colocarPieza(new Torre(Constantes.COLOR_NEGRO,7,0));
		tablero.colocarPieza(new Torre(Constantes.COLOR_BLANCO,0,7));
		tablero.colocarPieza(new Torre(Constantes.COLOR_NEGRO,0,0));
		tablero.colocarPieza(new Torre(Constantes.COLOR_BLANCO,7,7));

		tablero.colocarPieza(new Caballo(Constantes.COLOR_NEGRO,1,0));
		tablero.colocarPieza(new Caballo(Constantes.COLOR_NEGRO,6,0));
		tablero.colocarPieza(new Caballo(Constantes.COLOR_BLANCO,1,7));
		tablero.colocarPieza(new Caballo(Constantes.COLOR_BLANCO,6,7));


		tablero.colocarPieza(new Alfil(Constantes.COLOR_NEGRO,2,0));
		tablero.colocarPieza(new Alfil(Constantes.COLOR_NEGRO,5,0));
		tablero.colocarPieza(new Alfil(Constantes.COLOR_BLANCO,2,7));
		tablero.colocarPieza(new Alfil(Constantes.COLOR_BLANCO,5,7));

		tablero.colocarPieza(new Rey(Constantes.COLOR_NEGRO,3,0));
		tablero.colocarPieza(new Reina(Constantes.COLOR_NEGRO,4,0));
		tablero.colocarPieza(new Rey(Constantes.COLOR_BLANCO,3,7));
		tablero.colocarPieza(new Reina(Constantes.COLOR_BLANCO,4,7));
		
		reyNegro =  (Rey) tablero.getPiezaPosicion(3, 0);
		reyBlanco = (Rey) tablero.getPiezaPosicion(3, 7);
	}

	public int getTurno() {
		return turno;
	}
	
	public void cambiarTurno() {
		this.turno = (this.turno == Constantes.COLOR_NEGRO) ? Constantes.COLOR_BLANCO : Constantes.COLOR_NEGRO; 
	}
	
	public boolean isTurnoNegras() {
		return this.turno == Constantes.COLOR_NEGRO;
	}
	
	public boolean isTurnoBlancas() {
		return this.turno == Constantes.COLOR_BLANCO;
	}
	
	public boolean muevesPieza() {
		cambiarTurno();
		return true;
	}
	
	
	public boolean hayJaque() {
	    // Obtén las coordenadas del rey del jugador actual
	    Rey rey = (turno == Constantes.COLOR_NEGRO) ? reyNegro : reyBlanco;
	    int xRey = rey.getX();
	    int yRey = rey.getY();

	    // Obtén todas las piezas del oponente
	    List<Pieza> piezasOponente = obtenerPiezasOponente();

	    // Verifica si alguna pieza del oponente puede atacar al rey
	    for (Pieza pieza : piezasOponente) {
	        if (pieza.movimientosPosibles(xRey, yRey, tablero)) {
	            return true; // El rey está en jaque
	        }
	    }

	    return false; // El rey no está en jaque
	}

	private List<Pieza> obtenerPiezasOponente() {

	    if(turno == Constantes.COLOR_NEGRO ) {
	    	return tablero.getPiezasBlancas();
	    }else {
	    	return tablero.getPiezasNegras();
	    }
	   

	}
	
	private List<Pieza> obtenerPiezasJugadorActual() {

	    if(turno == Constantes.COLOR_NEGRO ) {
	    	return tablero.getPiezasNegras();
	    }else {
	    	return tablero.getPiezasBlancas();
	    }
	   

	}

	public boolean comprobarMate() {
	    // Obtener todas las piezas del jugador actual
	    List<Pieza> piezasJugador = obtenerPiezasJugadorActual();

	    // Iterar sobre todas las piezas y sus posibles movimientos
	    for (Pieza pieza : piezasJugador) {
	        for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	                // Verificar si el movimiento es posible y evita el jaque mate
	                if (pieza.movimientosPosibles(i, j, tablero) && !movimientoProvocaJaqueMate(pieza, i, j)) {
	                    // Existe al menos un movimiento que evita el jaque mate 
	                    return false;
	                    
	                }
	            }
	        }
	    }

	    // Si no hay movimientos posibles que eviten el jaque mate, es mate
	    return true;
	}
	
	
	private boolean movimientoProvocaJaqueMate(Pieza pieza, int xNuevo, int yNuevo) {
	    // Realizar el movimiento temporalmente
	    int xOriginal = pieza.getX();
	    int yOriginal = pieza.getY();
	    pieza.setX(xNuevo);
	    pieza.setY(yNuevo);

	    
	    // Verificar si el movimiento provoca jaque mate
	    boolean provocaJaqueMate = hayJaque();
	    
	

	    // Deshacer el movimiento y revertir el cambio de turno
	    pieza.setX(xOriginal);
	    pieza.setY(yOriginal);


	    return provocaJaqueMate;
	}
	


	



	
}
