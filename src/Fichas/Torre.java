package Fichas;

import Juego.Tablero;

public class Torre extends Pieza {

	public Torre(int color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public boolean movimientosPosibles(int xNuevo, int yNuevo, Tablero t) {

		//Movimientos de una torre : Cruz
		boolean[][] tableroPosibles = new boolean[8][8];
		int xActual = super.getX();
		int yActual = super.getY();
		for (int i = 0; i < 8; i++) {
			tableroPosibles[i][yActual] = (i != xActual) ? true: false;
			tableroPosibles[xActual][i] = (i != yActual) ? true: false;
		}
		
		if(tableroPosibles[xNuevo][yNuevo] && sePuedeMover(xNuevo,yNuevo, t)) {
			if(t.getPiezaPosicion(xNuevo,yNuevo) == null || t.getPiezaPosicion(xNuevo,yNuevo).getColor() != getColor()) {
				return true;
			}
		}		
		
		return false;
	
	}

	private boolean sePuedeMover(int xNuevo, int yNuevo, Tablero tablero) {
		boolean sePuedeMover = true;
		
		if(getX() != xNuevo && getY() != yNuevo) {
			sePuedeMover = false;
		}	
		// Sabemos que se mueve en horizontal
		else if (getX() == xNuevo) {
			sePuedeMoverVertical(xNuevo,yNuevo,tablero);			
		}
		// Sabemos que se mueve en vertical
		else if (getY() == yNuevo) {
			sePuedeMoverHorizontal(xNuevo,yNuevo,tablero);			
		}

		return sePuedeMover;
	}
	
	private boolean sePuedeMoverVertical(int xNuevo, int yNuevo, Tablero tablero) {
		boolean sePuedeMover = true;
		boolean sumarCasillas = false;
		
		int casillas = Math.abs(yNuevo - getY()); 
		if (casillas == 0) {
			sePuedeMover = false;
			return sePuedeMover;
		}
		
		sumarCasillas = (yNuevo > getY()) ? true: false;
		for (int i = 1; i < casillas && sePuedeMover; i++) {
			if (sumarCasillas == true) {
				if (tablero.getPiezaPosicion(getX(),getY()+i) != null)
					sePuedeMover = false;
			} else {
				if (tablero.getPiezaPosicion(getX(),getY()-i) != null)
					sePuedeMover = false;
			}

		}
		
		return sePuedeMover;
	}
	
	
	private boolean sePuedeMoverHorizontal(int xNuevo, int yNuevo, Tablero tablero) {
		boolean sePuedeMover = true;
		boolean sumarCasillas = false;
		
		int casillas = Math.abs(xNuevo - getX());
		
		if (casillas == 0) {
			sePuedeMover = false;
			return sePuedeMover;
		}
		
		sumarCasillas = (xNuevo > getX()) ? true: false;
		for (int i = 1; i < casillas  && sePuedeMover ; i++) {
			if (sumarCasillas) {
				if (tablero.getPiezaPosicion(getX()+i,getY()) != null)
					sePuedeMover = false;
			} else {
				if (tablero.getPiezaPosicion(getX()-i,getY())!= null)
					sePuedeMover = false;
			}
		}
		
		return sePuedeMover;
	}
	

}
