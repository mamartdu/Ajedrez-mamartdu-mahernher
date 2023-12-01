package Fichas;

import Juego.Tablero;
import setup.Constantes;

public class Peon extends Pieza {

	public Peon(int color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public boolean movimientosPosibles(int xNuevo, int yNuevo, Tablero tablero) {
		int x = this.getX();
		int y = this.getY();
		if (this.getColor() == Constantes.COLOR_BLANCO) { // Blancas
			//int movPosibles = y == 7 ? 2 : 1;
			int movPosibles = 1;
			if (yNuevo < y) {
				if (y - yNuevo < movPosibles && tablero.getPiezaPosicion(xNuevo, yNuevo) != null) { // mover una hacia
																										// delante
					return true;
				} else if (x - xNuevo == 1 && (y - yNuevo == 1 || yNuevo - y == 1)) { // comer en diagonal
					if (tablero.getPiezaPosicion(xNuevo, yNuevo) != null) {
						if(tablero.getPiezaPosicion(xNuevo, yNuevo).getColor() == Constantes.COLOR_NEGRO) {
							return true;
						}
					}
				}
			}
		} else { // Negras
			//int movPosibles = y == 1 ? 2 : 1;
			int movPosibles = 1;
			if (yNuevo > y) {
				if (yNuevo - y < movPosibles && tablero.getPiezaPosicion(xNuevo, yNuevo) == null) { // mover una hacia
																										// delante
					return true;
				} else if (xNuevo - x == 1 && (y - yNuevo == 1 || yNuevo - y == 1)) { // comer en diagonal
					if (tablero.getPiezaPosicion(xNuevo, yNuevo) != null) {
						if(tablero.getPiezaPosicion(xNuevo, yNuevo).getColor() == Constantes.COLOR_BLANCO) {
							return true;
						}
					}
				}
			}
		}
		return false;

	}

}
