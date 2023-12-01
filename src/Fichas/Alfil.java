package Fichas;

import Juego.Tablero;

public class Alfil extends Pieza {

	public Alfil(int color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public boolean movimientosPosibles(int xNuevo, int yNuevo, Tablero tablero) {

		// Movimientos de una torre : Diagonales
		boolean[][] tableroPosibles = new boolean[8][8];
		int xActual = super.getX();
		int yActual = super.getY();

		for (int i = 1; i < 8; i++) {
			// Movimientos en la diagonal inferior derecha
			if (xActual + i <= 7 && yActual + i <= 7) {
				tableroPosibles[xActual + i][yActual + i] = true;
			}
			// Movimientos en la diagonal superior derecha
			if (xActual - i >= 0 && yActual + i <= 7) {
				tableroPosibles[xActual - i][yActual + i] = true;
			}
			// Movimientos en la diagonal inferior izquierda
			if (xActual + i <= 7 && yActual - i >= 0) {
				tableroPosibles[xActual + i][yActual - i] = true;
			}
			// Movimientos en la diagonal superior izquierda
			if (xActual - i >= 0 && yActual - i >= 0) {
				tableroPosibles[xActual - i][yActual - i] = true;
			}
		}

		if (tableroPosibles[xNuevo][yNuevo] == true && sePuedeMover(xNuevo, yNuevo, tablero)) {
			if (tablero.getPiezaPosicion(xNuevo, yNuevo) == null
					|| tablero.getPiezaPosicion(xNuevo, yNuevo).getColor() != this.getColor()) {
				return true;
			}
		}
		return false;

	}

	public boolean sePuedeMover(int xNuevo, int yNuevo, Tablero tablero) {
		boolean vacio = true;

		int casillas = Math.abs(xNuevo - this.getX());

		for (int i = 1; i < casillas && vacio; i++) {
				
		    int xDesplazado = this.getX() + i;
		    int yDesplazado = this.getY() + i;

		    // Movimientos en la diagonal superior izquierda
		    if (this.getX() > xNuevo && this.getY() > yNuevo && tablero.getPiezaPosicion(this.getX() - i, this.getY() - i) != null) {
		        vacio = false;
		    } 
		    
		    // Movimientos en la diagonal superior derecha
		    else if (this.getX() > xNuevo && this.getY() <= yNuevo && tablero.getPiezaPosicion(this.getX() - i, this.getY() + i) != null) {
		        vacio = false;
		    } 
		    
		    // Movimientos en la diagonal inferior izquierda
		    else if (this.getX() <= xNuevo && this.getY() > yNuevo && tablero.getPiezaPosicion(xDesplazado, this.getY() - i) != null) {
		        vacio = false;
		    } 
		    
		    // Movimientos en la diagonal inferior derecha
		    else if (this.getX() <= xNuevo && this.getY() <= yNuevo && tablero.getPiezaPosicion(xDesplazado, yDesplazado) != null) {
		        vacio = false;
		    }			
		}

		return vacio;

	}

}
