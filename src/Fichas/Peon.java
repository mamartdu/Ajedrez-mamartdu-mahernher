package Fichas;

import Juego.Tablero;

public class Peon  extends Pieza{

	public Peon(int color,int x,int y) {
		super(color,x,y);
	}

	@Override
	public boolean[][] movimientosPosibles(Tablero tablero) {
		return null;
	}

}
