package Juego;

import java.io.Serializable;

import Fichas.Alfil;
import Fichas.Caballo;
import Fichas.Reina;
import Fichas.Peon;
import Fichas.Rey;
import Fichas.Torre;
import setup.Constantes;

public class Juego implements Serializable{
	private Tablero tablero;
	private int turno;
	
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
		//Dentro de muevesPieza , cambiamos turno
		tablero.moverPieza(new Reina(Constantes.COLOR_BLANCO,4,7),4,5);
		cambiarTurno();
		return true;
	}
	
	
}
