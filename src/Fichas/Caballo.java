package Fichas;

import Juego.Tablero;

public class Caballo extends Pieza{

	public Caballo(int color,int x,int y) {
		super(color,x,y);
	}

	@Override
	public boolean movimientosPosibles(int xNuevo, int yNuevo, Tablero tablero){

		int x = this.getX();
		int y = this.getY();
		
		//Movimientos de un caballo : L (ambos sentidos)

		if(tablero.getPiezaPosicion(xNuevo,yNuevo) != null) { //ya tienes una ficha ahi
			if(tablero.getPiezaPosicion(xNuevo,yNuevo).getColor()==this.getColor()) {
				return false;
			}
			
		}

		
		
		if(x-xNuevo == 2 && (y-yNuevo == 1 || yNuevo-y == 1 )) {
			return true;
		}
		
		if(xNuevo-x == 2 && (y-yNuevo == 1 || yNuevo-y == 1 )) {
			return true;
		}
		
		
		if(y-yNuevo == 2 && (x-xNuevo == 1 || xNuevo-x == 1 )) {
			return true;
		}
		
		if(yNuevo-y == 2 && (x-xNuevo == 1 || xNuevo-x == 1 )) {
			return true;
		}

		
		return false;
	
			
	}

}
