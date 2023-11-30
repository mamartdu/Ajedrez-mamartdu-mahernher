package Fichas;

import Juego.Tablero;

public class Caballo  extends Pieza{

	public Caballo(int color,int x,int y) {
		super(color,x,y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean movimientosPosibles(int xNuevo, int yNuevo, Tablero tablero){
		//Movimientos de un caballo : L
		boolean [][] b=new boolean[8][8];
		int xActual=super.getX();
		int yActual=super.getY();
		
		
		
		if(tablero.getPiezaPosicion(xNuevo,yNuevo).getColor()!=this.getColor() || (tablero.getPiezaPosicion(xNuevo,yNuevo) != null)) {
			return b[xNuevo][yNuevo];
		}
		else {
			return false;
		}
		
	}

}
