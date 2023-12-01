package Fichas;

import Juego.Tablero;

public class Reina extends Pieza{

	public Reina(int color,int x,int y) {
		super(color,x,y);
	}

	@Override
	public boolean movimientosPosibles(int xNuevo, int yNuevo, Tablero tablero) {
		
		//Fuera de matriz
		if(xNuevo>7 || yNuevo>7) {
			return false;
		}
		
		boolean [][] tableroPosiblesAlfil=new boolean[8][8];
		boolean [][] tableroPosiblesTorre=new boolean[8][8];
		boolean [][] tableroPosiblesDama=new boolean[8][8];
		int xActual = this.getX();
		int yActual = this.getY();
		
		//Movimientos alfil
		for (int i = 1; i < 8; i++) {
		    if (xActual + i <= 7 && yActual + i <= 7) {
		    	tableroPosiblesAlfil[xActual + i][yActual + i] = true;
		    }
		    
		    if (xActual + i <= 7 && yActual - i >= 0) {
		    	tableroPosiblesAlfil[xActual + i][yActual - i] = true;
		    }
		    
		    if (xActual - i >= 0 && yActual + i <= 7) {
		    	tableroPosiblesAlfil[xActual - i][yActual + i] = true;
		    }
		    
		    if (xActual - i >= 0 && yActual - i >= 0) {
		    	tableroPosiblesAlfil[xActual - i][yActual - i] = true;
		    }
		}
		
		//Movimientos torre
		for (int i = 0; i < 8; i++) {
			tableroPosiblesTorre[i][yActual] = (i != xActual) ? true: false;
			tableroPosiblesTorre[xActual][i] = (i != yActual) ? true: false;
		}
		
		//Movimientos dama
		for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	        	tableroPosiblesDama[i][j] = tableroPosiblesAlfil[i][j] || tableroPosiblesTorre[i][j];
	        }
	    }
		
		if(tableroPosiblesDama[xNuevo][yNuevo] && sePuedeMover(xNuevo,yNuevo, tablero)) {
			
			if( (tablero.getPiezaPosicion(xNuevo,yNuevo) == null) || (tablero.getPiezaPosicion(xNuevo,yNuevo) != null && tablero.getPiezaPosicion(xNuevo,yNuevo).getColor()!=this.getColor())) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean sePuedeMover(int xNuevo, int yNuevo, Tablero tablero) {
		
		return sePuedeMoverComoAlfil(xNuevo,yNuevo,tablero) || sePuedeMoverComoTorre(xNuevo,yNuevo,tablero);
		
	}
	
	private boolean sePuedeMoverComoAlfil(int xNuevo, int yNuevo, Tablero tablero) {
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
	
	private boolean sePuedeMoverComoTorre(int xNuevo, int yNuevo, Tablero tablero) {
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
