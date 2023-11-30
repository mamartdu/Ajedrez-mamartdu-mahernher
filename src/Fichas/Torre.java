package Fichas;

import Juego.Tablero;

public class Torre extends Pieza {

	public Torre(int color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public boolean movimientosPosibles(int xNuevo, int yNuevo, Tablero t) {
		boolean[][] b = new boolean[8][8];
		int x = super.getX();
		int y = super.getY();
		for (int i = 0; i < 8; i++) {
			if (i != x) {
				b[i][y] = true;
			}
			if (i != y) {
				b[x][i] = true;
			}

		}
	
		return false;
	
	}

	private boolean sePuedeMover(int xNuevo, int yNuevo, Tablero t) {
		boolean sePuedeMover = true;
		
		if(getX() != xNuevo && getY() != yNuevo) {
			sePuedeMover = false;
		}	
		// Sabemos que se mueve en horizontal
		else if (getX() == xNuevo) {
			sePuedeMoverVertical(xNuevo,yNuevo,t);			
		}
		// Sabemos que se mueve en vertical
		else if (getY() == yNuevo) {
			sePuedeMoverHorizontal(xNuevo,yNuevo,t);			
		}

		return sePuedeMover;
	}
	
	private boolean sePuedeMoverVertical(int xNuevo, int yNuevo, Tablero t) {
		boolean sePuedeMover = true;
		boolean sumarCasillas = false;
		
		int casillas = Math.abs(yNuevo - getY()); 
		if (casillas == 0) {
			sePuedeMover = false;
			return sePuedeMover;
		}
		
		sumarCasillas = (yNuevo > getY()) ? true: false;
		for (int i = 1; i < casillas && sePuedeMover == true; i++) {
			if (sumarCasillas == true) {
				if (t.getPiezaPosicion(getX(),getY()+i) != null)
					sePuedeMover = false;
			} else {
				if (t.getPiezaPosicion(getX(),getY()-i) != null)
					sePuedeMover = false;
			}

		}
		
		return sePuedeMover;
	}
	
	
	private boolean sePuedeMoverHorizontal(int xNuevo, int yNuevo, Tablero t) {
		boolean sePuedeMover = true;
		boolean sumarCasillas = false;
		
		int casillas = Math.abs(xNuevo - getX());
		
		if (casillas == 0) {
			sePuedeMover = false;
			return sePuedeMover;
		}
		
		sumarCasillas = (xNuevo > getX()) ? true: false;
		for (int i = 1; i < casillas ; i++) {
			if (sumarCasillas) {
				if (t.getPiezaPosicion(getX()+i,getY()) != null)
					sePuedeMover = false;
			} else {
				if (t.getPiezaPosicion(getX()-i,getY())!= null)
					sePuedeMover = false;
			}
		}
		
		return sePuedeMover;
	}
	

}
