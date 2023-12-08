package Fichas;

import Juego.Tablero;
import setup.Constantes;

public class Peon extends Pieza {
	
	private int xInical;
	private int yInical;
	
	
	public Peon(int color, int x, int y) {
		super(color, x, y);
		this.xInical = x;
		this.yInical = y;
		
	}

	@Override
	public boolean movimientosPosibles(int xNuevo, int yNuevo, Tablero tablero) {
		int x = this.getX();
		int y = this.getY();
		int movPosibles = 1;
		if(x == xInical && y == yInical) {
			movPosibles = 2;
		}
		
		if (this.getColor() == Constantes.COLOR_BLANCO) { // Blancas
	
			if (yNuevo < y) {
				if (y - yNuevo <= movPosibles && x == xNuevo && tablero.getPiezaPosicion(xNuevo, yNuevo) == null) { //  mover una hacia delante																				// delante
					return true;
				} else if (y - yNuevo == 1 && (x - xNuevo == 1 || xNuevo - x == 1)) { // comer en diagonal
					if (tablero.getPiezaPosicion(xNuevo, yNuevo) != null) {
						if(tablero.getPiezaPosicion(xNuevo, yNuevo).getColor() == Constantes.COLOR_NEGRO) {
							return true;
						}
					}
				}
			}
		} else { // Negras
			if (yNuevo > y) {
				if (yNuevo - y <= movPosibles && x == xNuevo && tablero.getPiezaPosicion(xNuevo, yNuevo) == null) { // mover una hacia delante
																		
					return true;
				} else if (yNuevo - y == 1 && (x - xNuevo == 1 || xNuevo - x == 1)) { // comer en diagonal
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
