package Fichas;

import Juego.Tablero;

public class Caballo extends Pieza{

	public Caballo(int color,int x,int y) {
		super(color,x,y);
	}

	@Override
	public boolean movimientosPosibles(int xNuevo, int yNuevo, Tablero tablero){
		
		boolean [][] tableroPosibles=new boolean[8][8];
		int xActual=super.getX();
		int yActual=super.getY();
		
		//Movimientos de un caballo : L (ambos sentidos)
	    // Movimientos horizontales
	    if (xActual > 1) {
	        int i = xActual - 2;
	        tableroPosibles[i][yActual - 1]=dentroTablero(tableroPosibles, i, yActual - 1);
	        tableroPosibles[i][yActual + 1]=dentroTablero(tableroPosibles, i, yActual + 1);
	    }
	    
	    if (xActual < 6) {
	        int i = xActual + 2;
	        tableroPosibles[i][yActual - 1]=dentroTablero(tableroPosibles, i, yActual - 1);
	        tableroPosibles[i][yActual + 1]=dentroTablero(tableroPosibles, i, yActual + 1);
	    }

	    // Movimientos verticales
	    if (yActual > 1) {
	        int j = yActual - 2;
	        tableroPosibles[xActual-1][j]=dentroTablero(tableroPosibles, xActual - 1, j);
	        tableroPosibles[xActual+1][j]=dentroTablero(tableroPosibles, xActual + 1, j);
	    }
	    
	    if (yActual < 6) {
	        int j = yActual + 2;
	        tableroPosibles[xActual-1][j]=dentroTablero(tableroPosibles, xActual - 1, j);
	        tableroPosibles[xActual+1][j]=dentroTablero(tableroPosibles, xActual + 1, j);
	    }

		
		if(tablero.getPiezaPosicion(xNuevo,yNuevo).getColor()!=this.getColor() || (tablero.getPiezaPosicion(xNuevo,yNuevo) != null)) {
			return tableroPosibles[xNuevo][yNuevo];
		}
		else {
			return false;
		}
			
	}
	
	private boolean dentroTablero(boolean[][] b, int x, int y) {
	    if (x >= 0 && x < 8 && y >= 0 && y < 8) {
	       return true;
	    }else {
	       return false;
	    }
	}

}
