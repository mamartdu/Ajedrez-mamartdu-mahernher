package Fichas;

import Juego.Tablero;

public class Rey  extends Pieza{

	public Rey(int color,int x,int y) {
		super(color,x,y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public  boolean movimientosPosibles(int xNuevo, int yNuevo, Tablero tablero) {
		int x = this.getX();
		int y = this.getY();
		if(( Math.abs(x - xNuevo) == 1 || Math.abs(y- yNuevo) == 1 ) && 
				tablero.getTablero()[xNuevo][yNuevo].getColor() != this.getColor()) {
			return true;
		}
		
		//Falta enroque
		return false;
	
	}

}
