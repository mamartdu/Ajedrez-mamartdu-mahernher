package Juego;

import Fichas.Alfil;
import Fichas.Caballo;
import Fichas.Dama;
import Fichas.Peon;
import Fichas.Rey;
import Fichas.Torre;
import setup.Constantes;

public class Juego{
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
			tablero.colocarPieza(new Peon(Constantes.COLOR_BLANCO,6,i));
			tablero.colocarPieza(new Peon(Constantes.COLOR_NEGRO,1,i));
		}
		tablero.colocarPieza(new Torre(Constantes.COLOR_NEGRO,0,0));
		tablero.colocarPieza(new Torre(Constantes.COLOR_NEGRO,0,7));
		tablero.colocarPieza(new Torre(Constantes.COLOR_BLANCO,7,0));
		tablero.colocarPieza(new Torre(Constantes.COLOR_BLANCO,7,7));

		tablero.colocarPieza(new Caballo(Constantes.COLOR_NEGRO,0,1));
		tablero.colocarPieza(new Caballo(Constantes.COLOR_NEGRO,0,6));
		tablero.colocarPieza(new Caballo(Constantes.COLOR_BLANCO,7,1));
		tablero.colocarPieza(new Caballo(Constantes.COLOR_BLANCO,7,6));


		tablero.colocarPieza(new Alfil(Constantes.COLOR_NEGRO,0,2));
		tablero.colocarPieza(new Alfil(Constantes.COLOR_NEGRO,0,5));
		tablero.colocarPieza(new Alfil(Constantes.COLOR_BLANCO,7,2));
		tablero.colocarPieza(new Alfil(Constantes.COLOR_BLANCO,7,5));

		tablero.colocarPieza(new Rey(Constantes.COLOR_NEGRO,0,3));
		tablero.colocarPieza(new Dama(Constantes.COLOR_NEGRO,0,4));
		tablero.colocarPieza(new Rey(Constantes.COLOR_BLANCO,7,3));
		tablero.colocarPieza(new Dama(Constantes.COLOR_BLANCO,7,4));
	}

	public int getTurno() {
		return turno;
	}
	
	public void cambiarTurno() {
		this.turno = this.turno == Constantes.COLOR_NEGRO ? Constantes.COLOR_BLANCO : Constantes.COLOR_NEGRO; 
	}
	
	public boolean isTurnoNegras() {
		return this.turno == Constantes.COLOR_NEGRO;
	}
	
	public boolean isTurnoBlancas() {
		return this.turno == Constantes.COLOR_BLANCO;
	}
	
}
